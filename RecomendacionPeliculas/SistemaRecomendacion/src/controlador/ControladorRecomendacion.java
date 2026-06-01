package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.AlgoritmoRecomendacion;
import modelo.Genero;
import modelo.Pelicula;
import modelo.Preferencia;
import modelo.SistemaRecomendacion;
import modelo.Usuario;
import vista.JFRecomendacion;

public class ControladorRecomendacion implements ActionListener {

    private JFRecomendacion frmRecomendacion;
    private SistemaRecomendacion sistema;
    private AlgoritmoRecomendacion algoritmo;

    public ControladorRecomendacion(JFRecomendacion frmRecomendacion) {
        this.frmRecomendacion = frmRecomendacion;
        this.sistema = new SistemaRecomendacion();
        this.algoritmo = new AlgoritmoRecomendacion();

        frmRecomendacion.btnRegistrarPelicula.addActionListener(this);
        frmRecomendacion.btnRegistrarUsuario.addActionListener(this);
        frmRecomendacion.btnBuscar.addActionListener(this);
        frmRecomendacion.btnRecomendar.addActionListener(this);
        frmRecomendacion.btnLimpiar.addActionListener(this);
        
        cargarPeliculasEjemplo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmRecomendacion.btnRegistrarPelicula) {
            registrarPelicula();
        }
        if (e.getSource() == frmRecomendacion.btnRegistrarUsuario) {
            registrarUsuario();
        }
        if (e.getSource() == frmRecomendacion.btnBuscar) {
            buscar();
        }
        if (e.getSource() == frmRecomendacion.btnRecomendar) {
            recomendar();
        }
        if (e.getSource() == frmRecomendacion.btnLimpiar) {
            limpiar();
        }
    }

    private void registrarPelicula() {
        String titulo = frmRecomendacion.txtTitulo.getText();
        String duracionTxt = frmRecomendacion.txtDuracion.getText();
        String autor = frmRecomendacion.txtAutor.getText();
        String añoTxt = frmRecomendacion.txtAño.getText();
        String generoNombre = (String) frmRecomendacion.cmbGenero.getSelectedItem();

        if (titulo.isEmpty() || duracionTxt.isEmpty() || autor.isEmpty() || añoTxt.isEmpty()) {
            frmRecomendacion.txtAreaResultados.setText("Por favor complete todos los campos.");
            return;
        }

        int duracion = Integer.parseInt(duracionTxt);
        int año = Integer.parseInt(añoTxt);
        Genero genero = new Genero(generoNombre);
        Pelicula pelicula = new Pelicula(titulo, duracion, autor, año, genero);

        sistema.registrarPelicula(pelicula);
        frmRecomendacion.txtAreaResultados.setText("Película registrada: " + titulo);
        limpiar();
    }

    private void registrarUsuario() {
        String nombre = frmRecomendacion.txtNombreUsuario.getText();
        String generoNombre = (String) frmRecomendacion.cmbGeneroPreferido.getSelectedItem();

        if (nombre.isEmpty()) {
            frmRecomendacion.txtAreaResultados.setText("Por favor ingrese el nombre del usuario.");
            return;
        }

        Usuario usuario = new Usuario(nombre);
        Genero genero = new Genero(generoNombre);
        Preferencia preferencia = new Preferencia(genero);
        usuario.agregarPreferencia(preferencia);

        sistema.registrarUsuario(usuario);
        frmRecomendacion.txtAreaResultados.setText("Usuario registrado: " + nombre
                + "\nGénero preferido: " + generoNombre);
        limpiar();
    }

    private void buscar() {
        String texto = frmRecomendacion.txtBuscar.getText();
        String tipoBusqueda = (String) frmRecomendacion.cmbTipoBusqueda.getSelectedItem();

        if (texto.isEmpty()) {
            frmRecomendacion.txtAreaResultados.setText("Por favor ingrese un término de búsqueda.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (tipoBusqueda.equals("Por Título")) {
            Pelicula p = sistema.busquedaByTittle(texto);
            if (p != null) {
                sb.append("Película encontrada:\n");
                sb.append("Título: ").append(p.getTitulo()).append("\n");
                sb.append("Director: ").append(p.getAutor()).append("\n");
                sb.append("Género: ").append(p.getGenero().getNombre()).append("\n");
                sb.append("Año: ").append(p.getAño()).append("\n");
                sb.append("Duración: ").append(p.getDuracion()).append(" min\n");
            } else {
                sb.append("No se encontró ninguna película con ese título.");
            }
        } else if (tipoBusqueda.equals("Por Género")) {
            ArrayList<Pelicula> lista = sistema.busquedaByGenre(texto);
            if (lista.isEmpty()) {
                sb.append("No se encontraron películas de ese género.");
            } else {
                sb.append("Películas encontradas:\n");
                for (Pelicula p : lista) {
                    sb.append("- ").append(p.getTitulo())
                            .append(" (").append(p.getAño()).append(")\n");
                }
            }
        } else if (tipoBusqueda.equals("Por Director")) {
            ArrayList<Pelicula> lista = sistema.busquedaByAuthor(texto);
            if (lista.isEmpty()) {
                sb.append("No se encontraron películas de ese director.");
            } else {
                sb.append("Películas encontradas:\n");
                for (Pelicula p : lista) {
                    sb.append("- ").append(p.getTitulo())
                            .append(" (").append(p.getAño()).append(")\n");
                }
            }
        }

        frmRecomendacion.txtAreaResultados.setText(sb.toString());
    }

    private void recomendar() {
        String nombreUsuario = frmRecomendacion.txtNombreUsuario.getText();

        if (nombreUsuario.isEmpty()) {
            frmRecomendacion.txtAreaResultados.setText("Ingrese el nombre del usuario para recomendar.");
            return;
        }

        // Busca el usuario en la lista
        Usuario usuarioEncontrado = null;
        for (Usuario u : sistema.getUsuarios()) {
            if (u.getNombre().equalsIgnoreCase(nombreUsuario)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            frmRecomendacion.txtAreaResultados.setText("No se encontró el usuario: " + nombreUsuario);
            return;
        }

        ArrayList<Pelicula> recomendadas = algoritmo.generarRecomendacion(
                usuarioEncontrado, sistema.getPeliculas());

        if (recomendadas.isEmpty()) {
            frmRecomendacion.txtAreaResultados.setText("No hay recomendaciones para " + nombreUsuario);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Recomendaciones para ").append(nombreUsuario).append(":\n\n");
        for (Pelicula p : recomendadas) {
            sb.append("- ").append(p.getTitulo())
                    .append(" | ").append(p.getGenero().getNombre())
                    .append(" | ").append(p.getAño()).append("\n");
        }

        frmRecomendacion.txtAreaResultados.setText(sb.toString());
    }

    private void limpiar() {
        frmRecomendacion.txtTitulo.setText("");
        frmRecomendacion.txtDuracion.setText("");
        frmRecomendacion.txtAutor.setText("");
        frmRecomendacion.txtAño.setText("");
        frmRecomendacion.txtNombreUsuario.setText("");
        frmRecomendacion.txtBuscar.setText("");
    }

    // Se cargan películas de ejemplo para probar
    private void cargarPeliculasEjemplo() {
        sistema.registrarPelicula(new Pelicula("Avengers", 180, "Russo", 2019, new Genero("Acción")));
        sistema.registrarPelicula(new Pelicula("Titanic", 195, "Cameron", 1997, new Genero("Romance")));
        sistema.registrarPelicula(new Pelicula("It", 135, "Muschietti", 2017, new Genero("Terror")));
        sistema.registrarPelicula(new Pelicula("Toy Story", 81, "Lasseter", 1995, new Genero("Animación")));
        sistema.registrarPelicula(new Pelicula("Interstellar", 169, "Nolan", 2014, new Genero("Ciencia Ficción")));
    }
}
