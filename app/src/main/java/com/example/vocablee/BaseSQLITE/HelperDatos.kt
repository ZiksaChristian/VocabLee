package com.example.vocablee.BaseSQLITE

import android.app.DownloadManager.COLUMN_ID
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDatos(context: Context) : SQLiteOpenHelper(context, NOMBRE, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USUARIO_TABLE)
        db.execSQL(CREATE_LIBROS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $USUARIOS")
        db.execSQL("DROP TABLE IF EXISTS $LIBROS")
        onCreate(db)
    }

    fun Eliminar() {
        val db: SQLiteDatabase = writableDatabase
        db.execSQL("DELETE FROM $USUARIOS")
        db.execSQL("DELETE FROM $LIBROS")
        db.close()
    }


    companion object {
        private const val VERSION = 2 // Aumenta la versión de la base de datos
        private const val NOMBRE = "BASEdeDatos.db"
        const val USUARIOS = "Usuarios"
        const val COLUMN_RECORD = "record"
        const val COLUMN_USUARIO = "username"
        const val COLUMN_CONTRA = "password"

        const val LIBROS = "Libros"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_LECTURA = "description"

        private const val CREATE_USUARIO_TABLE = "CREATE TABLE $USUARIOS (" +
                "$COLUMN_RECORD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USUARIO TEXT, " +
                "$COLUMN_CONTRA TEXT)"

        private const val CREATE_LIBROS_TABLE = "CREATE TABLE $LIBROS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_LECTURA TEXT, " +
                "$COLUMN_RECORD INTEGER DEFAULT 0)" // Añadir la columna de rating
    }
}