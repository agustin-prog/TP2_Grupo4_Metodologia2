package patrones.gestionheroes;
import java.time.LocalDate;
public final class Mago extends Heroe {
    private final int mana;
    public Mago(String nombre, String apodo, LocalDate fechaCreacion, int mana) {
        super(nombre, apodo, fechaCreacion);
        if (mana < 0) throw new IllegalArgumentException("El maná no puede ser negativo");
        this.mana=mana;
    }
    public int getMana() { return mana; }
    @Override public String resumen() { return "Mago " + getApodo() + " (maná: " + mana + ")"; }
}
