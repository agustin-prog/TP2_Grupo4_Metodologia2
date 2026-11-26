package refactoring;

/**
 * Habilidad mágica que duplica el daño base del personaje.
 */
public class HabilidadMagica extends Habilidad {

    private static final double MULTIPLICADOR_MAGICO = 2.0;

    @Override
    public double calcularDanio(double danioBase) {
        return danioBase * MULTIPLICADOR_MAGICO;
    }

    @Override
    public String obtenerDescripcion() {
        return "Habilidad tipo habilidad mágica\nEsta habilidad dobla tu fuerza";
    }
}
