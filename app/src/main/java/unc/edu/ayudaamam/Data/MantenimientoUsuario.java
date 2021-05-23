package unc.edu.ayudaamam.Data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import unc.edu.ayudaamam.Model.Receta;
import unc.edu.ayudaamam.Model.Usuario;

public class MantenimientoUsuario {
    String nombreBD;
    ArrayList<Usuario> oListaUsuario;

    public MantenimientoUsuario() {
        this.nombreBD = "BDUsuario";
        oListaUsuario = new ArrayList<>();
    }

    public boolean AgregarUsuario(Activity oActividad, Usuario oUsuario){
        boolean rpta = false;
        GestionBD oBDHelper = new GestionBD(oActividad,nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        if(oBD != null){
            //definir un objeto para las columnas de la tabla a utilizar en a base de datos
            ContentValues oColumnas = new ContentValues();
            oColumnas.put("nombre",oUsuario.getNombre());
            oColumnas.put("user",oUsuario.getUser());
            oColumnas.put("clave",oUsuario.getClave());
            //agregando a la tabla
            long fila = oBD.insert("Usuario",null,oColumnas);
            if(fila > 0)
                rpta = true;
        }
        return rpta;
    }

    public boolean VerificarUsuario(String users, String key){
        boolean rpta = false;
        for (Usuario oU:oListaUsuario)
            if(oU.getUser().equals(users) && oU.getClave().equals(key))
                rpta = true;
        return rpta;
    }
    public void CargarDatos(Activity oActividad){
        GestionBD oBDHelper = new GestionBD(oActividad, nombreBD,null,1);
        SQLiteDatabase oBD = oBDHelper.getWritableDatabase();
        Cursor oRegsitro = oBD.rawQuery("SELECT * FROM Usuario",null);
        if(oRegsitro.moveToFirst()){
            do{
                String nombre = oRegsitro.getString(0);
                String usuario = oRegsitro.getString(1);
                String clave = oRegsitro.getString(2);
                Usuario oUser = new Usuario(nombre,usuario,clave);
                oListaUsuario.add(oUser);
            }while(oRegsitro.moveToNext());
            oRegsitro.close();
        }
        oBD.close();
    }
}
