package br.com.devdiegopirutti.pontocertoapp.Manager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class AddUserActivity : AppCompatActivity() {

    companion object {
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.register_login)

        btn_entrar_dois.setOnClickListener {
            performRegister()
        }

    }

    private fun performRegister() {
        val email = mail_cadastrar.text.toString()
        val password = pass_cadastrar.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Coloque e-mail e senha", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "tentativa de cdriar um mail: $email")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d(TAG, "deu certo a criação do usuario: ${it.result?.user?.uid}")

                }
                .addOnFailureListener {
                    Log.d(TAG, "Falha em criar o usuario: ${it.message}")
                    Toast.makeText(this, "deu erro!: ${it.message}", Toast.LENGTH_SHORT).show()
                }
    }

//    private fun saveUserToFirebaseDatabase() {
//        val uid = FirebaseAuth.getInstance().uid ?: ""
//        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
//        val user = User( nome_cadastro.text.toString(), uid)
//
//        ref.setValue(user)
//                .addOnSuccessListener {
//                    Log.d(TAG, "Salvou o usuario no firebase")
//                }
//                .addOnFailureListener {
//                    Log.d(TAG, "falou em salvar o usuario: ${it.message}")
//                }
//    }
}