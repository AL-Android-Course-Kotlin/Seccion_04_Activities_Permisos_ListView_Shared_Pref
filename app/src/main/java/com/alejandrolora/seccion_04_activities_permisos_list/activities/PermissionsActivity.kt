package com.alejandrolora.seccion_04_activities_permisos_list.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.alejandrolora.seccion_04_activities_permisos_list.R
import kotlinx.android.synthetic.main.activity_permiossions.*

class PermissionsActivity : AppCompatActivity() {

    private val requestCameraPermission = 100
    private val requestCameraPicture = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permiossions)

        buttonPicture.setOnClickListener { getPictureFromCamera() }
    }

    private fun getPictureFromCamera() {
        // Asegurarnos de que no hay permiso de camara en el manifest
        // Crear intent para capturar la foto
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Comprobar que podemos manejar la captura de fotos (Tenemos camara y app de camara)
        if (pictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(pictureIntent, requestCameraPicture)
        } else {
            // No hay activity que pueda manejar el intent (por ejemplo sin cámara)
        }
    }

    private fun getPictureFromCameraAskingPermissions() {
        // Añadir permiso al manifest
        // Comprobar el permiso de la camara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Si no ha sido aceptado previamente (Para versiones desde la 6.0 [API 23] en adelante)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), requestCameraPermission)
        } else {
            // Si ha sido aceptado previamente (Para versiones inferiores a la 6.0 [API 23])
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, requestCameraPicture)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            requestCameraPermission -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso aceptado
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, requestCameraPicture)
                } else {
                    // Permiso denegado
                    Toast.makeText(this, "You can't take a picture if you don't allow it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestCameraPicture -> {
                // Comprobar si el resultado es bueno
                if (resultCode == Activity.RESULT_OK) {
                    // Obtenemos los extras del intent recibido por parametros
                    val extras = data!!.extras
                    // Formamos el bitmap a partir de los extras
                    val imageBitMap = extras.get("data") as Bitmap
                    // Cargamos la foto como bitmap en el imageView
                    imageViewPicture.setImageBitmap(imageBitMap)
                } else {
                    // La foto no ha sido tomada con éxito
                    Toast.makeText(this, "Picture has failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
