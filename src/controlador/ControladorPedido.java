package controlador;

import modelo.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ControladorPedido {
    private final List<Pedido> pedidos;

    public ControladorPedido() {
        pedidos = new ArrayList<>();
    }

    /**
     * Agrega un pedido a la lista.
     */
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Entrega la lista de pedidos para que la vista pueda mostrarla en la tabla.
     */
    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public boolean existePedido(int idPedido) {
        return pedidos.stream()
                .anyMatch(pedido -> pedido.getIdPedido() == idPedido);
    }
}




