package patrones.adapter.adapters;

import java.math.BigDecimal;
import java.util.Objects;
import patrones.adapter.*;
import patrones.adapter.externos.BilleteraVirtual;

/** Traduce los datos de Reserva a la API de billetera externa. */
public final class AdapterBilletera implements MedioPago {
    private final BilleteraVirtual billetera;

    public AdapterBilletera(BilleteraVirtual billetera) {
        this.billetera = Objects.requireNonNull(billetera, "billetera");
    }

    @Override
    public ResultadoPago pagar(Huesped huesped, BigDecimal monto) throws PagoException {
        if (huesped == null || monto == null || monto.signum() <= 0
                || huesped.getEmail() == null || huesped.getEmail().isBlank()) {
            throw new PagoException(PagoException.Motivo.DATOS_INVALIDOS, "Email o monto inválidos");
        }
        try {
            int codigo = billetera.enviarPago(huesped.getEmail(), monto);
            if (codigo != 0) {
                throw new PagoException(PagoException.Motivo.RECHAZADO, "Pago con billetera rechazado");
            }
            return new ResultadoPago("Pago con billetera confirmado");
        } catch (PagoException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new PagoException(PagoException.Motivo.ERROR_PROVEEDOR, "Error al procesar la billetera", e);
        }
    }
}
