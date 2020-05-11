package br.com.devdiegopirutti.pontocertoapp.Main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import br.com.devdiegopirutti.pontocertoapp.Model.Events;
import br.com.devdiegopirutti.pontocertoapp.Model.InfoConta;

public class MainActivityViewModel {

    public MutableLiveData<Events> events = new MutableLiveData();
    public MutableLiveData<InfoConta> info = new MutableLiveData();
    private MainActivityUseCase usecase = new MainActivityUseCase();

    public void marcarPonto(String tipo, boolean ponto) {
        usecase.gravarHorario(ponto)
                .addOnCompleteListener(authResult -> {
                    if (tipo.equals("Entrada")) {
                        events.postValue(Events.GRAVARPONTOENTRADA);
                    } else {
                        events.postValue(Events.GRAVARPONTOSAÍDA);
                    }
                });
    }

    public void pegarInformaçõesDoUsuario() {
        usecase.pegarInformações()
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


