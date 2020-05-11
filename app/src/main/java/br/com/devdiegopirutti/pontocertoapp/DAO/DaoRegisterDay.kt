package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.*
import br.com.devdiegopirutti.pontocertoapp.Model.RegisterDay

@Dao
interface DaoRegisterDay {

        @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertRegister(vararg register: RegisterDay)

        @Update
        fun updateUser(registerDay: RegisterDay)

        @Delete
        fun deleteUser(registerDay: RegisterDay)
    }
