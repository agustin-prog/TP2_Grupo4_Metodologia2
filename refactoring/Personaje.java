package refactoring;

/**
 * Entidad que representa a un personaje del videojuego.
 * Encapsula su habilidad y delega en ella el cálculo de daño y descripción
 * (Move Method + Hide Delegate).
 */
public class Personaje {

    private final String nombre;
    private final double danioBase;
    private Habilidad tipoHabilidad;

    /**
     * Crea un nuevo personaje con su nombre, daño base y habilidad inicial.
     *
     * @param nombre        nombre identificatorio del personaje
     * @param danioBase     daño base no negativo
     * @param tipoHabilidad estrategia de habilidad asignada
     */
    public Personaje(String nombre, double danioBase, Habilidad tipoHabilidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede estar vacío.");
        }
        if (Double.isNaN(danioBase) || danioBase < 0) {
            throw new IllegalArgumentException("El daño base no puede ser negativo ni NaN.");
        }
        if (tipoHabilidad == null) {
            throw new IllegalArgumentException("El tipo de habilidad no puede ser nulo.");
        }
        this.nombre = nombre.trim();
        this.danioBase = danioBase;
        this.tipoHabilidad = tipoHabilidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getDanioBase() {
        return this.danioBase;
    }

    /**
     * Permite cambiar la habilidad del personaje en tiempo de ejecución.
     * No se expone un getter de Habilidad para mantener Hide Delegate.
     *
     * @param tipoHabilidad nueva habilidad del personaje
     */
    public void setTipoHabilidad(Habilidad tipoHabilidad) {
        if (tipoHabilidad == null) {
            throw new IllegalArgumentException("El tipo de habilidad no puede ser nulo.");
        }
        this.tipoHabilidad = tipoHabilidad;
    }

    /**
     * Calcula el daño efectivo delegando en la habilidad encapsulada.
     *
     * @return daño total calculado
     */
    public double calcularDanioTotal() {
        return this.tipoHabilidad.calcularDanio(this.danioBase);
    }

    /**
     * Construye la ficha descriptiva completa del personaje y su habilidad.
     *
     * @return texto con el resumen de daño y descripción de la habilidad
     */
    public String obtenerInformacion() {
        return this.nombre + " tiene como daño base " + this.danioBase
                + " (daño total: " + this.calcularDanioTotal() + ")\n"
                + this.tipoHabilidad.obtenerDescripcion();
    }
}
