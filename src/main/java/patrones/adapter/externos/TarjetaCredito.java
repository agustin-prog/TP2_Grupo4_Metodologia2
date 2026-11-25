package patrones.adapter.externos;

import java.math.BigDecimal;

/** Simulación de API externa preexistente e inmodificable para el ejercicio. */
public class TarjetaCredito {
    private final BigDecimal limiteDisponible;

    public TarjetaCredito(BigDecimal limiteDisponible) {
        this.limiteDisponible = limiteDisponible;
    }

    /** API externa ficticia: códigos textuales propios del proveedor. */
    public String validarLimite(String numeroTarjeta, BigDecimal monto) {
        return limiteDisponible != null && limiteDisponible.compareTo(monto) >= 0 ? "OK" : "SIN_LIMITE";
    }

    public String autorizarCargo(String numeroTarjeta, BigDecimal monto) {
        return "AUTORIZADO";
    }
}
