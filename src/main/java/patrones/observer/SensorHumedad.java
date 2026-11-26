package patrones.observer;

public class SensorHumedad extends Sensor {
    public SensorHumedad(String identificador, double valorInicial) {
        super(identificador, "Humedad", "%", valorInicial);
    }
}
