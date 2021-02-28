package com.example.calificaciones

class Alumno  (var nombre: String, var expediente: String){

    var calif_1p = 0
    var calif_2p = 0
    var calif_3p = 0

    fun getPromedio(): Int {
        return (calif_1p + calif_2p + calif_3p) / 3
    }



}