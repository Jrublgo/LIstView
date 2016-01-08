package com.altair.jrh.listview;

/**
 * Created by Alumno on 10/11/2015.
 */
public class Inmigrantes {

   // Alt + insert
    // Control + Barra

    private String nombre;
    private int edad;
    private String nacionalidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Inmigrantes(String nombre, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    @Override
    public String toString() {
        return "[ Nombre => " + nombre + " | Edad => " + edad + " | País de Origen => " + nacionalidad + " ]";
    }
}
