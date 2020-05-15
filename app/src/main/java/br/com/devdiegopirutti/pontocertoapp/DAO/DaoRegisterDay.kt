package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.*
import br.com.devdiegopirutti.pontocertoapp.Model.Register
import br.com.devdiegopirutti.pontocertoapp.Model.RegisterDay

@Dao
interface DaoRegisterDay {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegister(register: Register)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegisterDay(register: RegisterDay)

    @Update
    fun updateUser(register: Register)

    @Delete
    fun deleteUser(register: Register)
}
