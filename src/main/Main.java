package main;

import controlador.ControladorPedido;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ControladorPedido controlador = new ControladorPedido();
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
            ventanaPrincipal.setVisible(true);
        });
    }
}
