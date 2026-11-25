package patrones.strategy;

/**
 * Cuenta para consultar saldo, acreditar y debitar fondos
 */
public class CuentaBancaria {

    private String numero;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numero, String titular, double saldoInicial) {
        if (numero == null || numero.trim().isEmpty() || titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("El número y el titular son obligatorios.");
        }
        if (!Double.isFinite(saldoInicial) || saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser válido y no negativo.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void acreditar(double monto) {
        validarImporte(monto);
        double nuevoSaldo = saldo + monto;
        if (!Double.isFinite(nuevoSaldo)) {
            throw new IllegalStateException("No se puede acreditar: saldo fuera de rango.");
        }
        saldo = nuevoSaldo;
    }

    public void debitar(double monto) {
        validarImporte(monto);
        if (!tieneSaldoSuficiente(monto)) {
            throw new IllegalStateException("Saldo insuficiente.");
        }
        saldo -= monto;
    }

    public boolean tieneSaldoSuficiente(double monto) {
        return Double.isFinite(monto) && monto > 0 && saldo >= monto;
    }

    public double getSaldo() {
        return saldo;
    }

    private void validarImporte(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            throw new IllegalArgumentException("El importe debe ser positivo y finito.");
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

}
