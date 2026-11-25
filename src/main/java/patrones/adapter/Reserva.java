package patrones.adapter;

import java.math.BigDecimal;
import java.util.Objects;

/** Cliente del patrón Adapter; solo conoce el contrato MedioPago. */
public final class Reserva {
    private final Huesped huesped;
    private final BigDecimal montoTotal;
    private boolean pagada;
    private String confirmacion;

    public Reserva(Huesped huesped, BigDecimal montoTotal) {
        this.huesped = Objects.requireNonNull(huesped, "huesped");
        if (montoTotal == null || montoTotal.signum() <= 0) {
            throw new IllegalArgumentException("El monto de la reserva debe ser positivo");
        }
        this.montoTotal = montoTotal;
    }

    public ResultadoPago pagarCon(MedioPago medio) throws PagoException {
        Objects.requireNonNull(medio, "medio");
        if (pagada) {
            throw new PagoException(PagoException.Motivo.RESERVA_PAGADA, "La reserva ya está pagada");
        }
        ResultadoPago resultado = medio.pagar(huesped, montoTotal);
        if (resultado == null) {
            throw new PagoException(PagoException.Motivo.ERROR_PROVEEDOR, "El medio no devolvió resultado");
        }
        pagada = true;
        confirmacion = resultado.getConfirmacion();
        return resultado;
    }

    public boolean isPagada() { return pagada; }
    public String getConfirmacion() { return confirmacion; }
    public Huesped getHuesped() { return huesped; }
    public BigDecimal getMontoTotal() { return montoTotal; }
}
