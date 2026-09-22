package modelo;

/**
 * Representa la dirección física de una persona
 */
public class Direccion {
    // Atributos de la dirección
    private String calle;
    private int numero;
    private String ciudad;
    private String region;


    /**
     * Constructor de la clase Direccion.
     *
     * @param calle nombre de la calle
     * @param numero número de la dirección
     * @param ciudad ciudad donde vive el dueño
     * @param region región donde vive el dueño
     */
    public Direccion(String calle, int numero, String ciudad, String region) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.region = region;
    }

    /**
     * Muestra la dirección completa de la persona.
     *
     * @return dirección formateada como texto
     */
    public String mostrarDireccion() {
        return calle + " #" + numero + ", " + ciudad + ", Region de " + region;
    }

    // Métodos get y set

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Direccion{" + "calle=" + calle + ", ciudad=" + ciudad + ", region=" + region + ", numero=" + numero + '}';
    }
}