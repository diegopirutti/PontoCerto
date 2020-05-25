package br.com.devdiegopirutti.pontocertoapp.Main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

import br.com.devdiegopirutti.pontocertoapp.DAO.MyApplication;
import br.com.devdiegopirutti.pontocertoapp.Historico.HistoricoActivity;
import br.com.devdiegopirutti.pontocertoapp.Model.Ponto;
import br.com.devdiegopirutti.pontocertoapp.R;


public class MainActivity extends AppCompatActivity {

    private TextView userName, userEmpresa, alertText;
    private FirebaseAuth firebaseAuth;
    private Button historico, ponto;
    private Intent intent;
    private AlertDialog alerta;
    private RecyclerView recyclerView;
    private ArrayList<Ponto> list = new ArrayList<>();
    private MainActivityViewModel viewModel;
    private NavigationMenu navigationMenu;
    DayDataAdapter adapter = new DayDataAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MainActivityViewModel(Objects.requireNonNull(((MyApplication) getApplication()).getDatabase()));

        initializeViews();
        initializeButtons();
        observerResult();
        getUserInformationForView();
        initializeStetho();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_historico:
                        startActivity(new Intent(getApplicationContext()
                                ,HistoricoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_home:
                        return true;
                    case R.id.exit:
                        firebaseAuth.getInstance().signOut();
                        break;
                }
                return false;
            }
        });
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
        //historico = findViewById(R.id.hist_btn);
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

        viewModel.pontoDiarioMutableLiveData.observe(this, pontoDiario -> {
            adapter.clear();
            if (pontoDiario != null) {
                adapter.addAllRegisters(pontoDiario.getPontos());
            }
        });
    }

    public void initializeButtons() {
        intent = new Intent(this, HistoricoActivity.class);
        //historico.setOnClickListener(v -> irParaHistorico());

        ponto.setOnClickListener(v -> {
            if (adapter.getPonto().size() == 1 || adapter.getPonto().size() == 3) {
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

                    break;
                case GRAVARPONTOSAÍDA:
                    generateToastMessage(getString(R.string.pontoSaida));

                    break;
                default:
            }
        });
    }

    void getUserInformationForView() {
        viewModel.getUserInformation();
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                item.setOnMenuItemClickListener(item1 -> {
//                    if (adapter.getPonto().size() == 1 || adapter.getPonto().size() == 3) {
//                        AlertConfirmation("Saida");
//                    } else {
//                        AlertConfirmation("Entrada");
//                    }
//
//                    return true;
//                });
//
//
//        }
//        return false;
//    }
}






