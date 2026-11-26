package patrones.gestionheroes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
/** Singleton de inicialización anticipada: una instancia por classloader. */
public final class GestorHeroes {
    private static final GestorHeroes INSTANCIA = new GestorHeroes();
    private final List<Heroe> heroes = new ArrayList<>();
    private GestorHeroes() {}
    public static GestorHeroes getInstancia() { return INSTANCIA; }
    public synchronized void registrar(Heroe heroe) { heroes.add(Objects.requireNonNull(heroe)); }
    public synchronized Optional<Guerrero> consultarMejorGuerrero() {
        return heroes.stream().filter(Guerrero.class::isInstance).map(Guerrero.class::cast)
                .max(Comparator.comparingInt(Guerrero::getPoderTotal));
    }
    public synchronized String generarReporteBatalla() {
        if (heroes.isEmpty()) return "No hay héroes reclutados";
        return heroes.stream().map(Heroe::resumen).collect(Collectors.joining("\n"));
    }
    public synchronized int cantidadHeroes() { return heroes.size(); }
}
