package patrones.gestionheroes;
import java.time.LocalDate;
import java.util.Objects;
public abstract class Heroe {
    private final String nombre;
    private final String apodo;
    private final LocalDate fechaCreacion;
    protected Heroe(String nombre, String apodo, LocalDate fechaCreacion) {
        if (nombre == null || nombre.isBlank() || apodo == null || apodo.isBlank())
            throw new IllegalArgumentException("Nombre y apodo son obligatorios");
        this.nombre = nombre; this.apodo = apodo;
        this.fechaCreacion = Objects.requireNonNull(fechaCreacion, "fechaCreacion");
    }
    public String getNombre() { return nombre; }
    public String getApodo() { return apodo; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public abstract String resumen();
}
