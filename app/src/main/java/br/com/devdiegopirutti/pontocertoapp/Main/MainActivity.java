package br.com.devdiegopirutti.pontocertoapp.Main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.devdiegopirutti.pontocertoapp.R;
import br.com.devdiegopirutti.pontocertoapp.Model.DataClasses;
import br.com.devdiegopirutti.pontocertoapp.Historico.HistoricoActivity;


public class MainActivity extends AppCompatActivity {

    private TextView userName, userEmpresa;
    private FirebaseAuth firebaseAuth;
    private Button sair, historico, pontoEntrada, pontoSaida;
    private Intent intent;
    private AlertDialog alerta;
    private RecyclerView recyclerView;
    private ArrayList<DataClasses.RegisterDay> list = new ArrayList<DataClasses.RegisterDay>();
    private MainActivityViewModel viewModel = new MainActivityViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeButtons();
        observerResult();
        pegarInformações();
        listarColaboradores();

    }


    public void irParaHistorico() {
        startActivity(intent);
    }


    public void initializeViews() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        DayDataAdapter adapter = new DayDataAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userEmpresa = findViewById(R.id.empresa);
        userName = findViewById(R.id.txt_nome);
        historico = findViewById(R.id.hist_btn);
        pontoEntrada = findViewById(R.id.btn_entrada);
        recyclerView = findViewById(R.id.recyclerViewMain);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        viewModel.info
                .observe(this,
                        infoConta -> {
                            userEmpresa.setText(infoConta.getEmpresa());
                            userName.setText(infoConta.getName());
                        }
                );

    }

    public void initializeButtons() {

        intent = new Intent(this, HistoricoActivity.class);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //sair.setOnClickListener(v -> firebaseAuth.signOut());
        historico.setOnClickListener(v -> irParaHistorico());
        pontoEntrada.setOnClickListener(v -> builderConfirmacao( "Entrada"));
        //pontoSaida.setOnClickListener(v -> builderConfirmacao( "Saida"));
    }

    private void builderConfirmacao( String tipo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(getString(R.string.perguntaPonto, tipo));
            builder.setPositiveButton("Sim", ((dialog, which) -> marcarPonto(tipo, tipo.equals("Entrada"))));
            builder.setNegativeButton("Não", ((dialog, which) -> closeOptionsMenu()));
            alerta = builder.create();
            alerta.show();
        }


    private void marcarPonto(String tipo, Boolean ponto) {

        //viewModel.marcarPonto(tipo, ponto);
    }

    private void generateToastMessage(String string) {
        Toast.makeText(MainActivity.this, string,
                Toast.LENGTH_LONG).show();
    }

    private void observerResult() {
        viewModel.events.observe(this, events -> {
            switch (events) {
                case GRAVARPONTOENTRADA:
                    generateToastMessage(getString(R.string.pontoEntrada));
                    break;
                case GRAVARPONTOSAÍDA:
                    generateToastMessage(getString(R.string.pontoSaida));
                    break;
                default:
            }
        });
    }

    void pegarInformações() {
        viewModel.pegarInformaçõesDoUsuario();
    }

    public void listarColaboradores() {

        DataClasses.RegisterDay colaborador = new DataClasses.RegisterDay("Yuri Gonçalves Moreira Orfon", "Desenvolvedor Android Jr");
        list.add(colaborador);

        DataClasses.RegisterDay colaboradora = new DataClasses.RegisterDay("Yuri Gonçalves Moreira Orfon", "Desenvolvedor Android Jr");
        list.add(colaboradora);
    }

}




