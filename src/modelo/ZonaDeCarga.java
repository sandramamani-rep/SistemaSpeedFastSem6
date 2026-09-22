package modelo;

import controlador.ControladorPedido;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Representa la zona de carga compartida de SpeedFast.
 *
 * <p>La clase administra los pedidos disponibles para los repartidores
 * y constituye un recurso compartido entre los diferentes hilos de
 * ejecución.</p>
 *
 * <p>El acceso a la cola de pedidos se controla mediante mecanismos
 * de sincronización para evitar que dos repartidores retiren
 * simultáneamente el mismo pedido.</p>
 */
public class ZonaDeCarga {
    //private ControladorPedido controladorPedido;
    private final PriorityBlockingQueue<Pedido> colaPedidos;
    private final ReentrantLock lock = new ReentrantLock();

    public ZonaDeCarga(ControladorPedido controladorPedido) {
        this.colaPedidos = new PriorityBlockingQueue<>();
        //controladorPedido = controladorPedido;
    }

    /**
     * Agrega un pedido a la zona de carga compartida.
     *
     * <p>El pedido queda disponible para que cualquiera de los
     * repartidores pueda retirarlo posteriormente.</p>
     *
     * @return {@code true} si el pedido fue agregado correctamente;
     *         {@code false} si no pudo ser agregado.
     */
    public boolean agregarPedidos(ControladorPedido controladorPedido) {
        lock.lock();
        try {
            List<Pedido> pedidos = controladorPedido.obtenerPedidos();
            for (Pedido p : pedidos){
                if(p.getEstado().toString().equalsIgnoreCase(String.valueOf(EstadoPedido.PENDIENTE))){
                    colaPedidos.put(p);
                    System.out.println("[Zona de carga] Pedido #"+p.getIdPedido()+" agregado. Destino: " + p.getDireccionEntrega());
                }
            }
            return !colaPedidos.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retira de forma segura un pedido de la zona de carga.
     *
     * <p>La operación elimina el pedido de la cola para que este
     * pueda ser procesado por un único repartidor, evitando que
     * otro hilo pueda retirar nuevamente el mismo pedido.</p>
     *
     * @return el pedido retirado, o {@code null} si no existen
     *         pedidos disponibles en la zona de carga.
     */
    public Pedido retirarPedido() {
        lock.lock();
        try {
            Pedido pedido = colaPedidos.poll();
            if (pedido != null) {
                System.out.println("[Zona de carga] Procesando: Pedido #" + pedido.getIdPedido());
            }
            return pedido;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retorna el número actual de pedidos en espera.
     * @return cantidad de pedidos pendientes
     */
    public int getCantidadPedidosPendientes() {
        return colaPedidos.size();
    }

    /**
     * Verifica si existen pedidos pendientes en la zona de carga.
     *
     * @return {@code true} si existe al menos un pedido pendiente;
     *         {@code false} si la zona de carga está vacía.
     */
    public boolean quedanPedidos() {
        return !colaPedidos.isEmpty();
    }
}
