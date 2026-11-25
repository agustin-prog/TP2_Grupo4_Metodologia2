package patrones.strategy;

/** Interfaz para los distintos tipos de transacción bancaria. */
public interface EstrategiaTransaccion {
    void ejecutar(TransaccionBancaria transaccion);
}
