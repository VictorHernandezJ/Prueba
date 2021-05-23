package unc.edu.ayudaamam.Data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import unc.edu.ayudaamam.Model.Receta;

public class MantenimientoReceta {
    String nombreBD;
    ArrayList<Receta> olistaReceta;

    public MantenimientoReceta(){
        this.nombreBD="BDReceta";
        olistaReceta = new ArrayList<>();
    }

    public ArrayList getArray(){
        return olistaReceta;
    }

    public boolean Agregar(Activity oActividad, Receta oReceta){
        boolean rpta = false;
        // objeto para crear la base de datos
        GestionBD oBDHelper = new GestionBD(oActividad,nombreBD,null,1);
        // para utilizar la base de datos
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        if(oBD != null){
            //definir un objeto para las columnas de la tabla a utilizar en a base de datos
            ContentValues oColumnas = new ContentValues();
            oColumnas.put("nombre",oReceta.getNombre());
            oColumnas.put("ingredientes",oReceta.getIngredientes());
            oColumnas.put("tiempo",oReceta.getTiempo());
            oColumnas.put("pasos",oReceta.getPasos());
            oColumnas.put("dificultad",oReceta.getDificultad());
            oColumnas.put("tipo",oReceta.getTipo());
            //agregando a la tabla
            long fila = oBD.insert("Receta",null,oColumnas);
            if(fila > 0)
                rpta = true;
        }
        return rpta;
    }

    public void CargarListaReceta(Activity oActividad){
        GestionBD oBDHelper = new GestionBD(oActividad, nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        Cursor oRegistro = oBD.rawQuery("SELECT * FROM Receta",null);
        if(oRegistro.moveToFirst()){
            do{
                String nombre = oRegistro.getString(0);
                String ingredientes = oRegistro.getString(1);
                String tiempo = oRegistro.getString(2);
                String pasos = oRegistro.getString(3);
                String dificultad = oRegistro.getString(4);
                String tipo = oRegistro.getString(5);
                Receta oReceta = new Receta(nombre,ingredientes,tiempo,pasos,dificultad,tipo);
                olistaReceta.add(oReceta);
            }while(oRegistro.moveToNext());
            oRegistro.close();
        }
        oBD.close();
    }
    public ArrayList<String> mostrarListaRecetas(){
        ArrayList<String> lista = new ArrayList<>();
        for (Receta oP: olistaReceta){
            lista.add(oP.getNombre()+"\t Tiempo: "+oP.getTiempo()+"\t Dificultad: "+oP.getDificultad());
        }
        return lista;
    }

    public boolean Actualizar(Activity oActividad, Receta oReceta, String condicion){
        GestionBD oBDHelper = new GestionBD(oActividad,nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre",oReceta.getNombre());
        cv.put("ingredientes",oReceta.getIngredientes());
        cv.put("tiempo",oReceta.getTiempo());
        cv.put("pasos",oReceta.getPasos());
        cv.put("dificultad",oReceta.getDificultad());
        cv.put("tipo",oReceta.getTipo());
        String [] arg = {condicion};
        oBD.update("Receta",cv,"nombre=?", arg);
        oBD.close();
        return true;
    }

    public boolean Eliminar(Activity oActividad, int posicion){
        boolean rpta = false;
        GestionBD oBDHelper = new GestionBD(oActividad,nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        String [] arg ={olistaReceta.get(posicion).getNombre()};
        int fila = oBD.delete("Receta","nombre=?",arg);
        if(fila>0)
            rpta = true;
        oBD.close();
        return rpta;
    }

    public boolean Buscar(Activity oActividad, String buscar){
        boolean rpta = false;
        GestionBD oBDHelper = new GestionBD(oActividad, nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        Cursor oRegistro = oBD.rawQuery("SELECT * FROM Receta where nombre like'%"+buscar+"%'",null);
        if(oRegistro.moveToFirst()){
            rpta = true;
            do{
                String nombre = oRegistro.getString(0);
                String ingredientes = oRegistro.getString(1);
                String tiempo = oRegistro.getString(2);
                String pasos = oRegistro.getString(3);
                String dificultad = oRegistro.getString(4);
                String tipo = oRegistro.getString(5);
                Receta oReceta = new Receta(nombre,ingredientes,tiempo,pasos,dificultad,tipo);
                olistaReceta.add(oReceta);
            }while(oRegistro.moveToNext());
            oRegistro.close();
        }
        oBD.close();
        return rpta;
    }
}
