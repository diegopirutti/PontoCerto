package br.com.devdiegopirutti.pontocertoapp.Historico;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.com.devdiegopirutti.pontocertoapp.Main.MainActivity;
import br.com.devdiegopirutti.pontocertoapp.R;


public class HistoricoActivity extends AppCompatActivity {

    AdapterHistorico adapterHistorico = new AdapterHistorico();
    HistViewModel viewModel = new HistViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_);

        initializeView();
        receiveModelList();

    }

    private void initializeView() {

        RecyclerView recyclerView = findViewById(R.id.recycler_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterHistorico);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_historico);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext()
                            , MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_historico:
                    return true;
                case R.id.sign_out:
                    FirebaseAuth.getInstance().signOut();
                    break;
            }
            return false;
        });
    }


    public void receiveModelList() {
        viewModel.pontoLiveData.observe(this, resultDay -> {
            adapterHistorico.adicionarItens(resultDay);
        });
        viewModel.getData();
    }
}
