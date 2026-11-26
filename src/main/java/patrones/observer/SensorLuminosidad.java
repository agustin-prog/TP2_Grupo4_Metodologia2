package patrones.observer;

public class SensorLuminosidad extends Sensor {
    public SensorLuminosidad(String identificador, double valorInicial) {
        super(identificador, "Luminosidad", "lux", valorInicial);
    }
}
