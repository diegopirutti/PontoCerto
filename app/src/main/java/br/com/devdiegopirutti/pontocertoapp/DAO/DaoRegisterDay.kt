package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.*
import br.com.devdiegopirutti.pontocertoapp.Model.Register

@Dao
interface DaoRegisterDay {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegister(register: Register)

    @Update
    fun updateUser(register: Register)

    @Delete
    fun deleteUser(register: Register)
}
