package br.com.brunomorais.minicursounitins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.brunomorais.minicursounitins.bean.VagaEmprego;

public class CadastrarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String[] listacargos;
    private AutoCompleteTextView tvCargo, tvCidade, tvContratante, tvSalario, tvEmailContratante, tvTelContratante, tvQtdVaga, tvLocalContratante, tvFonteVaga;
    private TextInputLayout tilTelContratante, tilQtdVaga, tilLocalContratante, tilFonteVaga, tilDescricao;
    private CheckBox cbxDeficiente;
    private EditText tvDescricao;
    private Button btnMaisInformacoes;
    public int COD_CARGO = 1;
    public int COD_CIDADE = 2;
    public int VISIVEL =0;
    public View viewActivity;
    public ProgressBar pBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pBar = (ProgressBar) findViewById(R.id.progressVagaEmprego);
        pBar.setVisibility(View.GONE);

        viewActivity = findViewById(android.R.id.content);

        tvCargo = (AutoCompleteTextView) findViewById(R.id.tvCadastrarCargo);
        tvCidade = (AutoCompleteTextView) findViewById(R.id.tvCadastrarCidade);
        tvContratante = (AutoCompleteTextView) findViewById(R.id.tvCadastrarEmpresa);
        tvSalario = (AutoCompleteTextView) findViewById(R.id.tvCadastrarSalario);



    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    public final void uploadDados()
    {
        if (MainActivity.verificaConexao(this)) {
            //pBar.setVisibility(View.VISIBLE);
            //mSwipeRefreshLayout.setRefreshing(true);

            enviar();

        } else {
            //mSwipeRefreshLayout.setRefreshing(false);
            //pBar.setVisibility(View.GONE);
            Snackbar.make(viewActivity, "Sem conexão com a internet!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    //method to load all the devices from database
    private void enviar() {

        if (MainActivity.verificaConexao(this)) {

            long date = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = sdf.format(date);

            JsonObject json = new JsonObject();
            json.addProperty("CARGO", tvCargo.getText().toString());
            json.addProperty("CIDADE", tvCidade.getText().toString());
            json.addProperty("EMPRESA", tvContratante.getText().toString());
            json.addProperty("IMAGEM", "google.png");
            json.addProperty("SALARIO", tvSalario.getText().toString());
            json.addProperty("DATAPOSTAGEM", dateString);

            Ion.with(this)
                    .load("http://brunomorais.com.br/api/minicurso2/empregos.php/cadastrar")
                    .setLogging("minicurso", Log.DEBUG)
                    .setTimeout(60 * 60 * 1000)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            pBar.setVisibility(View.GONE);

                        }
                    });
        }
        else
        {
            Toast.makeText(this,"SEM INTERNET", Toast.LENGTH_SHORT).show();
            pBar.setVisibility(View.GONE);
        }


    }
}
