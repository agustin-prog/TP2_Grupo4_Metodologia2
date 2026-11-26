package refactoring;

/**
 * Habilidad física cuerpo a cuerpo que conserva el daño base del personaje.
 */
public class HabilidadCuerpoACuerpo extends Habilidad {

    @Override
    public double calcularDanio(double danioBase) {
        return danioBase;
    }

    @Override
    public String obtenerDescripcion() {
        return "Habilidad tipo habilidad Cuerpo a cuerpo\nEsta habilidad mantiene tu fuerza";
    }
}
