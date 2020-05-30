package br.com.devdiegopirutti.pontocertoapp.Manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.devdiegopirutti.pontocertoapp.Model.UserFirebase
import br.com.devdiegopirutti.pontocertoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_adicionar.*

class AddUserActivity : AppCompatActivity() {

    lateinit var nome: TextInputLayout
    lateinit var email: TextInputLayout
    lateinit var senha: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_adicionar)

        initializeView();

    }

    private fun initializeView() {

        nome = findViewById(R.id.nome_cadastro)
        email = findViewById(R.id.mail_cadastrar)
        senha = findViewById(R.id.pass_cadastrar)

        btn_cadastrar.setOnClickListener {
            performRegister()
        }

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
        val emailUser = email.editText?.text.toString()
        val passwordUser = senha.editText?.text.toString()
        val nomeUser = nome.editText?.text.toString()
        val userFirebase = UserFirebase(nomeUser, emailUser, passwordUser, "")

        if (emailUser.isEmpty() || passwordUser.isEmpty() || nomeUser.isEmpty()) {
            Toast.makeText(this, "Coloque e-mail e senha", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailUser, passwordUser)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("tag", "deu certo a criação do usuario: ${it.result?.user?.uid}")

                    saveUserToFirebaseDatabase(userFirebase)
                }
                .addOnFailureListener {
                    Log.d("TAG", "Falha em criar o usuario: ${it.message}")
                    Toast.makeText(this, "deu erro!: ${it.message}", Toast.LENGTH_SHORT).show()
                }
    }

    private fun saveUserToFirebaseDatabase(user: UserFirebase) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")


        ref.setValue(user)
                .addOnSuccessListener {
                    Log.d("tag", "Salvou o usuario no firebase")
                }
                .addOnFailureListener {
                    Log.d("TAG", "falou em salvar o usuario: ${it.message}")
                }


    }
}