package br.com.devdiegopirutti.pontocertoapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "register")
data class Ponto(var entrada: Boolean? = null,
                 @PrimaryKey
                 var data: Long? = null)

@Entity(tableName = "registerDay")
data class PontoDiario(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = 0,
        @TypeConverters(PontoConverter::class)
        var pontos: List<Ponto>? = null,
        var data: String? = null)

data class ResultDay(val pontoDiario: List<PontoDiario>? = null)

data class InfoConta(var name: String? = null, var empresa: String? = null)

data class HoraEData(var timestamp: Long, var entrada: Boolean)

data class UsersToGestor(var name: String?)

data class User(var email: String?, var password: String?)

enum class Events {
    SUCESS, FAILURE, SUCESSADMIN, GRAVARPONTOENTRADA, GRAVARPONTOSAÍDA
}

class PontoConverter {

    @TypeConverter
    fun fromJson(list: List<Ponto>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toJson(json: String): List<Ponto> {
        val type = object : TypeToken<List<Ponto>>() {}.type
        return Gson().fromJson<List<Ponto>>(json, type)
    }
}

