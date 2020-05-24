package br.com.devdiegopirutti.pontocertoapp.Historico;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;

public class HistViewModel extends ViewModel {

    public MutableLiveData<List<PontoDiario>> pontoLiveData = new MutableLiveData();
    public HistUseCase usecase = new HistUseCase();

    public void getData() {
        usecase.getDateInformationFromFirebase()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<PontoDiario> pontosBatidos = new ArrayList();

                        for (DataSnapshot listData : dataSnapshot.getChildren()) {
                            pontosBatidos.add(listData.getValue(PontoDiario.class));
                        }

                        pontoLiveData.postValue(pontosBatidos);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}

