package patrones.gestionheroes;
public final class FabricaGuerrero extends FabricaHeroe<DatosGuerrero> {
    @Override protected Guerrero crear(DatosGuerrero d) {
        return new Guerrero(d.nombre(),d.apodo(),d.fechaCreacion(),d.poderAtaque(),d.victorias(),d.bonoPorVictoria());
    }
}
