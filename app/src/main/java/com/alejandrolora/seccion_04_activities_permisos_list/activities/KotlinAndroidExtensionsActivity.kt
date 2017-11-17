package com.alejandrolora.seccion_04_activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.alejandrolora.seccion_04_activities_permisos_list.R
import kotlinx.android.synthetic.main.activity_kotlin_android_extensions.*

class KotlinAndroidExtensionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_android_extensions)

        val btn = findViewById<Button>(R.id.buttonById)
        btn.setOnClickListener { Toast.makeText(this, "Click By ID!", Toast.LENGTH_SHORT).show() }

        buttonByKAT.setOnClickListener { Toast.makeText(this, "Click By KAT!", Toast.LENGTH_SHORT).show() }
    }
}
