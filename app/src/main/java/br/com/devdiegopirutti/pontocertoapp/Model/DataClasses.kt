package br.com.devdiegopirutti.pontocertoapp.Model

import androidx.room.Entity


@Entity(tableName = "registerDay")
data class Register(var registro: String, var data: String)

data class RegisterDay(var entrada: String, var saida: String,
                       var segEntrada: String, var segSaida: String)

data class PontoModel(var timestamp: Long = 0, var registro: Boolean? = null)

data class InfoConta(var name: String? = null, var empresa: String? = null)

data class HoraEData(var timestamp: Long, var entrada: Boolean)

data class User(var email: String?, var password: String?)


enum class Events {
    SUCESS, FAILURE, SUCESSADMIN, GRAVARPONTOENTRADA, GRAVARPONTOSAÍDA
}

