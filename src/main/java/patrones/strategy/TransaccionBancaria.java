package patrones.strategy;

public class TransaccionBancaria {

    private EstrategiaTransaccion estrategia;
    private final CuentaBancaria cuentaPrincipal;
    private CuentaBancaria cuentaDestino;

    private final double monto;
    private boolean confirmada;
    private boolean ejecutada;

    public TransaccionBancaria(EstrategiaTransaccion estrategia,
            CuentaBancaria cuentaPrincipal,
            CuentaBancaria cuentaDestino,
            double monto) {
        if (estrategia == null || cuentaPrincipal == null) {
            throw new IllegalArgumentException("La estrategia y la cuenta principal son obligatorias.");
        }
        this.estrategia = estrategia;
        this.cuentaPrincipal = cuentaPrincipal;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.confirmada = false;
        this.ejecutada = false;
    }

    public void setEstrategia(EstrategiaTransaccion estrategia) {
        comprobarEditable();
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia no puede ser nula.");
        }
        this.estrategia = estrategia;
    }

    public void setCuentaDestino(CuentaBancaria destino) {
        comprobarEditable();
        this.cuentaDestino = destino;
    }

    public void confirmar() {
        if (confirmada) {
            throw new IllegalStateException("La transacción ya fue confirmada.");
        }
        confirmada = true;
    }

    public void ejecutar() {
        if (!confirmada) {
            throw new IllegalStateException("Primero debe confirmar la transacción.");
        }
        if (ejecutada) {
            throw new IllegalStateException("La transacción ya fue ejecutada.");
        }
        estrategia.ejecutar(this);
        ejecutada = true; // Solo se marca como ejecutada si la estrategia termina bien.
    }

    public void validarMonto() {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero y finito.");
        }
    }

    public CuentaBancaria getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    private void comprobarEditable() {
        if (confirmada) {
            throw new IllegalStateException("No se puede modificar una transacción confirmada.");
        }
    }
}
