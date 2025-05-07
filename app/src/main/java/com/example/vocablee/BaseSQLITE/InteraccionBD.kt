package com.example.vocablee.BaseSQLITE

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class InteraccionBD(context: Context) {
    private val dbHelper: HelperDatos = HelperDatos(context)

    fun addUser(username: String, password: String): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(HelperDatos.COLUMN_USUARIO, username)
            put(HelperDatos.COLUMN_CONTRA, password)
        }
        val newRowId = db.insert(HelperDatos.USUARIOS, null, values)
        return newRowId != -1L
    }

    fun checkUser(username: String, password: String): Boolean {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            HelperDatos.USUARIOS,
            arrayOf(HelperDatos.COLUMN_RECORD),
            "${HelperDatos.COLUMN_USUARIO} = ? AND ${HelperDatos.COLUMN_CONTRA} = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun formateo(){
        dbHelper.Eliminar()
    }
}