package unc.edu.ayudaamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import unc.edu.ayudaamam.Data.MantenimientoReceta;
import unc.edu.ayudaamam.Model.Receta;

public class RecetaDetallada extends AppCompatActivity {
    ListView lvIngredientes, lvPreparación;
    TextView lvNomReceta;
    int recetaSelecionada;
    MantenimientoReceta oMantReceta = new MantenimientoReceta();
    ArrayList<String> ingredientes = new ArrayList<>();
    ArrayList<String> pasos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_lista_detallada);
        lvIngredientes = findViewById(R.id.lvIngredientes);
        lvPreparación = findViewById(R.id.lvPreparacion);
        lvNomReceta = findViewById(R.id.txtNomReceta);


        recetaSelecionada = getIntent().getExtras().getInt("receta");
        oMantReceta.CargarListaReceta(RecetaDetallada.this);
        Receta oReceta = (Receta) oMantReceta.getArray().get(recetaSelecionada);
        ingredientes.add(oReceta.getIngredientes());
        pasos.add(oReceta.getPasos());

        lvNomReceta.setText(lvNomReceta.getText()+""+oReceta.getNombre());
        lvIngredientes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ingredientes));
        lvPreparación.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,pasos));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_editar_eliminar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent oIntento = null;
        switch (item.getItemId()){
            case R.id.ItemEditar:
                oIntento = new Intent(this, EditarReceta.class);
                oIntento.putExtra("receta",recetaSelecionada);
                startActivity(oIntento);
                break;
            case R.id.ItemEliminar:
                if(oMantReceta.Eliminar(RecetaDetallada.this,recetaSelecionada))
                    Toast.makeText(RecetaDetallada.this,"La receta fue eliminada",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RecetaDetallada.this,"No se elimino la Receta",Toast.LENGTH_LONG).show();
                oIntento = new Intent(this,ListaRecetas.class);
                startActivity(oIntento);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}