package patrones.gestionheroes;
import java.util.Objects;
/** Creator: la operación construir usa el método de fábrica redefinido. */
public abstract class FabricaHeroe<T extends DatosHeroe> {
    public final Heroe construir(T datos) {
        Objects.requireNonNull(datos, "datos");
        return crear(datos);
    }
    protected abstract Heroe crear(T datos); // Factory Method
}
