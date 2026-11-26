package patrones.gestionheroes;
import java.time.LocalDate;
/** Datos comunes de creación, sin obligar a nuevas clases a usar campos ajenos. */
public interface DatosHeroe {
    String nombre();
    String apodo();
    LocalDate fechaCreacion();
}
