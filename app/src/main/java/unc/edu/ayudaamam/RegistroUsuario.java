package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import unc.edu.ayudaamam.Data.MantenimientoReceta;
import unc.edu.ayudaamam.Data.MantenimientoUsuario;
import unc.edu.ayudaamam.Model.Usuario;

public class RegistroUsuario extends AppCompatActivity {
    EditText txtNombre, txtUsuario, txtClave;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_registro_usuario);
        txtNombre = findViewById(R.id.txtNombreReg);
        txtUsuario = findViewById(R.id.txtUsuarioReg);
        txtClave = findViewById(R.id.txtClaveReg);
        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MantenimientoUsuario oMantUsuario = new MantenimientoUsuario();

                String nombre = txtNombre.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String clave = txtClave.getText().toString();

                Usuario oUsuario = new Usuario(nombre,usuario,clave);

                if(oMantUsuario.AgregarUsuario(RegistroUsuario.this,oUsuario))
                    Toast.makeText(RegistroUsuario.this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegistroUsuario.this,"El Registro Falló",Toast.LENGTH_LONG).show();
                Intent oIntento = new Intent(RegistroUsuario.this,IniciarSesion.class);
                startActivity(oIntento);
            }
        });

    }
}