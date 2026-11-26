package patrones.gestionheroes;
import java.time.LocalDate;
public final class Guerrero extends Heroe {
    private final int poderAtaque;
    private final int victorias;
    private final int bonoPorVictoria;
    public Guerrero(String nombre, String apodo, LocalDate fechaCreacion,
                    int poderAtaque, int victorias, int bonoPorVictoria) {
        super(nombre, apodo, fechaCreacion);
        if (poderAtaque < 0 || victorias < 0 || bonoPorVictoria < 0)
            throw new IllegalArgumentException("Las estadísticas no pueden ser negativas");
        this.poderAtaque=poderAtaque; this.victorias=victorias; this.bonoPorVictoria=bonoPorVictoria;
    }
    public int getPoderTotal() { return poderAtaque + victorias * bonoPorVictoria; }
    @Override public String resumen() { return "Guerrero " + getApodo() + " (poder total: " + getPoderTotal() + ")"; }
}
