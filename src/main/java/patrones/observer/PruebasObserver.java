package patrones.observer;

public class PruebasObserver {
    private static int aprobadas = 0;

    private static class ObservadorDePrueba implements ObservadorSensor {
        int llamadas = 0;
        Sensor ultimoSensor;
        double anterior;
        double nuevo;

        @Override
        public void actualizar(Sensor sensor, double valorAnterior, double valorNuevo) {
            llamadas++;
            ultimoSensor = sensor;
            anterior = valorAnterior;
            nuevo = valorNuevo;
        }
    }

    private static void comprobar(boolean condicion, String descripcion) {
        if (!condicion) throw new AssertionError("Fallo: " + descripcion);
        aprobadas++;
        System.out.println("OK " + aprobadas + ": " + descripcion);
    }

    public static void main(String[] args) {
        Sensor t = new SensorTemperatura("T1", 20.0);
        Sensor h = new SensorHumedad("H1", 40.0);
        ObservadorDePrueba a = new ObservadorDePrueba();
        ObservadorDePrueba b = new ObservadorDePrueba();

        t.suscribir(a);
        t.suscribir(b);
        t.registrarValor(25.0);
        comprobar(a.llamadas == 1 && b.llamadas == 1 && a.anterior == 20.0
                && a.nuevo == 25.0, "Notifica a todos con valores anterior/nuevo");

        t.desuscribir(b);
        t.registrarValor(26.0);
        comprobar(a.llamadas == 2 && b.llamadas == 1, "Desuscripcion selectiva");

        h.suscribir(b);
        h.registrarValor(55.0);
        comprobar(a.llamadas == 2 && b.llamadas == 2 && b.ultimoSensor == h,
                "Sensores independientes");

        t.suscribir(a);
        t.registrarValor(27.0);
        comprobar(a.llamadas == 3, "Sin notificaciones duplicadas");

        ObservadorDePrueba c = new ObservadorDePrueba();
        Sensor nuevo = new SensorCO2("C1", 500.0);
        nuevo.suscribir(c);
        nuevo.registrarValor(1001.0);
        comprobar(c.llamadas == 1 && c.ultimoSensor == nuevo,
                "Se admiten nuevos sensores y observadores");

        boolean rechazo = false;
        try {
            t.registrarValor(Double.NaN);
        } catch (IllegalArgumentException ex) {
            rechazo = true;
        }
        comprobar(rechazo && t.getValor() == 27.0, "Rechaza valores invalidos");

        h.suscribir(a);
        h.registrarValor(60.0);
        comprobar(a.llamadas == 4 && a.ultimoSensor == h && a.nuevo == 60.0,
                "Un observador sigue varios sensores");

        PanelPrincipal panel = new PanelPrincipal();
        t.suscribir(panel);
        t.registrarValor(28.0);
        comprobar(Double.valueOf(28.0).equals(panel.consultarUltimoValor("T1")),
                "PanelPrincipal consulta el ultimo valor por identificador");

        System.out.println("RESULTADO: " + aprobadas + "/8 pruebas correctas.");
    }
}
