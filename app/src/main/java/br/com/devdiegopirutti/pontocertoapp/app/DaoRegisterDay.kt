package br.com.devdiegopirutti.pontocertoapp.app

import androidx.room.*
import br.com.devdiegopirutti.pontocertoapp.model.DataClasses

@Dao
interface DaoRegisterDay {

        @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertRegister(vararg register: DataClasses.RegisterDay)

        @Update
        fun updateUser(registerDay: DataClasses.RegisterDay)

        @Delete
        fun deleteUser(registerDay: DataClasses.RegisterDay)
    }
