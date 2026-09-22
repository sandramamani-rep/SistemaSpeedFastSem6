package vista;

import controlador.ControladorPedido;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListarPedidos extends JDialog {


    private ControladorPedido controladorPedidos;
    private JPanel ListarPedidos;
    private JTable tblPedidos;
    private JScrollPane jspTable;
    private JButton btnCerrar;

    private DefaultTableModel modeloTabla;

    public VentanaListarPedidos(JFrame padre, ControladorPedido controladorPedidos) {
        super(padre, "Listar pedidos", true); // true = modal
        this.controladorPedidos = controladorPedidos;
        configurarVentana();
        configurarEventos();
        inicializarTabla();
        actualizarTabla();
    }

    /**
     * Configuración general de la ventana JFrame.
     */
    private void configurarVentana() {
        setTitle("Sistema Speed Fast");
        setContentPane(ListarPedidos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Conecta los botones visuales con el código que se ejecutará al hacer clic.
     */
    private void configurarEventos() {
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void inicializarTabla(){
        String[] columnas = {"Numero", "Direccion", "Distancia(km)", "Estado"};
        modeloTabla = new DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblPedidos.setModel(modeloTabla);
        tblPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /**
     * Vuelve a cargar la tabla usando los pedidos almacenados.
     */
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Pedido pedido : controladorPedidos.obtenerPedidos()) {
            Object[] fila = {
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega().mostrarDireccion(),
                    pedido.getDistanciaKm(),
                    pedido.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }
}
