package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import unc.edu.ayudaamam.Data.MantenimientoReceta;

public class ListaRecetas extends AppCompatActivity {
    ListView lvLista;
    int recetaSelecionada;
    ImageButton btnBuscar;
    EditText txtBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_lista_recetas);
        lvLista = findViewById(R.id.lvLista);
        txtBuscar = findViewById(R.id.txtBuscar);
        btnBuscar = findViewById(R.id.imgbtnBuscar);
        MantenimientoReceta oManReceta = new MantenimientoReceta();
        oManReceta.CargarListaReceta(ListaRecetas.this);

        lvLista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, oManReceta.mostrarListaRecetas()));

        /*btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oManReceta.Buscar(ListaRecetas.this,txtBuscar.getText().toString())){
                    lvLista.setAdapter(new ArrayAdapter<String>(ListaRecetas.this, android.R.layout.simple_list_item_1, oManReceta.mostrarListaRecetas()));
                }
                else
                    Toast.makeText(ListaRecetas.this, "NO encontró registros",Toast.LENGTH_SHORT).show();
            }
        });*/

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recetaSelecionada = position;
                Intent oInteto = new Intent(ListaRecetas.this,RecetaDetallada.class);
                oInteto.putExtra("receta",recetaSelecionada);
                startActivity(oInteto);
            }
        });
    }

}