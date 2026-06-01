package modelo;

public class HistorialPelicula {

    private Fecha fechaVista;
    private int tiempoVisto;
    private Pelicula pelicula;

    public HistorialPelicula(Fecha fechaVista, int tiempoVisto, Pelicula pelicula) {
        this.fechaVista = fechaVista;
        this.tiempoVisto = tiempoVisto;
        this.pelicula = pelicula;
    }

    public Fecha getFechaVista() {
        return fechaVista;
    }

    public void setFechaVista(Fecha fechaVista) {
        this.fechaVista = fechaVista;
    }

    public int getTiempoVisto() {
        return tiempoVisto;
    }

    public void setTiempoVisto(int tiempoVisto) {
        this.tiempoVisto = tiempoVisto;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
