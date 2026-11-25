package patrones.adapter;

import java.math.BigDecimal;
import patrones.adapter.adapters.*;
import patrones.adapter.externos.*;

/** Demo independiente para integrar después con el Main grupal. */
public final class DemoAdapter {
    private DemoAdapter() { }

    public static void main(String[] args) throws PagoException {
        Huesped huesped = new Huesped("Ana", "Pérez", "ana@ejemplo.com", "TARJETA_DE_PRUEBA");
        Reserva reservaTarjeta = new Reserva(huesped, new BigDecimal("1500.00"));
        MedioPago tarjeta = new AdapterTarjeta(new TarjetaCredito(new BigDecimal("2000.00")));
        System.out.println(reservaTarjeta.pagarCon(tarjeta).getConfirmacion());

        Reserva reservaBilletera = new Reserva(huesped, new BigDecimal("750.00"));
        MedioPago billetera = new AdapterBilletera(new BilleteraVirtual());
        System.out.println(reservaBilletera.pagarCon(billetera).getConfirmacion());
    }
}
