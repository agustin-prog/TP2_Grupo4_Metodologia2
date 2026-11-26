package patrones.observer;

public class SensorCO2 extends Sensor {
    public SensorCO2(String identificador, double valorInicial) {
        super(identificador, "CO2", "ppm", valorInicial);
    }
}
