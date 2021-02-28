package com.example.calificaciones

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    private var clicked = false

    var alumnos : Array<Alumno> = arregloAlumnos().getAlumnos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv_recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
        val mAdapter = RecyclerViewAdapter(alumnos)

        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = mAdapter


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val fab_agrega_alumno = findViewById<ExtendedFloatingActionButton>(R.id.fab_agrega_alumno)

        fab.setOnClickListener { view ->
            onAddButtonClicked()
        }

        fab_agrega_alumno.setOnClickListener { view ->
            nuevoAlumno(mAdapter)
        }

    }


    private fun nuevoAlumno(adapter: RecyclerViewAdapter) {
        val view = View.inflate(this, R.layout.agregar_alumno,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        onAddButtonClicked()
        view.findViewById<Button>(R.id.aa_cancelar).setOnClickListener { v: View ->
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.aa_aceptar).setOnClickListener { v : View ->
            val nuevo_alumno = Alumno(
                    view.findViewById<EditText>(R.id.aa_nombre_input).text.toString(),
                    view.findViewById<EditText>(R.id.aa_exp_input).text.toString()
            )
            alumnos += nuevo_alumno
            adapter.update(alumnos)
            Toast.makeText(this, "Alumno agregado", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setclickable(clicked)
        clicked = !clicked
    }

    private fun setclickable(clicked: Boolean) {
        val fab_agrega_alumno = findViewById<ExtendedFloatingActionButton>(R.id.fab_agrega_alumno)
        if (!clicked){
            fab_agrega_alumno.isClickable = true
        } else {
            fab_agrega_alumno.isClickable = false
        }

    }

    private fun setVisibility(clicked: Boolean) {
        val fab_agrega_alumno = findViewById<ExtendedFloatingActionButton>(R.id.fab_agrega_alumno)
        if (!clicked){
            fab_agrega_alumno.visibility = View.VISIBLE
        } else {
            fab_agrega_alumno.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        val fab_agrega_alumno = findViewById<ExtendedFloatingActionButton>(R.id.fab_agrega_alumno)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        if (!clicked) {
            fab_agrega_alumno.startAnimation(fromBottom)
            fab.startAnimation(rotateOpen)
        } else {
            fab_agrega_alumno.startAnimation(toBottom)
            fab.startAnimation(rotateClose)
        }
    }
}