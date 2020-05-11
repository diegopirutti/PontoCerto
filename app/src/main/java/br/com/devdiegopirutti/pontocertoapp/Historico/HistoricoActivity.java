package br.com.devdiegopirutti.pontocertoapp.Historico;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.devdiegopirutti.pontocertoapp.R;


public class HistoricoActivity extends AppCompatActivity {

    AdapterHistorico adapterHistorico = new AdapterHistorico();
    HistViewModel viewModel = new HistViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_);

        iniatilizeRecycler();
        receiveModelList();
    }

    private void iniatilizeRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterHistorico);
    }


    public void receiveModelList() {
        viewModel.pontoLiveData.observe(this, pontoModel -> {
            //adapterHistorico.adicionarItens(pontoModel);
        });
    }
}
