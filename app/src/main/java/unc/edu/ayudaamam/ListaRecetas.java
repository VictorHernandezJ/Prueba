package unc.edu.ayudaamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    EditText txtBuscar;
    ArrayAdapter<String> adapter;
    ArrayList<String> listaReceta;
    MantenimientoReceta oManReceta = new MantenimientoReceta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_lista_recetas);
        lvLista = findViewById(R.id.lvLista);
        txtBuscar = findViewById(R.id.txtBuscar);
        oManReceta.CargarListaReceta(ListaRecetas.this);
        listaReceta = oManReceta.mostrarListaRecetas();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaReceta);

        lvLista.setAdapter(adapter);
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String comparar = adapter.getItem(position);
                int index = calcularIndice(listaReceta,comparar);
                Intent oInteto = new Intent(ListaRecetas.this,RecetaDetallada.class);
                oInteto.putExtra("receta",index);
                startActivity(oInteto);
            }
        });
    }

    public int calcularIndice(ArrayList<String> lista, String mensaje){
        int j=0;
        for(int i=0; i<lista.size();i++){
            if(lista.get(i).toString().equals(mensaje)) {
                j=i;
                break;
            }
        }
        return j;
    }

}