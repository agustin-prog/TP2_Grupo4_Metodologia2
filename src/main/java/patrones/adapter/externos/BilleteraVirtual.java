package patrones.adapter.externos;

import java.math.BigDecimal;

/** Simulación de API externa preexistente e inmodificable para el ejercicio. */
public class BilleteraVirtual {
    /** API externa ficticia: 0 = aceptado; otro código = rechazado. */
    public int enviarPago(String email, BigDecimal monto) {
        return 0;
    }
}
