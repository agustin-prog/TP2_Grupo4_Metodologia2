package patrones.gestionheroes;
/** Facade: el gremio no conoce la coordinación de creación y registro. */
public final class FachadaGremio {
    private final GestorHeroes gestor;
    private final FabricaGuerrero fabricaGuerrero;
    private final FabricaMago fabricaMago;
    public FachadaGremio() {
        this(GestorHeroes.getInstancia(), new FabricaGuerrero(), new FabricaMago());
    }
    public FachadaGremio(GestorHeroes gestor, FabricaGuerrero fg, FabricaMago fm) {
        this.gestor=java.util.Objects.requireNonNull(gestor);
        this.fabricaGuerrero=java.util.Objects.requireNonNull(fg);
        this.fabricaMago=java.util.Objects.requireNonNull(fm);
    }
    public Guerrero reclutarGuerrero(DatosGuerrero datos) {
        Guerrero h=(Guerrero) fabricaGuerrero.construir(datos);
        gestor.registrar(h); return h;
    }
    public Mago reclutarMago(DatosMago datos) {
        Mago h=(Mago) fabricaMago.construir(datos);
        gestor.registrar(h); return h;
    }
    /** Extensión sin cambiar la fachada: permitir una nueva fábrica. */
    public <T extends DatosHeroe> Heroe reclutar(FabricaHeroe<T> fabrica, T datos) {
        Heroe h=java.util.Objects.requireNonNull(fabrica).construir(datos);
        gestor.registrar(h); return h;
    }
    public String consultarMejorGuerrero() {
        return gestor.consultarMejorGuerrero().map(Heroe::resumen).orElse("No hay guerreros");
    }
    public String generarReporteBatalla() { return gestor.generarReporteBatalla(); }
}
