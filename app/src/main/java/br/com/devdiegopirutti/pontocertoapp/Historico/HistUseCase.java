package br.com.devdiegopirutti.pontocertoapp.Historico;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistUseCase {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public DatabaseReference getDateInformationFromFirebase(String userId) {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(userId != null ? userId + "/pontoDiario/" : FirebaseAuth.getInstance().getCurrentUser().getUid() + "/pontoDiario/");
    }
}
