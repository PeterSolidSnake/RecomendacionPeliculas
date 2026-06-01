package modelo;

import java.util.ArrayList;

public class AlgoritmoRecomendacion {

    public ArrayList<Pelicula> generarRecomendacion(Usuario usuario,
            ArrayList<Pelicula> todasLasPeliculas) {
        ArrayList<Pelicula> recomendadas = new ArrayList<>();

        for (Preferencia pref : usuario.getPreferencias()) {
            for (Pelicula p : todasLasPeliculas) {
                if (p.getGenero().getNombre()
                        .equals(pref.getGeneroPreferido().getNombre())) {

                    // Verifica que no la haya visto ya
                    boolean yaVista = false;
                    for (HistorialPelicula h : usuario.getHistorial()) {
                        if (h.getPelicula().getTitulo().equals(p.getTitulo())) {
                            yaVista = true;
                            break;
                        }
                    }
                    if (!yaVista) {
                        recomendadas.add(p);
                    }
                }
            }
        }
        return recomendadas;
    }
}
