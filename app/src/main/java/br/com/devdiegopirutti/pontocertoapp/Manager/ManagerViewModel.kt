package br.com.devdiegopirutti.pontocertoapp.Manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ManagerViewModel : ViewModel() {

    var useCase = UseCaseGestor()
    var usersList = MutableLiveData<List<UsersToGestor>>()

    fun getUserInformation() {
        useCase.getInformation()
                ?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val usuarios: ArrayList<UsersToGestor> = ArrayList()

                        dataSnapshot.children.forEach {
                            it.getValue(UsersToGestor::class.java)
                                    ?.let { user ->
                                        user.userId = it.key
                                        usuarios.add(user)
                                    }
                        }

                        usersList.postValue(usuarios)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
    }
}

