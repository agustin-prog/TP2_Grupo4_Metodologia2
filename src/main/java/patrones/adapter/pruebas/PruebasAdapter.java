package patrones.adapter.pruebas;

import java.math.BigDecimal;
import patrones.adapter.*;
import patrones.adapter.adapters.*;
import patrones.adapter.externos.*;

/** Pruebas automatizadas sin JUnit, Maven ni conexión a internet. */
public final class PruebasAdapter {
    private static int realizadas;
    private static final Huesped H = new Huesped("Ana", "Pérez", "ana@ejemplo.com", "TEST-123");

    private static void comprobar(boolean condicion, String caso) {
        realizadas++;
        if (!condicion) throw new AssertionError("FALLÓ: " + caso);
        System.out.println("OK " + caso);
    }

    private static void esperarError(PagoException.Motivo motivo, Accion accion, String caso) {
        try {
            accion.ejecutar();
            throw new AssertionError("No se lanzó error: " + caso);
        } catch (PagoException e) {
            comprobar(e.getMotivo() == motivo, caso);
        }
    }

    @FunctionalInterface
    private interface Accion { void ejecutar() throws PagoException; }

    public static void main(String[] args) throws Exception {
        BigDecimal monto = new BigDecimal("500.00");
        // Mock de interfaz Target: Reserva no requiere un adaptador real.
        MedioPago falso = (h, m) -> {
            comprobar(h == H && m.compareTo(monto) == 0, "Reserva entrega datos al Target");
            return new ResultadoPago("CONFIRMACION-TEST");
        };
        Reserva reserva = new Reserva(H, monto);
        comprobar("CONFIRMACION-TEST".equals(reserva.pagarCon(falso).getConfirmacion()) && reserva.isPagada(),
                "Reserva registra pago aprobado");
        esperarError(PagoException.Motivo.RESERVA_PAGADA, () -> reserva.pagarCon(falso), "No permite doble pago");

        TarjetaCredito tarjeta = new TarjetaCredito(new BigDecimal("1000"));
        comprobar(new AdapterTarjeta(tarjeta).pagar(H, monto).getConfirmacion().contains("tarjeta"),
                "Tarjeta: límite y cargo aprobados");
        esperarError(PagoException.Motivo.RECHAZADO,
                () -> new AdapterTarjeta(new TarjetaCredito(new BigDecimal("50"))).pagar(H, monto),
                "Tarjeta: límite insuficiente");
        esperarError(PagoException.Motivo.DATOS_INVALIDOS,
                () -> new AdapterTarjeta(tarjeta).pagar(new Huesped("A", "B", "a@b", null), monto),
                "Tarjeta: número faltante");
        final boolean[] autorizarSeLlamo = {false};
        TarjetaCredito rechazo = new TarjetaCredito(new BigDecimal("1000")) {
            @Override public String autorizarCargo(String n, BigDecimal m) {
                autorizarSeLlamo[0] = true;
                return "DENEGADO";
            }
        };
        esperarError(PagoException.Motivo.RECHAZADO,
                () -> new AdapterTarjeta(rechazo).pagar(H, monto), "Tarjeta: autorización denegada");
        comprobar(autorizarSeLlamo[0], "Tarjeta: se intentó autorización tras validar límite");
        TarjetaCredito roto = new TarjetaCredito(new BigDecimal("1000")) {
            @Override public String validarLimite(String n, BigDecimal m) { throw new IllegalStateException("SDK caído"); }
        };
        esperarError(PagoException.Motivo.ERROR_PROVEEDOR,
                () -> new AdapterTarjeta(roto).pagar(H, monto), "Tarjeta: excepción externa normalizada");

        BilleteraVirtual billetera = new BilleteraVirtual() {
            @Override public int enviarPago(String email, BigDecimal m) {
                comprobar("ana@ejemplo.com".equals(email) && m.compareTo(monto) == 0,
                        "Billetera: mapea email y monto");
                return 0;
            }
        };
        comprobar(new AdapterBilletera(billetera).pagar(H, monto).getConfirmacion().contains("billetera"),
                "Billetera: aceptación mapeada a resultado propio");
        esperarError(PagoException.Motivo.RECHAZADO,
                () -> new AdapterBilletera(new BilleteraVirtual() {
                    @Override public int enviarPago(String e, BigDecimal m) { return 42; }
                }).pagar(H, monto), "Billetera: código no aceptado normalizado");
        esperarError(PagoException.Motivo.DATOS_INVALIDOS,
                () -> new AdapterBilletera(new BilleteraVirtual()).pagar(new Huesped("A","B","",null), monto),
                "Billetera: email faltante");
        esperarError(PagoException.Motivo.ERROR_PROVEEDOR,
                () -> new AdapterBilletera(new BilleteraVirtual() {
                    @Override public int enviarPago(String e, BigDecimal m) { throw new RuntimeException("sin servicio"); }
                }).pagar(H, monto), "Billetera: excepción externa normalizada");
        comprobar(!new Reserva(H, monto).isPagada(), "Reserva nueva empieza pendiente");
        System.out.println("PRUEBAS CORRECTAS: " + realizadas);
    }
}
