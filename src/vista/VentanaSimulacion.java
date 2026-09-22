package vista;

import controlador.ControladorPedido;
import modelo.Repartidor;
import modelo.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VentanaSimulacion extends JDialog {
    private JTextArea areaConsola;
    private JPanel ventanaSimulacion;
    private JButton btnCerrar;

    private ControladorPedido controladorPedido;

    public VentanaSimulacion(JFrame padre, ControladorPedido controladorPedidos) {
        super(padre, "Entregas", true); // true = modal
        controladorPedido = controladorPedidos;
        setTitle("Simulación de entregas");
        setContentPane(ventanaSimulacion);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        areaConsola.setEditable(false);
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> areaConsola.append(String.valueOf((char) b)));
            }
        }, true)); // <-- segundo parámetro: autoFlush = true

        // Corre la simulación en un hilo aparte para no bloquear
        // la construcción de la ventana ni el EDT.
        new Thread(this::iniciarEntregas).start();
    }

    public  void iniciarEntregas () {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga(controladorPedido);
        zonaDeCarga.agregarPedidos(controladorPedido);
        //Pedido pedido2 = new PedidoExpress(102, "Av. Providencia 1234, Providencia", 6, "Farmacia Cruz Verde");
        //Pedido pedido3 = new PedidoEncomienda(103, "Esperanza 1085, Quinta Normal", 8, 3.2, "Caja de cartón");

        // ----- Repartidor Camila -----
        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        // ----- Repartidor Luis -----
        Repartidor luis = new Repartidor("Luis", zonaDeCarga);
        // ----- Repartidor Sara -----
        Repartidor sara = new Repartidor("Sara", zonaDeCarga);

        System.out.println("[Main] =====SIMULADOR DE ENTREGAS DE PEDIDOS - SPEEDFAST=====");
        System.out.println();

        // Ejecuta los 3 repartidores en paralelo, cada uno en su propio hilo.
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(camila);
        executor.execute(luis);
        executor.execute(sara);

        // No se aceptan nuevas tareas, se espera a que las 3 en curso terminen.
        executor.shutdown();
        try {
            boolean terminoATiempo = executor.awaitTermination(1, TimeUnit.MINUTES);
            if (!terminoATiempo) {
                System.out.println("[Main] La simulación excedió el tiempo máximo de espera.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }

        System.out.println("\n[Main] === SIMULACIÓN FINALIZADA: Todos los pedidos han sido entregados correctamente ===");
    }
}
