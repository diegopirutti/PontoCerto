package br.com.devdiegopirutti.pontocertoapp.Manager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.devdiegopirutti.pontocertoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class AddUserActivity : AppCompatActivity() {
    var firebaseAuth: FirebaseAuth? = null
    companion object {
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_adicionar)

//        btn_entrar_dois.setOnClickListener {
//            performRegister()
//        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        bottomNavigationView.selectedItemId = R.id.navigation_colaboradores
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_colaboradores -> {
                    startActivity(Intent(applicationContext
                            , ManagerActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_add -> return@OnNavigationItemSelectedListener true
                R.id.exit -> {
                    FirebaseAuth.getInstance().signOut()
                }
            }
            false
        })

    }

    private fun performRegister() {
//        val email = mail_cadastrar.text.toString()
//        val password = pass_cadastrar.text.toString()

//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Coloque e-mail e senha", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        Log.d(TAG, "tentativa de cdriar um mail: $email")
//
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if (!it.isSuccessful) return@addOnCompleteListener
//
//                    Log.d(TAG, "deu certo a criação do usuario: ${it.result?.user?.uid}")
//
//                }
//                .addOnFailureListener {
//                    Log.d(TAG, "Falha em criar o usuario: ${it.message}")
//                    Toast.makeText(this, "deu erro!: ${it.message}", Toast.LENGTH_SHORT).show()
//                }
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