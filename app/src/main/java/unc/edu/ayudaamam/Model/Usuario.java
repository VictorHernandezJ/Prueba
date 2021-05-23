package unc.edu.ayudaamam.Model;

public class Usuario {
    private String nombre;
    private String user;
    private String clave;

    public Usuario(String nombre, String user, String clave) {
        this.nombre = nombre;
        this.user = user;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUser() {
        return user;
    }

    public String getClave() {
        return clave;
    }
}
