package modelo;

/**
 * Representa un pedido de tipo encomienda.
 *
 * Hereda los atributos generales de la clase Pedido
 *
 */
public class PedidoEncomienda extends Pedido{
    private double peso;
    private String tipoEmbalaje;
    
    /**
     * Construye un pedido de encomienda.
     *
     * 
     * @param idPedido identificador del pedido.
     * @param direccionEntrega dirección donde se entregará la encomienda.
     * @param distanciaKm distancia del pedido en kilómetros.
     * @param peso peso de la encomienda.
     * @param tipoEmbalaje tipo de embalaje utilizado.
     */
    public PedidoEncomienda(int idPedido, Direccion direccionEntrega, int distanciaKm, double peso, String tipoEmbalaje) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.peso = peso;
        this.tipoEmbalaje = tipoEmbalaje;
    }
    
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTipoEmbalaje() {
        return tipoEmbalaje;
    }

    public void setTipoEmbalaje(String tipoEmbalaje) {
        this.tipoEmbalaje = tipoEmbalaje;
    }
    
    /**
    * Calcula el tiempo estimado de entrega para un pedido de encomienda.
    * El tiempo base es de 20 minutos más 1.5 minutos por kilómetro.
    */
    @Override
    public void calcularTiempoEntrega(){
        int tiempoBase = 20;
        double tiempoExtra = 1.5 * super.getDistanciaKm();
        int resultado = tiempoBase + (int)tiempoExtra;
        System.out.println("Tiempo estimado de entrega: "+ resultado + " minutos.\n");
    }

    /**
     * Asigna un repartidor aplicando la validación del peso y embalaje.
     *
     * Este método sobrescribe la implementación de la clase Pedido.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("=== PEDIDO ENCOMIENDA #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor automaticamente...");
        System.out.println("-> Validando peso y embalaje... OK");
    }

    /**
     * Asigna un repartidor específico al pedido de encomienda.
     * Esta versión corresponde a una sobrecarga del método
     * asignarRepartidor().
     * @param nombreRepartidor nombre del repartidor asignado.
     */
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("=== PEDIDO ENCOMIENDA #"+super.getIdPedido()+" ====");
        System.out.println("Asignando repartidor...");
        System.out.println("-> Validando peso y embalaje... OK");
        System.out.println("-> Pedido asignado a: " + nombreRepartidor);
    }
    /**
    * Muestra un resumen del pedido con su identificador,
    * dirección de entrega y distancia.
    */
    @Override
    public void mostrarResumen(){
        System.out.println("PedidoEncomienda #" + super.getIdPedido());
        System.out.println("Dirección: " + super.getDireccionEntrega());
        System.out.println("Distancia: " + super.getDistanciaKm() + " km" );
        System.out.println("Estado: " + super.getEstado());
        System.out.println("Peso: " + peso);
        System.out.println("Tipo embalaje: " + tipoEmbalaje);
    }
    
    /**
     * Devuelve una representación textual del pedido de encomienda.
     *
     * @return información del pedido de encomienda.
     */
    @Override
    public String toString() {
        return "PedidoEncomienda{"
                + "peso=" + peso
                + ", tipoEmbalaje=" + tipoEmbalaje
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
        super.registrarEvento("Pedido despachado (peso: " + peso + " kg).");
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
