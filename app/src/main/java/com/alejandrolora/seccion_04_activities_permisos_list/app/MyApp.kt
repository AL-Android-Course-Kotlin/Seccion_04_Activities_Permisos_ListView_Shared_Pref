package com.alejandrolora.seccion_04_activities_permisos_list.app

import android.app.Application
import com.alejandrolora.seccion_04_activities_permisos_list.others.MySharedPreferences

val preferences: MySharedPreferences by lazy { MyApp.prefs!! }

class MyApp : Application() {

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences(applicationContext)
    }

}