package br.com.devdiegopirutti.pontocertoapp.Gestor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor;
import br.com.devdiegopirutti.pontocertoapp.R;

public class ColaboradoresFragment extends Fragment {

    ArrayList<UsersToGestor> arrayList;
    AdapterColaboradores adapterColaboradores = new AdapterColaboradores(arrayList, getActivity());
    PresenterGestor presenterGestor = new PresenterGestor();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colaboradores, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initializeViews();

    }

    private void initializeViews() {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_hist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterColaboradores);
    }


}

