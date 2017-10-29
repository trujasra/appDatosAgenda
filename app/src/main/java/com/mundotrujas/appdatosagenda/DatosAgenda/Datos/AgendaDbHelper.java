package com.mundotrujas.appdatosagenda.DatosAgenda.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trujas on 7/10/2017.
 */

public class AgendaDbHelper extends SQLiteOpenHelper {

    // Variables de nombre base de Datos y la version.
    public static final String DB_NAME = "datosAgenda.sqlite";
    public static final int DB_VERSION = 1;

    public AgendaDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ejecuta para crear la tabla de Usuarios.
        db.execSQL(AgendaAdministracion.CREATE_TABLE_USUARIO);
        // ejecuta para crear la tabla de Agenda.
        db.execSQL(AgendaAdministracion.CREATE_TABLE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Verifica si existe la tabla Usuarios lo elimina.
        db.execSQL("DROP TABLE IF EXISTS " + AgendaAdministracion.TABLE_NAME_USUARIO);
        // Verifica si existe la tabla Agenda lo elimina.
        db.execSQL("DROP TABLE IF EXISTS " + AgendaAdministracion.TABLE_NAME_AGENDA);
         // envia al metodo para crear las tablas.
        onCreate(db);
    }
}
