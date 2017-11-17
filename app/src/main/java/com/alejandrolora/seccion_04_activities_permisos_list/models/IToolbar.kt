package com.alejandrolora.seccion_04_activities_permisos_list.models

import android.support.v7.widget.Toolbar

interface IToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(value: Boolean)
}