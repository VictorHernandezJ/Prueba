package unc.edu.ayudaamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent oIntento = null;
        switch (item.getItemId()){
            case R.id.ItemAgregar:
                oIntento = new Intent(this, AgregarReceta.class);
                startActivity(oIntento);
                break;
            case R.id.ItemLista:
                oIntento = new Intent(this, ListaRecetas.class);
                startActivity(oIntento);
                break;
            case R.id.ItemCerrarSesion:
                oIntento = new Intent(this, IniciarSesion.class);
                startActivity(oIntento);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}