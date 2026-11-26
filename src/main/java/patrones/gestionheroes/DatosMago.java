package patrones.gestionheroes;
import java.time.LocalDate;
public record DatosMago(String nombre, String apodo, LocalDate fechaCreacion,
                        int mana) implements DatosHeroe {}
