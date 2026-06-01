package modelo;

import java.util.ArrayList;

public class SistemaRecomendacion {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Pelicula> peliculas;

    public SistemaRecomendacion() {
        this.usuarios = new ArrayList<>();
        this.peliculas = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void registrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void registrarPelicula(Pelicula p) {
        peliculas.add(p);
    }

    public Pelicula busquedaByTittle(String titulo) {
        for (Pelicula p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Pelicula> busquedaByGenre(String genero) {
        ArrayList<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getGenero().getNombre().equalsIgnoreCase(genero)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public ArrayList<Pelicula> busquedaByAuthor(String autor) {
        ArrayList<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
