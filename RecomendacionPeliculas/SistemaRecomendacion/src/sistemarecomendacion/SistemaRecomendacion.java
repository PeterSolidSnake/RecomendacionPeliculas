package sistemarecomendacion;

import controlador.ControladorRecomendacion;
import vista.JFRecomendacion;

public class SistemaRecomendacion {

    public static void main(String[] args) {
        JFRecomendacion frmRecomendacion = new JFRecomendacion();
        frmRecomendacion.setVisible(true);
        ControladorRecomendacion controlador = new ControladorRecomendacion(frmRecomendacion);
    }
}
