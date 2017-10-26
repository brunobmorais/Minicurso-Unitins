package br.com.brunomorais.minicursounitins;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import br.com.brunomorais.minicursounitins.adapter.AdapterLista;
import br.com.brunomorais.minicursounitins.bean.VagaEmprego;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterLista adapter;
    private ArrayList<VagaEmprego> arrayVagaEmprego = null;
    private ProgressBar pBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = (ProgressBar) this.findViewById(R.id.progressVagaEmprego);
        pBar.setVisibility(View.VISIBLE);

        //LISTA DE GRUPOS
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterLista(this);
        mRecyclerView.setAdapter(adapter);
        arrayVagaEmprego = new ArrayList<VagaEmprego>();
        carregaVagas();
    }

    private void carregaVagas() {

        if (verificaConexao(this)) {
            Ion.with(this)
                    .load("http://brunomorais.com.br/api/minicurso2/empregos.php/vagas/json")
                    .setLogging("minicurso", Log.DEBUG)
                    .setTimeout(60 * 60 * 1000)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            pBar.setVisibility(View.GONE);
                            try {
                                JSONArray jsonDevices = new JSONArray(result);
                                if (jsonDevices.length() > 0) {
                                    for (int i = 0; i < jsonDevices.length(); i++) {
                                        JSONObject d = jsonDevices.getJSONObject(i);
                                        arrayVagaEmprego.add(new VagaEmprego(Integer.parseInt(d.getString("CODVAGAS")), d.getString("CARGO"), d.getString("EMPRESA"), d.getString("SALARIO"), d.getString("CIDADE"), d.getString("IMAGEM"), d.getString("DATAPOSTAGEM")));
                                    }
                                    adapter.addAll(arrayVagaEmprego);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this,"SEM INTERNET", Toast.LENGTH_SHORT).show();
            pBar.setVisibility(View.GONE);
        }

    }

    public static final boolean verificaConexao(Activity context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
