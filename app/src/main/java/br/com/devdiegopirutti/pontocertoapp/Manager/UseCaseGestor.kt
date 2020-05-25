package br.com.devdiegopirutti.pontocertoapp.Manager

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UseCaseGestor {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun getInformation(): DatabaseReference? {
        return firebaseDatabase.getReference()
                .child("/users/")
                .child(FirebaseAuth.getInstance().currentUser!!.displayName.toString())
    }

}
