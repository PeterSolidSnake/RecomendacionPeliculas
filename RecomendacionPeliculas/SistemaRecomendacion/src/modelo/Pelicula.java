package modelo;

import java.util.ArrayList;

public class Pelicula {

    private String titulo;
    private int duracion;
    private String autor;
    private int año;
    private Genero genero;
    private ArrayList<Calificacion> calificaciones;

    public Pelicula(String titulo, int duracion, String autor, int año, Genero genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.autor = autor;
        this.año = año;
        this.genero = genero;
        this.calificaciones = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getAutor() {
        return autor;
    }

    public int getAño() {
        return año;
    }

    public Genero getGenero() {
        return genero;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void agregarCalificacion(Calificacion c) {
        calificaciones.add(c);
    }

    public double getCalificacionPromedio() {
        if (calificaciones.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Calificacion c : calificaciones) {
            suma += c.getPuntuacion();
        }
        return suma / calificaciones.size();
    }

    @Override
    public String toString() {
        return titulo;
    }
}
