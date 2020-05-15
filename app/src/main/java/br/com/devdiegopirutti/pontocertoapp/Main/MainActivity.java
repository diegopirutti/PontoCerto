package br.com.devdiegopirutti.pontocertoapp.Main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import br.com.devdiegopirutti.pontocertoapp.DAO.MyApplication;
import br.com.devdiegopirutti.pontocertoapp.Historico.HistoricoActivity;
import br.com.devdiegopirutti.pontocertoapp.Model.Register;
import br.com.devdiegopirutti.pontocertoapp.Model.RegisterDay;
import br.com.devdiegopirutti.pontocertoapp.R;


public class MainActivity extends AppCompatActivity {

    private TextView userName, userEmpresa, alertText;
    private FirebaseAuth firebaseAuth;
    private Button historico, ponto;
    private Intent intent;
    private AlertDialog alerta;
    private RecyclerView recyclerView;
    private ArrayList<RegisterDay> list = new ArrayList<>();
    private MainActivityViewModel viewModel = new MainActivityViewModel();
    DayDataAdapter adapter = new DayDataAdapter(list);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeButtons();
        observerResult();
        pegarInformações();
        listarColaboradores();
        initializeStetho();
    }

    private void initializeStetho() {
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        Stetho.Initializer initializer = initializerBuilder.build();
        Stetho.initialize(initializer);
    }


    public void irParaHistorico() {
        startActivity(intent);
    }


    public void initializeViews() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userEmpresa = findViewById(R.id.empresa);
        userName = findViewById(R.id.txt_nome);
        historico = findViewById(R.id.hist_btn);
        ponto = findViewById(R.id.btn_registro);
        recyclerView = findViewById(R.id.recyclerViewMain);
        alertText = findViewById(R.id.AlertText);

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

        historico.setOnClickListener(v -> irParaHistorico());
        ponto.setOnClickListener(v -> {
            if (list.size() == 1 || list.size() == 3) {
                AlertConfirmation("Saida");
            } else {
                AlertConfirmation("Entrada");
            }
        });
    }

    private void AlertConfirmation(String tipo) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.perguntaPonto, tipo));
        builder.setPositiveButton("Sim", ((dialog, which) -> marcarPonto(tipo)));
        builder.setNegativeButton("Não", ((dialog, which) -> closeOptionsMenu()));
        alerta = builder.create();
        alerta.show();
    }


    private void marcarPonto(String tipo) {
        if (tipo.equals("Entrada")) {
            viewModel.marcarPonto(tipo, true);
        } else {
            viewModel.marcarPonto(tipo, false);
        }
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
                    verifyAllRegisters("Entrada");
                    break;
                case GRAVARPONTOSAÍDA:
                    generateToastMessage(getString(R.string.pontoSaida));
                    verifyAllRegisters("Saída");
                    break;
                default:
            }
        });
    }

    void pegarInformações() {
        viewModel.pegarInformaçõesDoUsuario();
    }

    public void listarColaboradores() {

        Register colaborador = new Register(0, "Yuri Gonçalves Moreira Orfon", "Desenvolvedor Android Jr");
        //list.add(colaborador);

        Register colaboradora = new Register(0, "Yuri Gonçalves Moreira Orfon", "Desenvolvedor Android Jr");
        //list.add(colaboradora);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void verifyAllRegisters(String type) {

        Register pontoGravado = new Register(0, type, getDateTime());
        RegisterDay pontoGravadoDay = new RegisterDay("pontoGravado", "", "", "");
        if (list.size() < 4) {
            list.clear();
            recyclerView.setVisibility(View.INVISIBLE);
            alertText.setVisibility(View.VISIBLE);
            registerDay(pontoGravadoDay);
            ((MyApplication) getApplication()).getDatabase().registerDao().insertRegister(pontoGravado);
        } else adapter.updateList(pontoGravadoDay);
    }

    private void countTime() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                alertText.setVisibility(View.VISIBLE);

            }
        }, 4000, 100000);

    }

    private void registerDay(RegisterDay registerDay) {
        if (registerDay.getEntrada() == null) {
            ((MyApplication) getApplication()).getDatabase().registerDao().insertRegisterDay(registerDay);
        } else if (registerDay.getSaida() == null) {
            ((MyApplication) getApplication()).getDatabase().registerDao().insertRegisterDay(registerDay);
        } else if (registerDay.getSegEntrada() == null) {
            ((MyApplication) getApplication()).getDatabase().registerDao().insertRegisterDay(registerDay);
        } else {
            ((MyApplication) getApplication()).getDatabase().registerDao().insertRegisterDay(registerDay);
        }

    }
}





