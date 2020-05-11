package br.com.devdiegopirutti.pontocertoapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.devdiegopirutti.pontocertoapp.R;
import br.com.devdiegopirutti.pontocertoapp.ViewModel.LoginViewModel;
import br.com.devdiegopirutti.pontocertoapp.model.User;


public class LoginActivity extends AppCompatActivity {

    EditText emailTxt, passwordTxt;
    Button button;
    LoginViewModel model = new LoginViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        observerResult();
    }


    private void initializeViews() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        button = findViewById(R.id.btn_logar);
        emailTxt = findViewById(R.id.edt_email);
        passwordTxt = findViewById(R.id.edt_senha);
        button.setOnClickListener(view -> doLogin());
    }

    //Login Region
    private void doLogin() {
        User user = new User(emailTxt.getText().toString(), passwordTxt.getText().toString());
        if (user.getEmail() != null && user.getPassword() != null) model.doLogin(user);
        else Toast.makeText(this, "ACC/PASS Inválidos", Toast.LENGTH_LONG).show();
    }

    private void observerResult() {
        model.event.observe(this, events -> {
            switch (events) {
                case SUCESS:
                    loginSuccessView();
                    break;
                case SUCESSADMIN:
                    loginSuccessToAdmView();
                    break;

                default:
                    loginFailureView();

            }
        });
    }

    private void loginSuccessView() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void loginFailureView() {
        Toast.makeText(this, "Erro do Login", Toast.LENGTH_LONG).show();
    }

    private void loginSuccessToAdmView() {
        // startActivity(new Intent(this, MainActivity.class));
    }
    //end Login Region
}






