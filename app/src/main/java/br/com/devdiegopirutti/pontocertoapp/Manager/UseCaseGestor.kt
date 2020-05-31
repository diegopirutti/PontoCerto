package br.com.devdiegopirutti.pontocertoapp.Manager

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UseCaseGestor {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun getInformation(): DatabaseReference? {
        return firebaseDatabase.reference
                .child("/users/")
    }
}
