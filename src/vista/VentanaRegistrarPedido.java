package vista;

import controlador.ControladorPedido;
import modelo.Direccion;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarPedido extends  JDialog{
    private JPanel registrarPedido;
    private JLabel lbnNumeroPedido;
    private JLabel lblDireccion;
    private JSpinner spNumeroPedido;
    private JSpinner spDistancia;
    private JComboBox cmbTipoPedido;
    private JLabel lblTipoPedido;
    private JLabel lblDistancia;
    private JLabel lblRestaurante;
    private JLabel lblPeso;
    private JLabel lblEmbalaje;
    private JLabel lblComercio;
    private JTextField txtRestaurante;
    private JTextField txtComercio;
    private JTextField txtEmbalaje;
    private JSpinner spPeso;
    private JButton btnCancelar;
    private JButton btnRegistrar;
    private JTextField txtCalle;
    private JTextField txtCiudad;
    private JTextField txtRegion;
    private JSpinner spNumeroCalle;
    private JLabel lblCalle;
    private JLabel lblCiudad;
    private JLabel lblNumero;
    private JLabel lblRegion;

    private ControladorPedido controladorPedidos;

    public VentanaRegistrarPedido(JFrame padre, ControladorPedido controladorPedidos) {
        super(padre, "Registrar pedido", true); // true = modal
        this.controladorPedidos = controladorPedidos;

        configurarVentana();
        configurarComponentes();
        configurarEventos();
        actualizarCamposPedido();
    }

    /**
     * Configuración general de la ventana JFrame.
     */
    private void configurarVentana() {
        setTitle("Sistema Speed Fast");
        setContentPane(registrarPedido);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Configura los datos que no conviene dejar fijos en el diseñador visual.
     */
    private void configurarComponentes() {
        cmbTipoPedido.addItem("Comida");
        cmbTipoPedido.addItem("Encomienda");
        cmbTipoPedido.addItem("Express");
    }

    /**
     * Conecta los botones visuales con el código que se ejecutará al hacer clic.
     */
    private void configurarEventos() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cmbTipoPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCamposPedido();
            }
        });
    }

    public void agregarPedido() {
        String textoNumeroPedido = spNumeroPedido.getValue().toString();
        //Direccion
        String calle = txtCalle.getText();
        String textoNumeroCalle = spNumeroCalle.getValue().toString();
        String ciudad = txtCiudad.getText();
        String region = txtRegion.getText();

        String textoDistancia = spDistancia.getValue().toString();

        String restaurante = txtRestaurante.getText();
        String comercio = txtComercio.getText();
        String textoPeso = spPeso.getValue().toString();
        String embalaje = txtEmbalaje.getText();

        String tipoPedido = cmbTipoPedido.getSelectedItem().toString();

        if(!validarCamposPedido(tipoPedido)){
            return;
        }

        int numeroPedido = Integer.parseInt(textoNumeroPedido);
        int distancia = Integer.parseInt(textoDistancia);
        int numeroCalle = Integer.parseInt(textoNumeroCalle);

        switch (tipoPedido){
            case "Comida":
                PedidoComida pedidoComida = new PedidoComida(numeroPedido, new Direccion(calle, numeroCalle, ciudad, region),distancia, restaurante);
                controladorPedidos.agregarPedido(pedidoComida);
                break;
            case "Encomienda":
                double peso = Double.parseDouble(textoPeso);
                PedidoEncomienda pedidoEncomienda = new PedidoEncomienda(numeroPedido,new Direccion(calle, numeroCalle, ciudad, region),distancia,peso,embalaje);
                controladorPedidos.agregarPedido(pedidoEncomienda);
                break;
            case "Express":
                PedidoExpress pedidoExpress = new PedidoExpress(numeroPedido,new Direccion(calle, numeroCalle, ciudad, region),distancia,comercio);
                controladorPedidos.agregarPedido(pedidoExpress);
                break;
            default:
                System.out.println("Default");
        }
        JOptionPane.showMessageDialog(
                this,
                "Pedido agregado correctamente.",
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE
        );
        limpiarCampos();

    }
    /**
     * Valida los datos ingresados en el formulario
     *
     * @return true si todos los datos son válidos;
     * false en caso contrario.
     */
    private boolean validarCamposPedido(String tipoPedido) {
        String numeroPedido = spNumeroPedido.getValue().toString();
        //Direccion
        String calle = txtCalle.getText();
        String numeroCalle = spNumeroCalle.getValue().toString();
        String ciudad = txtCiudad.getText();
        String region = txtRegion.getText();

        String distancia = spDistancia.getValue().toString();

        String restaurante = txtRestaurante.getText();
        String comercio = txtComercio.getText();
        String peso = spPeso.getValue().toString();
        String embalaje = txtEmbalaje.getText();

        if (validarParametro(numeroPedido)) {
            JOptionPane.showMessageDialog(this, "El campo Numero pedido no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (validarParametro(calle)) {
            JOptionPane.showMessageDialog(this, "El campo Calle no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (validarParametro(numeroCalle)) {
            JOptionPane.showMessageDialog(this, "El campo Número no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);

            return false;
        } else if (validarParametro(ciudad)) {
            JOptionPane.showMessageDialog(this, "El campo Ciudad no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);

            return false;
        } else if (validarParametro(region)) {
            JOptionPane.showMessageDialog(this, "El campo Región no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (validarParametro(distancia)) {
            JOptionPane.showMessageDialog(this, "El campo distancia no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!validarEnteroPositivo(numeroPedido, "El numero de pedido debe ser un número entero positivo.")) {
            return false;
        }
        if (!validarEnteroPositivo(distancia, "La distancia debe ser un número entero positivo.")) {
            return false;
        }
        if (!validarEnteroPositivo(numeroCalle, "El número de la calle debe ser un número entero positivo.")) {
            return false;
        }

        int idPedido = Integer.parseInt(numeroPedido);

        if (controladorPedidos.existePedido(idPedido)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ya existe un pedido con el número " + idPedido + ".",
                    "Pedido duplicado",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        switch (tipoPedido) {
            case "Comida" -> {
                if (validarParametro(restaurante)) {
                    JOptionPane.showMessageDialog(this, "El campo restaurante no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
            case "Encomienda" -> {
                if (validarParametro(peso)) {
                    JOptionPane.showMessageDialog(this, "El campo peso no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                } else if (validarParametro(embalaje)) {
                    JOptionPane.showMessageDialog(this, "El campo embalaje no puede estar vacío.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                if (!validarDecimalPositivo(peso, "El peso debe ser un número positivo.")) {
                    return false;
                }
            }
            case "Express" -> {
                if (validarParametro(comercio)) {
                    JOptionPane.showMessageDialog(this, "El campo comercio no puede estar vacío.");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica que un campo no se encuentre vacío.
     *
     * @param campo texto ingresado por el usuario.
     * @return true si el campo está vacío; false en caso contrario.
     */
    private Boolean validarParametro(String campo){
        Boolean resultado = false;
        if(campo.isBlank()){
            resultado = true;
        }
        return resultado;
    }

    /**
     * Valida que el valor ingresado corresponda
     * a un número entero positivo.
     *
     * @param valor texto ingresado.
     * @param mensaje mensaje mostrado si la validación falla.
     * @return true si el número es válido; false en caso contrario.
     */
    private boolean validarEnteroPositivo(String valor, String mensaje) {

        try {
            int numero = Integer.parseInt(valor);
            if (numero <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        mensaje,
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    mensaje,
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private boolean validarDecimalPositivo(String valor, String mensaje) {
        try {
            double numero = Double.parseDouble(valor);

            if (numero <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        mensaje,
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    mensaje,
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return false;
        }
    }
    private void limpiarCampos(){
        spNumeroPedido.setValue(0);
        txtCalle.setText("");
        spNumeroCalle.setValue(0);
        txtCiudad.setText("");
        txtRegion.setText("");
        spDistancia.setValue(0);
        txtRestaurante.setText("");
        txtComercio.setText("");
        spPeso.setValue(0);
        txtEmbalaje.setText("");
        cmbTipoPedido.setSelectedIndex(0);
    }

    private void actualizarCamposPedido() {
        String opcion = cmbTipoPedido.getSelectedItem().toString();

        if (opcion.equals("Comida")) {
            txtRestaurante.setEnabled(true);
            txtComercio.setEnabled(false);
            spPeso.setEnabled(false);
            txtEmbalaje.setEnabled(false);
        }
        else if (opcion.equals("Encomienda")) {
            txtRestaurante.setEnabled(false);
            txtComercio.setEnabled(false);
            spPeso.setEnabled(true);
            txtEmbalaje.setEnabled(true);

        }
        else if (opcion.equals("Express")) {
            txtRestaurante.setEnabled(false);
            txtComercio.setEnabled(true);
            spPeso.setEnabled(false);
            txtEmbalaje.setEnabled(false);

        }
    }
}
