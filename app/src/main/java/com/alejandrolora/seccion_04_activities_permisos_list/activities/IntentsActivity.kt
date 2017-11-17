package com.alejandrolora.seccion_04_activities_permisos_list.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alejandrolora.seccion_04_activities_permisos_list.R
import com.alejandrolora.seccion_04_activities_permisos_list.models.Student
import kotlinx.android.synthetic.main.activity_intents.*

class IntentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        buttonIntentExtras.setOnClickListener { goIntentExtras() }
        buttonIntentFlags.setOnClickListener { goIntentFlags() }
        buttonIntentObject.setOnClickListener { goIntentObject() }
    }

    private fun goIntentExtras() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
        intent.putExtra("name", "Alejandro")
        intent.putExtra("lastName", "Lora")
        intent.putExtra("age", 27)
        intent.putExtra("developer", true)
        startActivity(intent)
    }

    private fun goIntentFlags() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
        // intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY // No guarda el activity en el stack
        // intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION // No animación
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Borra el stack completo
        startActivity(intent)
        // finish() // Destruye este activity evitando que entre en el stack
    }

    private fun goIntentObject() {
        val intent = Intent(this, IntentExtrasActivity::class.java)
        val student = Student("Alberto", "Santos", 24, false)
        intent.putExtra("student", student)
        startActivity(intent)
    }
}
