package br.com.devdiegopirutti.pontocertoapp.DAO

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.devdiegopirutti.pontocertoapp.Model.Register

@Database(version = 1, entities = arrayOf(Register::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun registerDao(): DaoRegisterDay

}

open class MyApplication : Application() {

    var database: AppDataBase? = null

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDataBase::class.java, "my-db")
                .allowMainThreadQueries().build()

    }
}