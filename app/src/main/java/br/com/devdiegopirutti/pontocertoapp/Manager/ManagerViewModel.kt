package br.com.devdiegopirutti.pontocertoapp.Manager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.devdiegopirutti.pontocertoapp.Model.UsersToGestor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ManagerViewModel : ViewModel() {

    var useCase: UseCaseGestor? = null
    var usersList: MutableLiveData<Any?> = MutableLiveData<Any?>()

    fun getUserInformation() {
        useCase?.getInformation()
                ?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val usuarios: ArrayList<UsersToGestor> = ArrayList<UsersToGestor>()
                        for (listData in dataSnapshot.children) {
                            listData.getValue(UsersToGestor::class.java)?.let { usuarios.add(it) }
                        }
                        usersList.postValue(usuarios)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
    }
}

