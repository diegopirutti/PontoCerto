package br.com.devdiegopirutti.pontocertoapp.UseCase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistUseCase {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public DatabaseReference pegarInformações() {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/ponto/");

    }
}
