package patrones.strategy;

/** Estrategia para validar el monto y acreditar en la cuenta principal. */
public class Deposito implements EstrategiaTransaccion {
    @Override
    public void ejecutar(TransaccionBancaria transaccion) {
        transaccion.validarMonto();
        transaccion.getCuentaPrincipal().acreditar(transaccion.getMonto());
    }
}
