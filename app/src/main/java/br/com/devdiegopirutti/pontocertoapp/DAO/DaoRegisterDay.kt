package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.*
import br.com.devdiegopirutti.pontocertoapp.Model.Ponto
import br.com.devdiegopirutti.pontocertoapp.Model.PontoDiario

@Dao
interface DaoRegisterDay {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegister(ponto: PontoDiario)

    @Query("SELECT * FROM REGISTERDAY WHERE id = 0")
    fun getRegister(): PontoDiario

    @Update
    fun updateUser(ponto: Ponto)

    @Delete
    fun deleteRegister(ponto: PontoDiario)
}
