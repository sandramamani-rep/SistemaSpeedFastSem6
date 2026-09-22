package modelo;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

/**
 * Representa un pedido genérico dentro del sistema de reparto SpeedFast.
 *
 * Esta clase corresponde a la superclase de los distintos tipos de pedidos
 * que maneja la empresa.
 */
public abstract class Pedido implements Cancelable, Despachable, Rastreable, Comparable<Pedido> {
    private int idPedido;
    private Direccion direccionEntrega;
    private int distanciaKm;
    private EstadoPedido estado;

    /**
     * Registro de historial de eventos asociados al pedido
     */
    private ArrayList<String> historial = new ArrayList<>();

    /**
     * Construye un pedido con sus datos principales.
     *
     * @param idPedido identificador único del pedido.
     * @param direccionEntrega dirección donde se debe entregar el pedido.
     * @param distanciaKm distancia del pedido en kilómetros.
     */
    public Pedido(int idPedido, Direccion direccionEntrega, int distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE;
        registrarEvento("Pedido creado (estado: PENDIENTE).");
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(int distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setEstado(String nuevoEstado) {
        this.estado = EstadoPedido.valueOf(nuevoEstado);
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    @Override
    public int compareTo(Pedido otro) {
        return Integer.compare(this.idPedido, otro.idPedido);
    }

    /**
     * Agrega un evento al historial del pedido.
     * @param evento descripción del evento ocurrido.
     */
    protected void registrarEvento(String evento) {
        historial.add(evento);
    }

    /**
    * Calcula e imprime el tiempo estimado de entrega
    * según el tipo de pedido.
    */
    public abstract void calcularTiempoEntrega();

    /**
     * Asigna un repartidor al pedido.
     *
     * Este método está diseñado para ser sobrescrito por las subclases,
     * permitiendo que cada tipo de pedido implemente su propia lógica
     * de asignación.
     */
    public void asignarRepartidor() {
        System.out.println("=== PEDIDO #"+getIdPedido()+" ====");
        System.out.println("-> Asignando repartidor al pedido numero: " + idPedido);
    }
    /**
    * Muestra un resumen del pedido con su identificador,
    * dirección de entrega y distancia.
    */
    public void mostrarResumen(){
        System.out.println("Pedido #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega.mostrarDireccion());
        System.out.println("Distancia: " + distanciaKm + " km" );
        System.out.println("Estado: " + estado);
    }

    /**
     * Devuelve una representación textual del pedido.
     * @return información del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{id=" + idPedido + ", direccion='" + direccionEntrega + "', estado=" + estado + "}";
    }
}
