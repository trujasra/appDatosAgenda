package com.mundotrujas.appdatosagenda;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mundotrujas.appdatosagenda.DatosAgenda.Datos.AgendaAdministracion;
import com.mundotrujas.appdatosagenda.DatosAgenda.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegistroUsuarioActivity extends AppCompatActivity {
    private EditText edNombre, edPrimerApellido, edSegundoApellido, edPassword, edRepPassword;
    private Button btnGuardar, btnLimpiar, btnObtener;

    private TextInputLayout tilNombres, tilPrimerApellido, tilSegundoApellido, tilPassword, tilRepPassword;

    AgendaAdministracion agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        // enlazamos los view.
        edNombre = (EditText) findViewById(R.id.edNombre);
        /*edPrimerApellido = (EditText) findViewById(R.id.edPrimerA);
        edSegundoApellido = (EditText) findViewById(R.id.edSegundoA);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edRepPassword = (EditText) findViewById(R.id.edRepPassword);*/
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnObtener = (Button) findViewById(R.id.btnObte);

        // enlazamos los TextInputLayout.
        tilNombres = (TextInputLayout) findViewById(R.id.tilNombre);
        /*tilPrimerApellido = (TextInputLayout) findViewById(R.id.tilPrimerApellido);
        tilSegundoApellido = (TextInputLayout) findViewById(R.id.tilSegundoApellido);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilRepPassword = (TextInputLayout) findViewById(R.id.tilRepPassword);*/

        try {
            // Instanciamos un Objeto para crear la BD y las Tablas.
            agenda = new AgendaAdministracion(this);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int vEstado = 1; // Eatado Activo.
                    int vRegistro = agenda.InsertarUsuario(edNombre.getText().toString(), edPrimerApellido.getText().toString(), edSegundoApellido.getText().toString(), "", edPassword.getText().toString(), vEstado);
                    // Verifica que se regsitro dato.
                    if (vRegistro > 0) {
                        Toast.makeText(getApplicationContext(), "Se adiciono correctamente el registro " + String.valueOf(vRegistro), Toast.LENGTH_SHORT).show();
                        LimpiarCampos();
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //region Validación de campos
    public boolean ValidarCampos() {
        if (esNombreValido(edNombre.getText().toString()))
            return true;
        else
            return false;
    }

    private boolean esNombreValido(String pNombre) {
        // Adiciono la expresion regular
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        // Se verifica si la cadena
        if (!patron.matcher(pNombre).matches() || pNombre.length() > 30) {
            try {
                tilNombres.setError("El nombre es inválido");
            }
            catch (Exception ex){
                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            return false;
        } else {
            // limpia el error.
            tilNombres.setError(null);
        }
        return true;
    }
    //endregion

    public void LimpiarCampos() {
        edNombre.setText("");
        edPrimerApellido.setText("");
        edSegundoApellido.setText("");
        edPassword.setText("");
        edRepPassword.setText("");
        edNombre.setFocusable(true);
    }


    public void ObtenerDatos(View v) {
        ValidarCampos();

        //List<Usuario> vListaUsuario = agenda.ObtenerUsuario();

        //boolean pVerifica = agenda.VerificaExisteUsuario(edSegundoApellido.getText().toString(), edPassword.getText().toString());
        //Toast.makeText(getApplicationContext(), String.valueOf(pVerifica), Toast.LENGTH_SHORT).show();

    }
}
