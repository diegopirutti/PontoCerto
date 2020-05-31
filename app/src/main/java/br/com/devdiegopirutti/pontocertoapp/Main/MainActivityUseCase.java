package br.com.devdiegopirutti.pontocertoapp.Main;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario;

import static android.text.format.DateFormat.format;


public class MainActivityUseCase {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public DatabaseReference getInformation() {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public Task<Void> sendRegisterDay(PontoDiario pontoDiario) {
        pontoDiario.setData(format("dd-MM-yy", new Date()).toString());
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/pontoDiario/")
                .push()
                .setValue(pontoDiario);
    }
}
