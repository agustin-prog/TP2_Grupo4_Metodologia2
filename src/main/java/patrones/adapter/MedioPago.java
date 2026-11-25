package patrones.adapter;

/** Contrato estable que utiliza Reserva: no expone tipos de proveedores. */
public interface MedioPago {
    ResultadoPago pagar(Huesped huesped, java.math.BigDecimal monto) throws PagoException;
}
