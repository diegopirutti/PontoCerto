package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.devdiegopirutti.pontocertoapp.Model.DataClasses

@Database(version = 1, entities = arrayOf(DataClasses.RegisterDay::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun registerDao(): DaoRegisterDay
}