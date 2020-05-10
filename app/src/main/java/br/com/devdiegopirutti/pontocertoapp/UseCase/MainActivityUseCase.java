package br.com.devdiegopirutti.pontocertoapp.UseCase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Date;
import br.com.devdiegopirutti.pontocertoapp.model.HoraEData;

public class MainActivityUseCase {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public Task<Void> gravarHorario(boolean b) {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "/ponto/").push()
                .setValue(new HoraEData(new Date().getTime(), b));
    }

    public DatabaseReference pegarInformações() {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
