package br.com.devdiegopirutti.pontocertoapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registerDay")
data class Register(@PrimaryKey(autoGenerate = true)
                    val id: Int? = 0,
                    var registro: String, var data: String)

data class RegisterDay(var horaUm: Register?, val entrada: Register?,
                       var horaDois: Register?, var saida: Register?)

data class PontoModel(var timestamp: Long = 0, var registro: Boolean? = null)

data class InfoConta(var name: String? = null, var empresa: String? = null)

data class HoraEData(var timestamp: Long, var entrada: Boolean)

data class User(var email: String?, var password: String?)

enum class Events {
    SUCESS, FAILURE, SUCESSADMIN, GRAVARPONTOENTRADA, GRAVARPONTOSAÍDA
}

