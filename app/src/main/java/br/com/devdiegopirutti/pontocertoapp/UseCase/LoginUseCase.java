package br.com.devdiegopirutti.pontocertoapp.UseCase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.devdiegopirutti.pontocertoapp.model.User;

public class LoginUseCase {

    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    public Task<AuthResult> verifyLoginType(User user) {
       return fbAuth.signInWithEmailAndPassword(user.email, user.password);
    }


}


