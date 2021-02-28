package com.example.calificaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent as Intent

class RecyclerViewAdapter (private var alumnos : Array<Alumno>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var cv_nombre : TextView = itemView.findViewById(R.id.cv_nombre)
        var cv_expediente : TextView = itemView.findViewById(R.id.cv_expediente)
        var cv_calif_1 : TextView = itemView.findViewById(R.id.cv_calif_1)
        var cv_calif_2 : TextView = itemView.findViewById(R.id.cv_calif_2)
        var cv_calif_3 : TextView = itemView.findViewById(R.id.cv_calif_3)
        var cv_calif_promedio : TextView = itemView.findViewById(R.id.cv_calif_promedio)


        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cv_nombre.text = alumnos[position].nombre
        holder.cv_expediente.text = alumnos[position].expediente
        holder.cv_calif_1.text = alumnos[position].calif_1p.toString()
        holder.cv_calif_2.text = alumnos[position].calif_2p.toString()
        holder.cv_calif_3.text = alumnos[position].calif_3p.toString()
        holder.cv_calif_promedio.text = alumnos[position].getPromedio().toString()

        holder.itemView.setOnClickListener { v: View ->
            initInfoAlumno(holder, alumnos[position])
        }
    }

    private fun initInfoAlumno(holder: ViewHolder, alumno: Alumno) {
        val view = View.inflate(holder.itemView.context, R.layout.modificar_informacion,null)
        view.findViewById<EditText>(R.id.mi_nombre_input).setText(alumno.nombre)
        view.findViewById<EditText>(R.id.mi_exp_input).setText(alumno.expediente)
        view.findViewById<EditText>(R.id.mi_1p_calif).setText(alumno.calif_1p.toString())
        view.findViewById<EditText>(R.id.mi_2p_calif).setText(alumno.calif_2p.toString())
        view.findViewById<EditText>(R.id.mi_3p_calif).setText(alumno.calif_3p.toString())
        val dialog = openWindow(holder, view)
        view.findViewById<Button>(R.id.mi_cancelar).setOnClickListener {v: View ->
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.mi_aceptar).setOnClickListener { v: View ->
            saveInfo(alumno,view)
            Toast.makeText(holder.itemView.context, "Información guardada", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            this.notifyDataSetChanged()
        }
    }

    private fun openWindow(holder: ViewHolder, view: View): AlertDialog {
        val builder = AlertDialog.Builder(holder.itemView.context)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        return dialog
    }

    private fun saveInfo(alumno : Alumno, view : View){
        alumno.nombre = view.findViewById<EditText>(R.id.mi_nombre_input).text.toString()
        alumno.expediente = view.findViewById<EditText>(R.id.mi_exp_input).text.toString()
        alumno.calif_1p = view.findViewById<EditText>(R.id.mi_1p_calif).text.toString().toInt()
        alumno.calif_2p = view.findViewById<EditText>(R.id.mi_2p_calif).text.toString().toInt()
        alumno.calif_3p = view.findViewById<EditText>(R.id.mi_3p_calif).text.toString().toInt()
    }

    override fun getItemCount(): Int {
        return alumnos.size
    }

    fun update(array: Array<Alumno>){
        this.alumnos = array
        this.notifyDataSetChanged()
    }

}