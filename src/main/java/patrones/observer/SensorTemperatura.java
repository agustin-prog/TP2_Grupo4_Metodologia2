package patrones.observer;

public class SensorTemperatura extends Sensor {
    public SensorTemperatura(String identificador, double valorInicial) {
        super(identificador, "Temperatura", "C", valorInicial);
    }
}
