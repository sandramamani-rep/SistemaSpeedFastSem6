package vista;

import controlador.ControladorPedido;
import modelo.EstadoPedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton btnRegistrarPedido;
    private JButton btnListarPedido;
    private JButton btnSimularEntrega;
    private JPanel ventanaPrincipal;
    private JButton btnCerrar;

    private ControladorPedido controladorPedidos;

    public VentanaPrincipal(ControladorPedido controladorPedidos) {
        this.controladorPedidos = controladorPedidos;
        configurarVentana();
        configurarEventos();
    }

    /**
     * Configuración general de la ventana JFrame.
     */
    private void configurarVentana() {
        setTitle("Sistema Speed Fast");
        setContentPane(ventanaPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Conecta los botones visuales con el código que se ejecutará al hacer clic.
     */
    private void configurarEventos() {
        btnRegistrarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
            }
        });
        btnListarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPedidos();
            }
        });
        btnSimularEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularEntrega();
            }
        });
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void agregarPedido(){
        new VentanaRegistrarPedido(this, controladorPedidos).setVisible(true);
        //this.dispose();
    }

    private void listarPedidos(){
        if (controladorPedidos.obtenerPedidos().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay pedidos registrados para mostrar.",
                    "Sin pedidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new VentanaListarPedidos(this, controladorPedidos).setVisible(true);
        //this.dispose();
    }

    private void simularEntrega(){
        boolean hayPendientes = controladorPedidos.obtenerPedidos().stream()
                .anyMatch(p -> p.getEstado() == EstadoPedido.PENDIENTE);

        if (!hayPendientes) {
            JOptionPane.showMessageDialog(this,
                    "No hay pedidos pendientes para entregar.",
                    "Sin pedidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new VentanaSimulacion(this, controladorPedidos).setVisible(true);
    }

}
