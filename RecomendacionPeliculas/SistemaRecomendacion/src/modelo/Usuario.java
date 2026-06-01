package modelo;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private ArrayList<Preferencia> preferencias;
    private ArrayList<HistorialPelicula> historial;
    private ArrayList<Calificacion> calificaciones;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.preferencias = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Preferencia> getPreferencias() {
        return preferencias;
    }

    public ArrayList<HistorialPelicula> getHistorial() {
        return historial;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void agregarPreferencia(Preferencia p) {
        preferencias.add(p);
    }

    public void agregarHistorial(HistorialPelicula h) {
        historial.add(h);
    }

    public void calificarPelicula(Calificacion c) {
        calificaciones.add(c);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
