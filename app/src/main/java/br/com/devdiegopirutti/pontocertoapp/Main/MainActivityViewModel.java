package br.com.devdiegopirutti.pontocertoapp.Main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.devdiegopirutti.pontocertoapp.DAO.AppDataBase;
import br.com.devdiegopirutti.pontocertoapp.Model.Events;
import br.com.devdiegopirutti.pontocertoapp.Model.InfoConta;
import br.com.devdiegopirutti.pontocertoapp.Model.Ponto;
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;

public class MainActivityViewModel {

    AppDataBase appDataBase;

    public MainActivityViewModel(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
        pontoDiarioMutableLiveData = appDataBase.registerDao().getRegister();
    }

    public MutableLiveData<Events> events = new MutableLiveData();
    public MutableLiveData<InfoConta> info = new MutableLiveData();
    private MainActivityUseCase usecase = new MainActivityUseCase();
    public LiveData<PontoDiario> pontoDiarioMutableLiveData;

    public void marcarPonto(String tipo, boolean ponto) {
        PontoDiario pontoDiario = pontoDiarioMutableLiveData.getValue();
        if (pontoDiario == null) {
            pontoDiario = new PontoDiario(0, new ArrayList<>());
        }
        pontoDiario.getPontos().add(new Ponto(ponto, System.currentTimeMillis()));
        appDataBase.registerDao().insertRegister(pontoDiario);
        if (pontoDiario.getPontos().size() == 4) {
            PontoDiario finalPontoDiario = pontoDiario;
            usecase.sendRegisterDay(pontoDiario)
                    .addOnFailureListener(e -> e.printStackTrace())
                    .addOnSuccessListener(aVoid -> appDataBase.registerDao()
                            .deleteRegister(finalPontoDiario));
        }
        events.postValue(tipo.equals("Entrada") ? Events.GRAVARPONTOENTRADA : Events.GRAVARPONTOSAÍDA);
    }

    public void getUserInformation() {
        usecase.getInformation()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        info.postValue(new InfoConta(
                                dataSnapshot.child("name").getValue(String.class),
                                dataSnapshot.child("empresa").getValue(String.class)));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }
}


