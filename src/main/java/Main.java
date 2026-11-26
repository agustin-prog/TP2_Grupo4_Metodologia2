import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import patrones.adapter.DemoAdapter;
import patrones.adapter.PagoException;
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

/** Punto de entrada para integrar las demostraciones del TP2. */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" PRUEBAS INTEGRADORAS - TP2 METODOLOGÍA II");
        System.out.println("=================================================\n");

        probarStrategy();
        probarAdapter(args);
        probarTemplate();
        probarRefactoring();
        probarGestionHeroes(args);

        System.out.println("=================================================");
        System.out.println(" FIN DE LAS PRUEBAS INTEGRADORAS");
        System.out.println("=================================================");
    }

    private static void probarStrategy() {
        System.out.println("--- 1. PATRÓN STRATEGY (Transacciones bancarias) ---");
        CuentaBancaria origen = new CuentaBancaria("Cta-001-UTN", "Titular", 10000.0);
        CuentaBancaria destino = new CuentaBancaria("Cta-Destino-888", "Destinatario", 0.0);

        ejecutarTransaccion(new Deposito(), origen, null, 5000.0);
        ejecutarTransaccion(new Extraccion(), origen, null, 3000.0);
        ejecutarTransaccion(new Transferencia(), origen, destino, 2000.0);

        System.out.printf("Saldo final de origen: $%.2f%n", origen.getSaldo());
        System.out.printf("Saldo final de destino: $%.2f%n%n", destino.getSaldo());
    }

    private static void ejecutarTransaccion(EstrategiaTransaccion estrategia,
            CuentaBancaria origen, CuentaBancaria destino, double monto) {
        TransaccionBancaria transaccion = new TransaccionBancaria(
                estrategia, origen, destino, monto);
        transaccion.confirmar();
        transaccion.ejecutar();
    }

    private static void probarAdapter(String[] args) {
        System.out.println("--- 2. PATRÓN ADAPTER (Pagos de hotel) ---");
        try {
            DemoAdapter.main(args);
        } catch (PagoException e) {
            System.out.println("Error al realizar el pago [" + e.getMotivo()
                    + "]: " + e.getMessage());
        }
        System.out.println();
    }

    private static void probarTemplate() {
        System.out.println("--- 3. PATRÓN TEMPLATE METHOD (Combate) ---");
        // Las clases que subieron los compañeros reciben únicamente el nombre.
        Guerrero guerrero = new Guerrero("Aragorn");
        Mago mago = new Mago("Gandalf");

        System.out.println("Turno del Guerrero:");
        guerrero.pelear();
        System.out.println("Turno del Mago:");
        mago.pelear();
    }

    private static void probarRefactoring() {
        System.out.println("--- 4. REFACTORING (Polimorfismo en videojuego) ---");
        try {
            Videojuego juego = new Videojuego();
            Personaje guerrero = new Personaje("Guerrero Feroz", 50.0,
                    new HabilidadCuerpoACuerpo());
            Personaje mago = new Personaje("Mago Supremo", 80.0,
                    new HabilidadMagica());

            juego.agregarPersonaje(guerrero);
            juego.agregarPersonaje(mago);
            juego.imprimirInfo(guerrero);
            juego.imprimirInfo(mago);

            Personaje masDanio = juego.obtenerPersonajeConMayorDanio();
            if (masDanio != null) {
                System.out.println("Personaje con mayor daño calculado: "
                        + masDanio.getNombre());
            }
        } catch (NoClassDefFoundError | ExceptionInInitializerError e) {
            // En el estado actual del repo, las fuentes refactoring están en
            // /refactoring y no en el source root configurado: src/main/java.
            System.out.println("No se pudo cargar refactoring: sus fuentes todavía están "
                    + "fuera de src/main/java (source root del proyecto).");
        }
        System.out.println();
    }


    private static void probarGestionHeroes(String[] args) {
        System.out.println("--- 5. FACTORY METHOD, SINGLETON Y FACADE (Gestión de héroes) ---");

        patrones.gestionheroes.DemoHeroes.main(args);

        System.out.println();
    }

}
