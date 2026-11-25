package patrones.strategy;

/** Estrategia para debitar el origen y acreditar el destino. */
public class Transferencia implements EstrategiaTransaccion {
    @Override
    public void ejecutar(TransaccionBancaria transaccion) {
        CuentaBancaria origen = transaccion.getCuentaPrincipal();
        CuentaBancaria destino = transaccion.getCuentaDestino();
        double monto = transaccion.getMonto();

        if (destino == null) {
            throw new IllegalStateException("La transferencia necesita una cuenta de destino.");
        }
        transaccion.validarMonto();
        if (!origen.tieneSaldoSuficiente(monto)) {
            throw new IllegalStateException("Saldo insuficiente para la transferencia.");
        }

        origen.debitar(monto);
        try {
            destino.acreditar(monto);
        } catch (RuntimeException ex) {
            // si la acreditación falla revierto en la cuenta de origen
            origen.acreditar(monto);
            throw ex;
        }
    }
}
