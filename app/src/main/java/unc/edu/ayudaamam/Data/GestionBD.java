package unc.edu.ayudaamam.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestionBD extends SQLiteOpenHelper{
    String tabla_receta = "CREATE TABLE Receta(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ingredientes TEXT, tiempo TEXT, pasos TEXT, dificultad TEXT, tipo TEXT)";

    public GestionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL(tabla_receta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {
        BD.execSQL("DROP TABLE IF EXISTS Receta");
        BD.execSQL(tabla_receta);
    }
}
