package patrones.gestionheroes;
import java.time.LocalDate;
public record DatosGuerrero(String nombre, String apodo, LocalDate fechaCreacion,
                            int poderAtaque, int victorias, int bonoPorVictoria) implements DatosHeroe {}
