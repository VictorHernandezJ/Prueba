package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import unc.edu.ayudaamam.Data.MantenimientoReceta;
import unc.edu.ayudaamam.Model.Receta;

public class AgregarReceta extends AppCompatActivity {
    EditText txtNombre, txtIngredientes,txtTiempo, txtPasos;
    Spinner spDifiultad, spTipo;
    Button btnAgregar;
    String [] dificultades ={"Fácil","Medio","Difícil"};
    String [] tipoReceta ={"Carne","Pollo","Bebidas"};

    int indDificultad = 0, indTipo= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_agregar_receta);
        txtNombre = findViewById(R.id.txtNombreAgregar);
        txtIngredientes = findViewById(R.id.txtIngredientes);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPasos = findViewById(R.id.txtPasos);
        btnAgregar = findViewById(R.id.btnGuardar);
        spDifiultad = findViewById(R.id.spDificultad);
        spTipo = findViewById(R.id.spTipo);

        spDifiultad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dificultades));
        spDifiultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indDificultad=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tipoReceta));
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indTipo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(txtNombre.getText().equals("") || txtTiempo.getText().equals("")){

               }else{
                   String nombre = txtNombre.getText().toString();
                   String ingredientes = txtIngredientes.getText().toString();
                   String tiempo = txtTiempo.getText().toString();
                   String pasos = txtPasos.getText().toString();
                   String dificultad = dificultades[indDificultad];
                   String tipo = tipoReceta[indTipo];

                   Receta oReceta = new Receta(nombre,ingredientes,tiempo,pasos,dificultad,tipo);

                   MantenimientoReceta oManReceta = new MantenimientoReceta();
                   if(oManReceta.Agregar(AgregarReceta.this,oReceta))
                       Toast.makeText(AgregarReceta.this,"Registro Correcto",Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(AgregarReceta.this,"Registro Incorrecto",Toast.LENGTH_SHORT).show();
                   Limpiar();
               }
            }
        });
    }

    public void Limpiar(){
        txtNombre.setText("");
        txtIngredientes.setText("");
        txtTiempo.setText("");
        txtPasos.setText("");
        spTipo.setSelection(0);
        spDifiultad.setSelection(0);
    }
}