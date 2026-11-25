package patrones.adapter.adapters;

import java.math.BigDecimal;
import java.util.Objects;
import patrones.adapter.*;
import patrones.adapter.externos.TarjetaCredito;

/** Traduce nuestro contrato a los dos pasos de la API de tarjeta. */
public final class AdapterTarjeta implements MedioPago {
    private final TarjetaCredito tarjeta;

    public AdapterTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = Objects.requireNonNull(tarjeta, "tarjeta");
    }

    @Override
    public ResultadoPago pagar(Huesped huesped, BigDecimal monto) throws PagoException {
        validar(huesped, monto);
        if (huesped.getNumeroTarjeta() == null || huesped.getNumeroTarjeta().isBlank()) {
            throw new PagoException(PagoException.Motivo.DATOS_INVALIDOS, "Se requiere número de tarjeta");
        }
        try {
            String limite = tarjeta.validarLimite(huesped.getNumeroTarjeta(), monto);
            if (!"OK".equals(limite)) {
                throw new PagoException(PagoException.Motivo.RECHAZADO, "Tarjeta sin límite disponible");
            }
            String cargo = tarjeta.autorizarCargo(huesped.getNumeroTarjeta(), monto);
            if (!"AUTORIZADO".equals(cargo)) {
                throw new PagoException(PagoException.Motivo.RECHAZADO, "Cargo de tarjeta rechazado");
            }
            return new ResultadoPago("Pago con tarjeta confirmado");
        } catch (PagoException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new PagoException(PagoException.Motivo.ERROR_PROVEEDOR, "Error al procesar la tarjeta", e);
        }
    }

    private void validar(Huesped huesped, BigDecimal monto) throws PagoException {
        if (huesped == null || monto == null || monto.signum() <= 0) {
            throw new PagoException(PagoException.Motivo.DATOS_INVALIDOS, "Huésped o monto inválidos");
        }
    }
}
