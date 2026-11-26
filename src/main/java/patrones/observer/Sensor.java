package patrones.observer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class Sensor {
    private final String identificador;
    private final String tipo;
    private final String unidad;
    private double valor;
    private final Set<ObservadorSensor> observadores = new LinkedHashSet<>();

    protected Sensor(String identificador, String tipo, String unidad, double valorInicial) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador es obligatorio.");
        }
        if (!Double.isFinite(valorInicial)) {
            throw new IllegalArgumentException("El valor inicial debe ser finito.");
        }
        this.identificador = identificador.trim();
        this.tipo = tipo;
        this.unidad = unidad;
        this.valor = valorInicial;
    }

    public String getIdentificador() { return identificador; }
    public String getTipo() { return tipo; }
    public String getUnidad() { return unidad; }
    public double getValor() { return valor; }

    public void suscribir(ObservadorSensor observador) {
        if (observador == null) {
            throw new IllegalArgumentException("El observador no puede ser nulo.");
        }
        observadores.add(observador);
    }

    public void desuscribir(ObservadorSensor observador) {
        observadores.remove(observador);
    }

    public void registrarValor(double nuevoValor) {
        if (!Double.isFinite(nuevoValor)) {
            throw new IllegalArgumentException("El valor debe ser finito.");
        }
        double valorAnterior = valor;
        valor = nuevoValor;
        notificarObservadores(valorAnterior);
    }

    private void notificarObservadores(double valorAnterior) {
        // Copia para no fallar si un observador cambia sus suscripciones al recibir un aviso.
        List<ObservadorSensor> destinatarios = new ArrayList<>(observadores);
        for (ObservadorSensor observador : destinatarios) {
            observador.actualizar(this, valorAnterior, valor);
        }
    }
}
