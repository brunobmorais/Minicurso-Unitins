package br.com.brunomorais.minicursounitins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VagaActivity extends AppCompatActivity {

    private TextView tvCargo, tvDataPostagem, tvContratante, tvSalario, tvCidade;
    private ImageView imgVagaCargo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaga);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCargo = (TextView) findViewById(R.id.tvVagaCargo);
        tvDataPostagem = (TextView) findViewById(R.id.tvVagaDataPostagem);
        tvContratante = (TextView) findViewById(R.id.tvVagaEmpresa);
        tvSalario = (TextView) findViewById(R.id.tvVagaSalario);
        tvCidade = (TextView) findViewById(R.id.tvVagaCidade);
        imgVagaCargo = (ImageView) findViewById(R.id.imgVagaCargo);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            tvCargo.setText(bundle.getString("cargo"));
            tvDataPostagem.setText(bundle.getString("datapostagem"));
            tvContratante.setText(bundle.getString("empresa"));
            tvSalario.setText(bundle.getString("salario"));
            tvCidade.setText(bundle.getString("cidade"));
            Picasso.with(this).load("http://brunomorais.com.br/api/minicurso/img/"+bundle.getString("imagem")).into(imgVagaCargo);

        }
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
}
