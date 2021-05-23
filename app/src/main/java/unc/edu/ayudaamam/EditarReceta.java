package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.LimitExceededException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import unc.edu.ayudaamam.Data.MantenimientoReceta;
import unc.edu.ayudaamam.Model.Receta;

public class EditarReceta extends AppCompatActivity {
    EditText txtNombre, txtIngredientes,txtTiempo, txtPasos;
    Spinner spDifiultad, spTipo;
    TextView txtTitulo;
    Button btnEditar;
    String [] dificultades ={"Fácil","Medio","Difícil"};
    String [] tipoReceta ={"Carne","Pollo","Bebidas"};
    int indDificultad = 0, indTipo= 0;
    int recetaSelecionada;
    MantenimientoReceta oMantReceta = new MantenimientoReceta();
    String condicion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_editar_receta);
        txtNombre = findViewById(R.id.txtNombreEdit);
        txtIngredientes = findViewById(R.id.txtIngredientesEdit);
        txtTiempo = findViewById(R.id.txtTiempoEdit);
        txtPasos = findViewById(R.id.txtPasosEdit);
        txtTitulo = findViewById(R.id.txtTitulo);
        btnEditar = findViewById(R.id.btnEditar);
        spDifiultad = findViewById(R.id.spDificultadEdit);
        spTipo = findViewById(R.id.spTipoEdit);

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

        //mostrar datos de la receta a modificar
        recetaSelecionada = getIntent().getExtras().getInt("receta");
        oMantReceta.CargarListaReceta(EditarReceta.this);
        Receta oReceta = (Receta) oMantReceta.getArray().get(recetaSelecionada);
        condicion = oReceta.getNombre();
        txtTitulo.setText(txtTitulo.getText()+""+oReceta.getNombre());
        txtNombre.setText(oReceta.getNombre());
        txtIngredientes.setText(oReceta.getIngredientes());
        txtTiempo.setText(oReceta.getTiempo());
        txtPasos.setText(oReceta.getPasos());
        spDifiultad.setSelection(calcularIndice(oReceta.getDificultad()));
        spTipo.setSelection(calcularIndice(oReceta.getTipo()));


       btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String ingredientes = txtIngredientes.getText().toString();
                String tiempo = txtTiempo.getText().toString();
                String pasos = txtPasos.getText().toString();
                String dificultad = dificultades[indDificultad];
                String tipo = tipoReceta[indTipo];

                Receta oRecActulizada = new Receta(nombre,ingredientes,tiempo,pasos,dificultad,tipo);
                if(oMantReceta.Actualizar(EditarReceta.this,oRecActulizada, condicion))
                    Toast.makeText(EditarReceta.this,"Registro Correcto",Toast.LENGTH_SHORT).show();
                //comentario
                else
                    Toast.makeText(EditarReceta.this,"Registro Incorrecto",Toast.LENGTH_SHORT).show();
                Limpiar();
            }
        });



    }
    public int calcularIndice(String buscar){
        int indice = -1;
        if(buscar.equals("Fácil"))
            indice = 0;
        if(buscar.equals("Medio"))
            indice = 1;
        if(buscar.equals("Difícil"))
            indice = 2;
        if(buscar.equals("Carne"))
            indice = 0;
        if(buscar.equals("Pollo"))
            indice = 1;
        if(buscar.equals("Bebidas"))
            indice = 2;
        return indice;
    }

    public void Limpiar(){
        txtNombre.setText("");
        txtIngredientes.setText("");
        txtTiempo.setText("");
        txtPasos.setText("");
        spDifiultad.setSelection(0);
        spTipo.setSelection(0);
    }
}