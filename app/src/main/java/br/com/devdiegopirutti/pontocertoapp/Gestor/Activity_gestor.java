package br.com.devdiegopirutti.pontocertoapp.Gestor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import br.com.devdiegopirutti.pontocertoapp.R;

public class Activity_gestor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);

        BottomNavigationView navigation = findViewById(R.id.navigation_view);
        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        loadFragment(new ColaboradoresFragment());

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.navigation_colaboradores:
                fragment = new ColaboradoresFragment();
                break;
            case R.id.navigation_add:
                fragment = new AdicionarFragment();
                break;
            case R.id.navigation_more:
                fragment = new OutrosFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
