package patrones.observer;

public class PruebaObserver {
    public static void main(String[] args) {
        Sensor temperatura = new SensorTemperatura("S-01", 22.0);
        Sensor humedad = new SensorHumedad("S-02", 45.0);
        Sensor co2 = new SensorCO2("S-03", 600.0);
        Sensor luminosidad = new SensorLuminosidad("S-04", 300.0);

        PanelPrincipal panelPrincipal = new PanelPrincipal();
        PanelAlertas panelAlertas = new PanelAlertas();
        RegistroConsola registro = new RegistroConsola();

        panelAlertas.definirUmbralMaximo("Temperatura", 30.0);
        panelAlertas.definirUmbralMaximo("Humedad", 70.0);
        panelAlertas.definirUmbralMaximo("CO2", 1000.0);
        panelAlertas.definirUmbralMaximo("Luminosidad", 800.0);

        Sensor[] sensores = {temperatura, humedad, co2, luminosidad};
        for (Sensor sensor : sensores) {
            sensor.suscribir(panelPrincipal);
            sensor.suscribir(panelAlertas);
            sensor.suscribir(registro);
        }

        System.out.println("=== Primeras lecturas: las tres vistas estan suscriptas ===");
        temperatura.registrarValor(32.5);
        humedad.registrarValor(51.0);
        co2.registrarValor(1200.0);
        luminosidad.registrarValor(850.0);

        System.out.println("\n=== Desuscribir ALERTAS solo de TEMPERATURA ===");
        temperatura.desuscribir(panelAlertas);
        temperatura.registrarValor(35.0); 

        System.out.println("\n=== Desuscribir PRINCIPAL solo de CO2 ===");
        co2.desuscribir(panelPrincipal);
        co2.registrarValor(1400.0); 

        System.out.println("\n=== Volver a suscribir ALERTAS a TEMPERATURA ===");
        temperatura.suscribir(panelAlertas);
        temperatura.registrarValor(36.0);
    }
}
