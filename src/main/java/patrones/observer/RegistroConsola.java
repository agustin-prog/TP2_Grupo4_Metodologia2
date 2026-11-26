package patrones.observer;

import java.util.Locale;

public class RegistroConsola implements ObservadorSensor {
    @Override
    public void actualizar(Sensor sensor, double valorAnterior, double valorNuevo) {
        System.out.printf(Locale.US, "[REGISTRO] %s (%s): %.1f -> %.1f %s%n",
                sensor.getTipo(), sensor.getIdentificador(),
                valorAnterior, valorNuevo, sensor.getUnidad());
    }
}
