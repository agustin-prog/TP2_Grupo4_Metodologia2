package patrones.strategy;

/** Estrategia para verificar saldo, validar monto y debitar */
public class Extraccion implements EstrategiaTransaccion {
    @Override
    public void ejecutar(TransaccionBancaria transaccion) {
        CuentaBancaria cuenta = transaccion.getCuentaPrincipal();
        double monto = transaccion.getMonto();

        // Consulto el saldo antes de validar
        boolean saldoSuficiente = cuenta.tieneSaldoSuficiente(monto);
        transaccion.validarMonto();
        if (!saldoSuficiente) {
            throw new IllegalStateException("Saldo insuficiente para la extracción.");
        }
        cuenta.debitar(monto);
    }
}
