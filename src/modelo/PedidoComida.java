package modelo;

/**
 * Representa un pedido de comida.
 *
 * Hereda los atributos generales de la clase Pedido
 */
public class PedidoComida extends Pedido {
    private String restaurante;
    
    /**
     * Construye un pedido de comida.
     *
     * @param idPedido identificador del pedido.
     * @param direccionEntrega dirección donde se entregará el pedido.
     * @param distanciaKm distancia del pedido en kilómetros.
     * @param restaurante nombre del restaurante.
     */
    public PedidoComida(int idPedido, Direccion direccionEntrega, int distanciaKm, String restaurante) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.restaurante = restaurante;
        
    }
    
    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }
    
    /**
    * Calcula el tiempo estimado de entrega para un pedido de comida.
    * El tiempo base es de 15 minutos más 2 minutos por kilómetro.
    */
    @Override
    public void calcularTiempoEntrega(){
        int tiempoBase = 15;
        int tiempoExtra = 2 * super.getDistanciaKm();
        int resultado = tiempoBase + tiempoExtra;
        System.out.println("Tiempo estimado de entrega: "+ resultado + " minutos.\n");
    }

    /**
     * Asigna un repartidor aplicando la validación de mochila térmica.
     *
     * Este método sobrescribe la implementación de la clase Pedido.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("=== PEDIDO COMIDA #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor automaticamente...");
        System.out.println("-> Verificando mochila térmica... OK");
    }

    /**
     * Asigna un repartidor específico al pedido de comida.
     * Esta versión corresponde a una sobrecarga del método
     * asignarRepartidor().
     * @param nombreRepartidor nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("=== PEDIDO COMIDA #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor...");
        System.out.println("-> Verificando mochila térmica... OK");
        System.out.println("-> Pedido asignado a: " + nombreRepartidor);
    }

    /**
    * Muestra un resumen del pedido con su identificador,
    * dirección de entrega y distancia.
    */
    @Override
    public void mostrarResumen(){
        System.out.println("PedidoComida #" + super.getIdPedido());
        System.out.println("Dirección: " + super.getDireccionEntrega());
        System.out.println("Distancia: " + super.getDistanciaKm() + " km" );
        System.out.println("Estado: " + super.getEstado());
        System.out.println("Restaurante: " + restaurante );
    }
    
    /**
     * Devuelve una representación textual del pedido de comida.
     *
     * @return información del pedido de comida.
     */
    @Override
    public String toString() {
        return "PedidoComida{"
                + "restaurante=" + restaurante
                + '}';
    }

    /**
     * Cancela el pedido y registra
     * el evento en el historial del pedido.
     */
    @Override
    public void cancelar() {
        super.registrarEvento("Pedido cancelado (estado: " + super.getEstado() + ").");
        System.out.println("-> Pedido #" + super.getIdPedido() + " CANCELADO");
    }

    /**
     * Despacha el pedido y registra
     * el evento en el historial del pedido.
     */
    @Override
    public void despachar() {
        super.registrarEvento("Pedido despachado desde " + restaurante + ".");
        System.out.println("-> Pedido #" + super.getIdPedido() + " DESPACHADO");
    }

    /**
     * Muestra el historial de eventos registrados para este pedido.
     */
    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido #" + super.getIdPedido() + ":");
        if (super.getHistorial().isEmpty()) {
            System.out.println("   (sin movimientos registrados)");
        } else {
            for (String evento : super.getHistorial()) {
                System.out.println("   - " + evento);
            }
        }
    }
}
