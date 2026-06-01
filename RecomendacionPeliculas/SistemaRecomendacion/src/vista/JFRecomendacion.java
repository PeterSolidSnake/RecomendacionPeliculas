package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class JFRecomendacion extends JFrame {

    // ── Campos de Película ──────────────────────────────────────────────────
    public JTextField txtTitulo;
    public JTextField txtDuracion;
    public JTextField txtAutor;
    public JTextField txtAño;
    public JComboBox<String> cmbGenero;

    // ── Campos de Usuario ───────────────────────────────────────────────────
    public JTextField txtNombreUsuario;
    public JComboBox<String> cmbGeneroPreferido;

    // ── Búsqueda ────────────────────────────────────────────────────────────
    public JTextField txtBuscar;
    public JComboBox<String> cmbTipoBusqueda;

    // ── Botones ─────────────────────────────────────────────────────────────
    public JButton btnRegistrarPelicula;
    public JButton btnRegistrarUsuario;
    public JButton btnBuscar;
    public JButton btnRecomendar;
    public JButton btnLimpiar;

    // ── Área de resultados ──────────────────────────────────────────────────
    public JTextArea txtAreaResultados;

    // Géneros disponibles
    private static final String[] GENEROS = {
        "Acción", "Animación", "Ciencia Ficción", "Comedia",
        "Drama", "Romance", "Terror", "Thriller"
    };

    public JFRecomendacion() {
        initComponents();
        setTitle("Sistema de Recomendación de Películas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 620);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        // Panel principal con padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(12, 14, 12, 14));
        mainPanel.setBackground(new Color(245, 245, 245));

        // ── Título superior ─────────────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Sistema de Recomendación de Películas");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(new EmptyBorder(0, 0, 8, 0));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // ── Panel central (izquierda: formularios | derecha: resultados) ────
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        centerPanel.setOpaque(false);

        // Panel izquierdo con los tres paneles apilados
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        leftPanel.add(buildPanelPelicula());
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(buildPanelUsuario());
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(buildPanelBusqueda());

        centerPanel.add(leftPanel);
        centerPanel.add(buildPanelResultados());

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ── Barra de botones inferior ────────────────────────────────────────
        mainPanel.add(buildPanelBotones(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // ── Panel: Registrar Película ────────────────────────────────────────────
    private JPanel buildPanelPelicula() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(titledBorder("Registrar Película"));

        GridBagConstraints gbc = defaultGbc();

        txtTitulo   = new JTextField();
        txtDuracion = new JTextField();
        txtAutor    = new JTextField();
        txtAño      = new JTextField();
        cmbGenero   = new JComboBox<>(GENEROS);

        agregarFila(panel, gbc, 0, "Título:",    txtTitulo);
        agregarFila(panel, gbc, 1, "Duración:",  txtDuracion);
        agregarFila(panel, gbc, 2, "Director:",  txtAutor);
        agregarFila(panel, gbc, 3, "Año:",       txtAño);
        agregarFila(panel, gbc, 4, "Género:",    cmbGenero);

        btnRegistrarPelicula = boton("Registrar Película");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 4, 4, 4);
        panel.add(btnRegistrarPelicula, gbc);

        return panel;
    }

    // ── Panel: Registrar Usuario ─────────────────────────────────────────────
    private JPanel buildPanelUsuario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(titledBorder("Registrar Usuario"));

        GridBagConstraints gbc = defaultGbc();

        txtNombreUsuario  = new JTextField();
        cmbGeneroPreferido = new JComboBox<>(GENEROS);

        agregarFila(panel, gbc, 0, "Nombre:",          txtNombreUsuario);
        agregarFila(panel, gbc, 1, "Género preferido:", cmbGeneroPreferido);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        btnRow.setOpaque(false);
        btnRegistrarUsuario = boton("Registrar Usuario");
        btnRecomendar       = boton("Recomendar");
        btnRow.add(btnRegistrarUsuario);
        btnRow.add(btnRecomendar);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 4, 4, 4);
        panel.add(btnRow, gbc);

        return panel;
    }

    // ── Panel: Búsqueda ──────────────────────────────────────────────────────
    private JPanel buildPanelBusqueda() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(titledBorder("Búsqueda"));

        GridBagConstraints gbc = defaultGbc();

        txtBuscar       = new JTextField();
        cmbTipoBusqueda = new JComboBox<>(new String[]{"Por Título", "Por Género", "Por Director"});

        agregarFila(panel, gbc, 0, "Buscar:",  txtBuscar);
        agregarFila(panel, gbc, 1, "Tipo:",    cmbTipoBusqueda);

        btnBuscar = boton("Buscar");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.insets = new Insets(8, 4, 4, 4);
        panel.add(btnBuscar, gbc);

        return panel;
    }

    // ── Panel: Resultados ────────────────────────────────────────────────────
    private JPanel buildPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(titledBorder("Resultados"));

        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        txtAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtAreaResultados.setLineWrap(true);
        txtAreaResultados.setWrapStyleWord(true);
        txtAreaResultados.setBackground(new Color(252, 252, 252));
        txtAreaResultados.setMargin(new Insets(6, 6, 6, 6));

        JScrollPane scroll = new JScrollPane(txtAreaResultados);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // ── Barra de botones inferior ────────────────────────────────────────────
    private JPanel buildPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(8, 0, 0, 0));

        btnLimpiar = boton("Limpiar");
        btnLimpiar.setBackground(new Color(230, 230, 230));
        panel.add(btnLimpiar);

        return panel;
    }

    // ── Utilidades ────────────────────────────────────────────────────────────

    private void agregarFila(JPanel panel, GridBagConstraints gbc,
                              int fila, String etiqueta, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(4, 4, 4, 4);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        if (campo instanceof JTextField) {
            ((JTextField) campo).setPreferredSize(new Dimension(160, 24));
        }
        panel.add(campo, gbc);
    }

    private GridBagConstraints defaultGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        return gbc;
    }

    private TitledBorder titledBorder(String titulo) {
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            titulo,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 12),
            new Color(70, 70, 70)
        );
        return border;
    }

    private JButton boton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(220, 220, 220));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
