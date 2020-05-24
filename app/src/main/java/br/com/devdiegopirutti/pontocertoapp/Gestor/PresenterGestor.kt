package br.com.devdiegopirutti.pontocertoapp.Gestor

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PresenterGestor : InterfaceGestor.Presenter {

    var useCase: UseCaseGestor? = null


    override fun getUserInformation() {
        useCase?.getInformation()
                ?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.child("name").getValue(String::class.java)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
    }
}

