package refactoring;

/**
 * Representa la estrategia de habilidad de un personaje.
 * Permite calcular el daño efectivo y obtener la descripción
 * mediante polimorfismo, evitando condicionales por tipo.
 */
public abstract class Habilidad {

    /**
     * Calcula el daño total a partir del daño base del personaje.
     *
     * @param danioBase daño base configurado en el personaje
     * @return daño efectivo resultante tras aplicar la habilidad
     */
    public abstract double calcularDanio(double danioBase);

    /**
     * Devuelve el texto descriptivo correspondiente a la habilidad.
     *
     * @return descripción legible de la habilidad
     */
    public abstract String obtenerDescripcion();
}
