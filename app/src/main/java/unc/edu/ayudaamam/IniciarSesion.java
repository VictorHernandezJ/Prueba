package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import unc.edu.ayudaamam.Data.MantenimientoUsuario;

public class IniciarSesion extends AppCompatActivity {
    EditText txtUsuario, txtClave;
    Button btnIniciar, btnRegistrar;
    MantenimientoUsuario oMantUsuario = new MantenimientoUsuario();
    Intent oIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_iniciar_sesion);

        oMantUsuario.CargarDatos(IniciarSesion.this);
        txtUsuario = findViewById(R.id.txtUsuarioIni);
        txtClave = findViewById(R.id.txtClaveIni);
        btnIniciar= findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnCrearUsuario);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String clave = txtClave.getText().toString();

                if(oMantUsuario.VerificarUsuario(usuario,clave)){
                    oIntent = new Intent(IniciarSesion.this,MainActivity.class);
                    startActivity(oIntent);
                }else
                    Toast.makeText(IniciarSesion.this,"No existe el Usuario",Toast.LENGTH_LONG).show();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oIntent = new Intent(IniciarSesion.this,RegistroUsuario.class);
                startActivity(oIntent);
            }
        });
    }





}