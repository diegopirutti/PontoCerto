package br.com.devdiegopirutti.pontocertoapp.DAO

import androidx.room.Database
import androidx.room.RoomDatabase

import br.com.devdiegopirutti.pontocertoapp.Model.Register

@Database(version = 1, entities = arrayOf(Register::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun registerDao(): DaoRegisterDay
}