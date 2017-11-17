package com.alejandrolora.seccion_04_activities_permisos_list.models

import android.os.Parcel
import android.os.Parcelable

// Parcel es un contenedor para un mensaje.
// Puede ser usado para mandar un objeto serializado a través de Intent.
// Mejore rendimiento que Serializer.

data class Student(var name: String, var lastName: String, var age: Int, var isDeveloper: Boolean = true) : Parcelable {
    // Leer en el mismo orden que declaramos las propiedades
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Tenemos que escribir los valores en el mismo orden en el que leemos en el constructor
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeInt(age)
        parcel.writeByte(if (isDeveloper) 1 else 0)
    }

    // Describe el tipo de contenido del objeto Parcelable
    // 0 siempre se usa, el otro valor es 1 o CONTENTS_FILE_DESCRIPTOR, para tipos de objetos que implementa un File Descriptor
    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Student> {
        // Esto creará el objeto desde el parcel llamando al constructor SECUNDARIO
        override fun createFromParcel(parcel: Parcel) = Student(parcel)

        // Esto ayudará a serializar arrays de objetos del mismo tipo que esta clase (Student)
        override fun newArray(size: Int) = arrayOfNulls<Student?>(size)
    }

}