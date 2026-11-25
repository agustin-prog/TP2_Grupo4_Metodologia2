package patrones.adapter;

/** Excepción normalizada: no traslada respuestas o excepciones externas al cliente. */
public final class PagoException extends Exception {
    public enum Motivo { DATOS_INVALIDOS, RECHAZADO, ERROR_PROVEEDOR, RESERVA_PAGADA }
    private final Motivo motivo;

    public PagoException(Motivo motivo, String mensaje) {
        super(mensaje);
        this.motivo = motivo;
    }

    public PagoException(Motivo motivo, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.motivo = motivo;
    }

    public Motivo getMotivo() { return motivo; }
}
