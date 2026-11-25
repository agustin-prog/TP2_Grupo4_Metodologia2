package patrones.adapter;

/** Resultado propio del dominio; no contiene DTO ni códigos de proveedor. */
public final class ResultadoPago {
    private final String confirmacion;

    public ResultadoPago(String confirmacion) {
        if (confirmacion == null || confirmacion.isBlank()) {
            throw new IllegalArgumentException("La confirmación es obligatoria");
        }
        this.confirmacion = confirmacion;
    }

    public String getConfirmacion() { return confirmacion; }
}
