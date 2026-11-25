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
            // Ejecuta la demo que ya implementó el equipo Adapter.
            DemoAdapter.main(args);
        } catch (PagoException e) {
            System.out.println("Error al realizar el pago [" + e.getMotivo()
                    + "]: " + e.getMessage());
        }
        System.out.println();
    }

    private static void probarTemplate() {
        System.out.println("--- 3. PATRÓN TEMPLATE METHOD (Combate) ---");
        try {
            // Reflexión para que Main no dependa de clases que aún no estén creadas.
            Class<?> guerreroClass = Class.forName("patrones.template.Guerrero");
            Class<?> magoClass = Class.forName("patrones.template.Mago");
            Object guerrero = guerreroClass.getConstructor(String.class, int.class)
                    .newInstance("Aragorn", 120);
            Object mago = magoClass.getConstructor(String.class, int.class)
                    .newInstance("Gandalf", 80);

            System.out.println("Turno del Guerrero:");
            guerreroClass.getMethod("pelear").invoke(guerrero);
            System.out.println("\nTurno del Mago:");
            magoClass.getMethod("pelear").invoke(mago);
        } catch (ClassNotFoundException e) {
            System.out.println("No ejecutado: todavía no hay clases de Template Method "
                    + "disponibles en patrones.template.");
        } catch (ReflectiveOperationException e) {
            informarErrorDeIntegracion("Template Method", e);
        }
        System.out.println();
    }

    private static void probarRefactoring() {
        System.out.println("--- 4. REFACTORING (Polimorfismo en videojuego) ---");
        try {
            // Se usa reflexión porque las clases refactoring del repositorio están
            // fuera de src/main/java; así Main sigue compilando sin moverlas.
            Class<?> habilidadClass = Class.forName("refactoring.Habilidad");
            Class<?> cuerpoACuerpoClass = Class.forName("refactoring.HabilidadCuerpoACuerpo");
            Class<?> habilidadMagicaClass = Class.forName("refactoring.HabilidadMagica");
            Class<?> personajeClass = Class.forName("refactoring.Personaje");
            Class<?> videojuegoClass = Class.forName("refactoring.Videojuego");

            Object fisica = cuerpoACuerpoClass.getConstructor().newInstance();
            Object magia = habilidadMagicaClass.getConstructor().newInstance();
            Constructor<?> constructorPersonaje = personajeClass.getConstructor(
                    String.class, double.class, habilidadClass);
            Object guerrero = constructorPersonaje.newInstance("Guerrero Feroz", 50.0, fisica);
            Object mago = constructorPersonaje.newInstance("Mago Supremo", 80.0, magia);
            Object videojuego = videojuegoClass.getConstructor().newInstance();

            Method agregar = videojuegoClass.getMethod("agregarPersonaje", personajeClass);
            agregar.invoke(videojuego, guerrero);
            agregar.invoke(videojuego, mago);
            videojuegoClass.getMethod("imprimirInfo", personajeClass).invoke(videojuego, guerrero);
            videojuegoClass.getMethod("imprimirInfo", personajeClass).invoke(videojuego, mago);

            Object masDanio = videojuegoClass
                    .getMethod("obtenerPersonajeConMayorDanio").invoke(videojuego);
            if (masDanio != null) {
                String nombre = (String) personajeClass.getMethod("getNombre").invoke(masDanio);
                System.out.println("Personaje con mayor daño calculado: " + nombre);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No ejecutado: las clases de refactoring no están disponibles "
                    + "en el classpath actual.");
        } catch (ReflectiveOperationException e) {
            informarErrorDeIntegracion("Refactoring", e);
        }
        System.out.println();
    }

    private static void informarErrorDeIntegracion(String modulo, ReflectiveOperationException e) {
        Throwable causa = e instanceof InvocationTargetException
                ? ((InvocationTargetException) e).getTargetException() : e;
        System.out.println("No se pudo ejecutar " + modulo + ": " + causa.getMessage());
    }
}
