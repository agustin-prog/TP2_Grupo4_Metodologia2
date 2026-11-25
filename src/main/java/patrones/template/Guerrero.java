package patrones.template;

public class Guerrero extends PersonajeCombate {

    // Constructor: le pasa el nombre a la clase padre
    public Guerrero(String nombre) {
        super(nombre);
    }

    // Paso 1: preparar el arma (según enunciado)
    @Override
    protected void prepararAtaque() {
        System.out.println("[Guerrero] " + getNombre() + " desenvaina y prepara su espada de combate.");
    }

    // Paso 2: equipar la armadura (según enunciado)
    @Override
    protected void prepararEquipamiento() {
        System.out.println("[Guerrero] " + getNombre() + " se ajusta y equipa su armadura pesada.");
    }

    // Paso 3: acercarse al enemigo (según enunciado)
    @Override
    protected void enfocarObjetivo() {
        System.out.println("[Guerrero] " + getNombre() + " corre y se acerca decididamente hacia el enemigo.");
    }

    // Paso 4: HOOK opcional (el guerrero decide usarlo para rugir)
    @Override
    protected void accionOpcional() {
        System.out.println("[Hook Guerrero] " + getNombre() + " lanza un rugido de batalla para intimidar al rival.");
    }

    // Paso 5: ejecutar un ataque físico (según enunciado)
    @Override
    protected void ejecutarAtaque() {
        System.out.println("[Guerrero] " + getNombre() + " asesta un feroz ataque físico con su espada.");
    }
}
