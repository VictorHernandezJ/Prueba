package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import unc.edu.ayudaamam.Data.MantenimientoReceta;

public class ListaRecetas extends AppCompatActivity {
    ListView lvLista;
    int recetaSelecionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_lista_recetas);
        lvLista = findViewById(R.id.lvLista);
        MantenimientoReceta oManReceta = new MantenimientoReceta();
        oManReceta.CargarListaReceta(ListaRecetas.this);

        lvLista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,oManReceta.mostrarListaRecetas()));

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