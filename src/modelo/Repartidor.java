package modelo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Representa a un repartidor de SpeedFast que ejecuta las entregas
 * de forma concurrente.
 *
 * <p>La clase implementa {@link Runnable}, por lo que cada instancia
 * puede ser ejecutada por un hilo. Los repartidores comparten una
 * instancia de {@link ZonaDeCarga}, desde la cual retiran los pedidos
 * disponibles de manera segura.</p>
 */
public class Repartidor implements Runnable {

    private String nombre;

    private final ZonaDeCarga zonaDeCarga;
    private final Random random = new Random();

    /**
     * Construye un repartidor asociado a una zona de carga compartida.
     *
     * @param nombre nombre del repartidor.
     * @param zonaDeCarga zona de carga compartida desde la cual se retirarán los pedidos.
     */
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Ejecuta el proceso de entrega de pedidos del repartidor.
     * El repartidor retira pedidos de la zona de carga compartida,
     * cambia su estado a {@link EstadoPedido#EN_REPARTO}, simula el
     * tiempo de entrega mediante {@link Thread#sleep(long)} y finalmente
     * cambia el estado del pedido a {@link EstadoPedido#ENTREGADO}.
     * El acceso a los pedidos se realiza mediante {@link ZonaDeCarga},
     * que controla el acceso concurrente al recurso compartido y evita
     * que un mismo pedido sea retirado por más de un repartidor.
     * Si el hilo es interrumpido durante la simulación, se restaura
     * su estado de interrupción y finaliza la ejecución del repartidor.
     */
    @Override
    public void run() {

        while (true) {
            try {
                Pedido pedidoRetirado = zonaDeCarga.retirarPedido();
                if (pedidoRetirado == null) {
                    break;
                }

                System.out.println("[Repartidor: " + nombre + "] Retirando "
                        + pedidoRetirado.getClass().getSimpleName() + " #" + pedidoRetirado.getIdPedido() + "...");
                pedidoRetirado.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedidoRetirado.getIdPedido() + "- Estado: " + pedidoRetirado.getEstado());

                        // Simula el tiempo que tarda el repartidor en llegar al destino.
                Thread.sleep(1000 + random.nextInt(2000));

                System.out.println("[Repartidor: " + nombre + "] Entregando pedido #"
                        + pedidoRetirado.getIdPedido() + "...");
                pedidoRetirado.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedidoRetirado.getIdPedido() + "- Estado: " + pedidoRetirado.getEstado());


            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor: " + nombre + "] fue interrumpido.");
                return;
            }
        }

        System.out.println("[Repartidor: " + nombre + "] finalizó todas sus entregas.");
    }
}
