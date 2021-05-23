package unc.edu.ayudaamam.Model;

public class Receta {
    private String nombre;
    private String ingredientes;
    private String tiempo;
    private String pasos;
    private String dificultad;
    private String tipo;

    public Receta(String nombre, String ingredientes, String tiempo, String pasos, String dificultad, String tipo) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tiempo = tiempo;
        this.pasos = pasos;
        this.dificultad = dificultad;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getPasos() {
        return pasos;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getTipo() {
        return tipo;
    }

    public String toString(){
        return nombre;
    }
}
