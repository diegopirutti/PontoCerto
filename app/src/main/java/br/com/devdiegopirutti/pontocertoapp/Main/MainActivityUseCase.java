package br.com.devdiegopirutti.pontocertoapp.Main;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import br.com.devdiegopirutti.pontocertoapp.Model.HoraEData;
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;

import static android.text.format.DateFormat.format;


public class MainActivityUseCase {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public Task<Void> gravarHorario(boolean b) {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/ponto/").push()
                .setValue(new HoraEData(new Date().getTime(), b));
    }

    public DatabaseReference getInformation() {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public Task<Void> sendRegisterDay(PontoDiario pontoDiario) {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/pontoDiario/" + format("dd/MM/yy", new Date()).toString())
                .setValue(pontoDiario);
    }
}
