package com.example.calificaciones

class Alumno  (var nombre: String, var expediente: String){

    var calif_1p = 0
    var calif_2p = 0
    var calif_3p = 0

    fun getPromedio(): Int {
        return (calif_1p + calif_2p + calif_3p) / 3
    }

    fun setCalif(calif_1p : Int, calif_2p : Int, calif_3p  : Int) {
        this.calif_1p = calif_1p
        this.calif_2p = calif_2p
        this.calif_3p = calif_3p
    }


}