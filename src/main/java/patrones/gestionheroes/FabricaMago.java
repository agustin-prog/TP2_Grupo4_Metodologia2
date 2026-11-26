package patrones.gestionheroes;
public final class FabricaMago extends FabricaHeroe<DatosMago> {
    @Override protected Mago crear(DatosMago d) {
        return new Mago(d.nombre(),d.apodo(),d.fechaCreacion(),d.mana());
    }
}
