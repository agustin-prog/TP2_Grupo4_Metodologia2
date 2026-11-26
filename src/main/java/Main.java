import java.util.List;
import patrones.adapter.DemoAdapter;
import patrones.adapter.PagoException;
import patrones.gestionheroes.DemoHeroes;
import patrones.observer.PruebaObserver;
import patrones.strategy.CuentaBancaria;
import patrones.strategy.Deposito;
import patrones.strategy.EstrategiaTransaccion;
import patrones.strategy.Extraccion;
import patrones.strategy.Transferencia;
import patrones.strategy.TransaccionBancaria;
import patrones.template.Guerrero;
import patrones.template.Mago;
import refactoring.HabilidadCuerpoACuerpo;
import refactoring.HabilidadMagica;
import refactoring.Personaje;
import refactoring.Videojuego;

/** Ejecuta por separado las demostraciones de las actividades del TP2. */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" TP2 - METODOLOGÍA DE SISTEMAS II - GRUPO 4");
        System.out.println("=================================================");

        ejecutar("Actividad 1 - Strategy: transacciones bancarias", Main::probarStrategy);
        ejecutar("Actividad 2 - Adapter: pagos de hotel", Main::probarAdapter);
        ejecutar("Actividad 3 - Template Method: combate", Main::probarTemplate);
        ejecutar("Actividad 4 - Observer: monitoreo ambiental", Main::probarObserver);
        ejecutar("Actividad 5 - Factory Method, Singleton y Facade: gestión de héroes",
                Main::probarGestionHeroes);
        ejecutar("Refactoring: polimorfismo de habilidades", Main::probarRefactoring);

        System.out.println("\n=================================================");
        System.out.println(" FIN DE LAS DEMOSTRACIONES");
        System.out.println("=================================================");
    }

    /** Evita que una falla en una demostración impida ejecutar las siguientes. */
    private static void ejecutar(String titulo, Runnable demostracion) {
        System.out.println("\n--- " + titulo + " ---");
        try {
            demostracion.run();
        } catch (RuntimeException | LinkageError e) {
            System.out.println("No se pudo completar esta demostración: " + e.getMessage());
        }
    }

    private static void probarStrategy() {
        CuentaBancaria origen = new CuentaBancaria("Cta-001-UTN", "Titular", 10000.0);
        CuentaBancaria destino = new CuentaBancaria("Cta-Destino-888", "Destinatario", 0.0);
        ejecutarTransaccion(new Deposito(), origen, null, 5000.0);
        ejecutarTransaccion(new Extraccion(), origen, null, 3000.0);
        ejecutarTransaccion(new Transferencia(), origen, destino, 2000.0);
        System.out.printf("Saldo final origen: $%.2f%n", origen.getSaldo());
        System.out.printf("Saldo final destino: $%.2f%n", destino.getSaldo());
    }

    private static void ejecutarTransaccion(EstrategiaTransaccion estrategia,
            CuentaBancaria origen, CuentaBancaria destino, double monto) {
        TransaccionBancaria transaccion = new TransaccionBancaria(
                estrategia, origen, destino, monto);
        transaccion.confirmar();
        transaccion.ejecutar();
    }

    private static void probarAdapter() {
        try {
            DemoAdapter.main(new String[0]);
        } catch (PagoException e) {
            System.out.println("Error al realizar el pago [" + e.getMotivo()
                    + "]: " + e.getMessage());
        }
    }

    private static void probarTemplate() {
        Guerrero guerrero = new Guerrero("Aragorn");
        Mago mago = new Mago("Gandalf");
        System.out.println("Turno del Guerrero:");
        guerrero.pelear();
        System.out.println("Turno del Mago:");
        mago.pelear();
    }

    private static void probarObserver() {
        // PruebaObserver demuestra sensores, vistas, umbrales y suscripciones dinámicas.
        PruebaObserver.main(new String[0]);
    }

    private static void probarGestionHeroes() {
        DemoHeroes.main(new String[0]);
    }

    private static void probarRefactoring() {
        Videojuego juego = new Videojuego();
        Personaje guerrero = new Personaje("Guerrero Feroz", 50.0,
                new HabilidadCuerpoACuerpo());
        Personaje mago = new Personaje("Mago Supremo", 80.0,
                new HabilidadMagica());
        juego.agregarPersonaje(guerrero);
        juego.agregarPersonaje(mago);

        List<Personaje> personajes = juego.getPersonajes();
        for (Personaje personaje : personajes) {
            juego.imprimirInfo(personaje);
        }
        Personaje masDanio = juego.obtenerPersonajeConMayorDanio();
        if (masDanio != null) {
            System.out.println("Personaje con mayor daño calculado: " + masDanio.getNombre());
        }
    }
}
