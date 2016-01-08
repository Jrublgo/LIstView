package com.altair.jrh.listview;

/**
 * Created by Alumno on 19/11/2015.
 */
public class Nacionalidad {

    private int idNacionalidad;
    private String nacion;


    public Nacionalidad(int idNacionalidad, String nacion) {
        this.idNacionalidad = idNacionalidad;
        this.nacion = nacion;
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(int idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }


    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    @Override
    public String toString() {
        return nacion;
    }
}
