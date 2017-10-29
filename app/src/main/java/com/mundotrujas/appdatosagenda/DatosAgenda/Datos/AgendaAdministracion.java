package com.mundotrujas.appdatosagenda.DatosAgenda.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.mundotrujas.appdatosagenda.DatosAgenda.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trujas on 7/10/2017.
 */

public class AgendaAdministracion {

    // Variable para el nombre de la tabla Usuario
    public static final String TABLE_NAME_USUARIO = "usuario";

    // Variable para el nombre de la tabla Agenda
    public static final String TABLE_NAME_AGENDA = "agenda";

    // Variables para los campos de la tabla Usuario
    private static final String FIELD_USUARIO_ID = "_id_usuario";
    private static final String FIELD_USUARIO_NOMBRES = "nombres";
    private static final String FIELD_USUARIO_PRIMER_APELLIDO = "primer_apellido";
    private static final String FIELD_USUARIO_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String FIELD_USUARIO_USER = "user";
    private static final String FIELD_USUARIO_PWD = "password";
    private static final String FIELD_USUARIO_ESTADO = "estado";

    // Variables para los campos de la tabla Agenda
    private static final String FIELD_AGENDA_ID = "id_agenda";
    private static final String FIELD_AGENDA_ID_USUARIO = "id_usuario";
    private static final String FIELD_AGENDA_TITULO = "titulo";
    private static final String FIELD_AGENDA_DESCRIPCION = "descripcion";
    private static final String FIELD_AGENDA_ESTADO = "estado";

    // Variables para abrir la conexion con la BD y el administrador.
    private SQLiteDatabase db;
    private AgendaDbHelper dbHelper;

    // Variable y Srcipt para la creacion de la tabla Usuario.
    public static final String CREATE_TABLE_USUARIO = "create table " + TABLE_NAME_USUARIO + "(" +
            FIELD_USUARIO_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_USUARIO_NOMBRES + " TEXT NOT NULL," +
            FIELD_USUARIO_PRIMER_APELLIDO + " TEXT NOT NULL," +
            FIELD_USUARIO_SEGUNDO_APELLIDO + " TEXT NULL," +
            FIELD_USUARIO_USER + " TEXT NOT NULL," +
            FIELD_USUARIO_PWD + " TEXT NOT NULL," +
            FIELD_USUARIO_ESTADO + " INTEGER );";

    // Variable y Script para la creacion de la tabla Agenda.
    public static final String CREATE_TABLE_AGENDA = "create table " + TABLE_NAME_AGENDA + "(" +
            FIELD_AGENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_AGENDA_ID_USUARIO + " INTEGER," +
            FIELD_AGENDA_TITULO + " TEXT NOT NULL," +
            FIELD_AGENDA_DESCRIPCION + " TEXT NOT NULL," +
            FIELD_AGENDA_ESTADO + " INTEGER );";


    // Método para crear e instanciar la BD y las tablss.
    public AgendaAdministracion(Context context) {
        // instanciamos un objeto de Agenda SqliteOpenHelper.
        dbHelper = new AgendaDbHelper(context);
        // Ponemos la Base de datos en modo escritura.
        db = dbHelper.getWritableDatabase();
    }

    public int InsertarUsuario(String pNombres, String pPrimerAp, String pSegundoAp, String pUser, String pPassword, int pEstado) {
        // Ejecutamos para insertar los datos.
        int vRegistro = (int) db.insert(TABLE_NAME_USUARIO, null, AsignarUsuarioValores(pNombres, pPrimerAp, pSegundoAp, pUser, pPassword, pEstado));
        return vRegistro;
    }

    public int ActualizarUsuario(int pId, String pNombres, String pPrimerAp, String pSegundoAp, String pUser, String pPassword, int pEstado) {
        int vActualizar = db.update(TABLE_NAME_USUARIO, AsignarUsuarioValores(pNombres, pPrimerAp, pSegundoAp, pUser, pPassword, pEstado), FIELD_USUARIO_ID + " = ?", new String[]{String.valueOf(pId)});
        return vActualizar;
    }

    public boolean EliminarUsuario(int pId) {
        try {
            db.delete(TABLE_NAME_USUARIO, FIELD_USUARIO_ID + " = ?", new String[]{String.valueOf(pId)});
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public int ActualizarEstadoUsuario(int pId, int pEstado) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_USUARIO_ESTADO, pEstado);
        int vActualiza = db.update(TABLE_NAME_USUARIO, contentValues, FIELD_USUARIO_ID + "= ?", new String[]{String.valueOf(pId)});
        return vActualiza;
    }

    public List<Usuario> ObtenerUsuario() {
        List<Usuario> oListaUsuario = null;
        // Ponemos la Base de datos en modo lectura.
        db = dbHelper.getReadableDatabase();
        // Consulta Obtener todos los reqistros de Usuario.
        String vConsulta = "SELECT " + FIELD_USUARIO_ID + ", " + FIELD_USUARIO_NOMBRES + ", " + FIELD_USUARIO_PRIMER_APELLIDO + ", " + FIELD_USUARIO_SEGUNDO_APELLIDO + ", " + FIELD_USUARIO_USER + ", " + FIELD_USUARIO_PWD + ", " + FIELD_USUARIO_ESTADO + " FROM " + TABLE_NAME_USUARIO;
        // Ejecutamos la consulta.
        Cursor cursor = db.rawQuery(vConsulta, null);
        try {
            // Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                // Instanciamos un objeto de la lista de Usuarios.
                oListaUsuario = new ArrayList<Usuario>();
                //Recorremos el cursor hasta que no haya más registros
                do {
                    // Obtengo del cursor los datos.
                    Usuario oUsuario = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
                    // Adiciono a la lista el usuario.
                    oListaUsuario.add(oUsuario);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            Log.e("Base de Datos", "Error al leer la Base de Datos.");
        }
        return oListaUsuario;
    }

    public boolean VerificaExisteUsuario(String pUsuario, String pPassword) {
        // Ponemos la Base de Datos en modo lectura.
        db = dbHelper.getReadableDatabase();
        // Argumentos para la consulta.
        String arg[] = {pUsuario.toString(), pPassword.toString()};
        // Script para la consulta
        String vConsulta = "SELECT * FROM " + TABLE_NAME_USUARIO + " WHERE " + FIELD_USUARIO_USER + " = ? AND " + FIELD_USUARIO_PWD + " = ? AND " + FIELD_USUARIO_ESTADO + " = 1";
        // Ejectuta la consula.
        Cursor cursor = db.rawQuery(vConsulta, arg);
        try {
            // Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                // Existe el usuario.
                return true;
            }
            else
            {
                // No existe el usuario.
                return false;
            }

        } catch (Exception ex) {
            Log.v("Error Base de Datos", ex.toString());
            return false;
        }
    }

    public int InsertarAgenda(int pIdUsuario, String pTitulo, String pDescripcion, int pEstado) {
        int vAgenda = (int) db.insert(TABLE_NAME_AGENDA, null, AsignaAgendaValores(pIdUsuario, pTitulo, pDescripcion, pEstado));
        return vAgenda;
    }


    public ContentValues AsignarUsuarioValores(String pNombres, String pPrimerAp, String pSegundoAp, String pUser, String pPassword, int pEstado) {
        // Se declara un objeto para almacenar los valores.
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_USUARIO_NOMBRES, pNombres);
        contentValues.put(FIELD_USUARIO_PRIMER_APELLIDO, pPrimerAp);
        contentValues.put(FIELD_USUARIO_SEGUNDO_APELLIDO, pSegundoAp);
        contentValues.put(FIELD_USUARIO_USER, pUser);
        contentValues.put(FIELD_USUARIO_PWD, pPassword);
        contentValues.put(FIELD_USUARIO_ESTADO, pEstado);
        return contentValues;
    }

    public ContentValues AsignaAgendaValores(int pIdUsuario, String pTitulo, String pDescripcion, int pEstado) {
        // Se declara un objeto para almacenar valores.
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_AGENDA_ID_USUARIO, pIdUsuario);
        contentValues.put(FIELD_AGENDA_TITULO, pTitulo);
        contentValues.put(FIELD_AGENDA_DESCRIPCION, pDescripcion);
        contentValues.put(FIELD_AGENDA_ESTADO, pEstado);
        return contentValues;
    }

}
