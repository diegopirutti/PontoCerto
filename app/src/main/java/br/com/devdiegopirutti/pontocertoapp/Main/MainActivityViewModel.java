package br.com.devdiegopirutti.pontocertoapp.Main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import br.com.devdiegopirutti.pontocertoapp.DAO.AppDataBase;
import br.com.devdiegopirutti.pontocertoapp.Model.Events;
import br.com.devdiegopirutti.pontocertoapp.Model.InfoConta;
import br.com.devdiegopirutti.pontocertoapp.Model.Ponto;
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;

public class MainActivityViewModel {

    AppDataBase myApplication;

    public MainActivityViewModel(AppDataBase appDataBase) {
        this.myApplication = appDataBase;
    }

    public MutableLiveData<Events> events = new MutableLiveData();
    public MutableLiveData<InfoConta> info = new MutableLiveData();
    private MainActivityUseCase usecase = new MainActivityUseCase();

    public void marcarPonto(String tipo, boolean ponto) {
        PontoDiario pontoDiario = myApplication.registerDao().getRegister();
        if (pontoDiario == null) {
            pontoDiario = new PontoDiario(0, new ArrayList<>());
        }

        pontoDiario.getPontos().add(new Ponto(ponto, new Date().getTime()));
        myApplication.registerDao().insertRegister(pontoDiario);
        if (pontoDiario.getPontos().size() == 4) {
            PontoDiario finalPontoDiario = pontoDiario;
            usecase.sendRegisterDay(pontoDiario)
                    .addOnSuccessListener(aVoid -> myApplication.registerDao()
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


