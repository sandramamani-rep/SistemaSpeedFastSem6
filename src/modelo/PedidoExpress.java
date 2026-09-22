package modelo;

/**
 * Representa un pedido de tipo Compra Express.
 *
 * Hereda los atributos generales de la clase Pedido 
 * 
 */
public class PedidoExpress extends Pedido{
    private String comercio;
    
    /**
     * Construye un pedido de tipo Express.
     *
     * @param idPedido identificador del pedido.
     * @param direccionEntrega dirección donde se entregará el pedido.
     * @param distanciaKm distancia del pedido en kilómetros.
     * @param comercio nombre del comercio.
     */
    public PedidoExpress(int idPedido, Direccion direccionEntrega, int distanciaKm, String comercio) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.comercio = comercio;
    }
    
    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }
    
    /**
    * Calcula el tiempo estimado de entrega para un pedido express.
    * El tiempo base es de 10 minutos más 5 minutos extra
    * si la distancia es > 5 kilómetros.
    */
    @Override
    public void calcularTiempoEntrega(){
        int tiempoBase = 10;
        int tiempoExtra = 5;
        int resultado = 0;
        if(super.getDistanciaKm() > 5){
            resultado = tiempoBase + tiempoExtra;
        }else{
            resultado = tiempoBase;
        }
        System.out.println("Tiempo estimado de entrega: "+ resultado + " minutos.\n");
    }

    /**
     * Asigna un repartidor considerando la cercanía y disponibilidad inmediata.
     *
     * Este método sobrescribe la implementación de la clase Pedido.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("=== PEDIDO EXPRESS #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor automaticamente...");
        System.out.println("-> Repartidor más cercano con disponibilidad "
                + "inmediata encontrado.");
    }

    /**
     * Asigna un repartidor específico al pedido Express.
     *
     * Esta versión corresponde a una sobrecarga del método
     * asignarRepartidor().
     *
     * @param nombreRepartidor nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("=== PEDIDO EXPRESS #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor...");
        System.out.println("-> Repartidor más cercano con disponibilidad "
                + "inmediata encontrado.");
        System.out.println("-> Pedido asignado a: " + nombreRepartidor);
    }
    /**
    * Muestra un resumen del pedido con su identificador,
    * dirección de entrega y distancia.
    */
    @Override
    public void mostrarResumen(){
        System.out.println("PedidoExpress #" + super.getIdPedido());
        System.out.println("Dirección: " + super.getDireccionEntrega());
        System.out.println("Distancia: " + super.getDistanciaKm() + " km" );
        System.out.println("Estado: " + super.getEstado());
        System.out.println("Comercio: " + comercio);
    }
    
    /**
     * Devuelve una representación textual del pedido Express.
     * @return información del pedido Express.
     */
    @Override
    public String toString() {
        return "PedidoExpress{"
                + "comercio=" + comercio
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
        super.registrarEvento("Pedido despachado desde " + comercio + ".");
        System.out.println("-> Pedido #" + super.getIdPedido() + " DESPACHADO");
    }

    /**
     * Muestra el historial de eventos registrados para este pedido.
     */
    @Override
    public void verHistorial() {
        System.out.println("Historial del pedido #" + super.getIdPedido() + ":");
        if (super.getHistorial().isEmpty()) {
            System.out.println("(sin movimientos registrados)");
        } else {
            for (String evento : super.getHistorial()) {
                System.out.println("   - " + evento);
            }
        }
    }
}
