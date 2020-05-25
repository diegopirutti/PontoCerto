package br.com.devdiegopirutti.pontocertoapp.Manager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor;
import br.com.devdiegopirutti.pontocertoapp.R;

public class ManagerActivity extends AppCompatActivity {

    ArrayList<UsersToGestor> arrayList;
    AdapterWorkers adapterWorkers = new AdapterWorkers(arrayList, this);
    ManagerViewModel managerViewModel = new ManagerViewModel();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);
        initializeViews();
        receiveModelList();

    }

    private void initializeViews() {
        RecyclerView recyclerView = findViewById(R.id.recycler_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterWorkers);
    }

    public void receiveModelList() {
        managerViewModel.getUsersList().observe(this, it -> {
            adapterWorkers.addAllUsers((ArrayList<UsersToGestor>) it);
        });
        managerViewModel.getUserInformation();

    }


}

