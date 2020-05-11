package br.com.devdiegopirutti.pontocertoapp.model

import androidx.room.Entity


class DataClasses {

    @Entity(tableName = "registerDay")
    data class RegisterDay(var registro: String, var data: String)

    data class PontoModel(var timestamp: Long = 0, var registro: Boolean? = null)

    data class InfoConta(var name: String? = null, var empresa: kotlin.String? = null)

    data class HoraEData(var timestamp: Long, var entrada: Boolean)

}

enum class Events {
    SUCESS, FAILURE, SUCESSADMIN, GRAVARPONTOENTRADA, GRAVARPONTOSAÍDA
}

 data class User(var email: String?,  var password: String?)