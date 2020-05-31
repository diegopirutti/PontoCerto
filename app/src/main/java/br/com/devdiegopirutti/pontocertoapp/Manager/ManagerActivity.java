package br.com.devdiegopirutti.pontocertoapp.Manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.devdiegopirutti.pontocertoapp.Login.LoginActivity;
import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor;
import br.com.devdiegopirutti.pontocertoapp.R;

public class ManagerActivity extends AppCompatActivity {

    ArrayList<UsersToGestor> arrayList = new ArrayList<>();
    AdapterWorkers adapterWorkers = new AdapterWorkers(arrayList, this);
    ManagerViewModel managerViewModel = new ManagerViewModel();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);
        initializeViews();
        receiveModelList();
        managerViewModel.getUserInformation();
    }

    private void initializeViews() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_colaboradores);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    startActivity(new Intent(getApplicationContext()
                            , AddUserActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_colaboradores:
                    return true;
                case R.id.exit:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, LoginActivity.class));
                    finishAffinity();
                    return true;
            }
            return false;
        });

        recyclerView = findViewById(R.id.recyclerViewListUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterWorkers);

    }

    public void receiveModelList() {
        managerViewModel.getUsersList().observe(this, it ->
                adapterWorkers.addAllUsers((ArrayList<UsersToGestor>) it));

    }
}

