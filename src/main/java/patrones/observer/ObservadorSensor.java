package patrones.observer;

public interface ObservadorSensor {
    void actualizar(Sensor sensor, double valorAnterior, double valorNuevo);
}
