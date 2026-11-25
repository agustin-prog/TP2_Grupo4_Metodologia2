package patrones.template;

public abstract class PersonajeCombate {

    private String nombre;

    // Constructor para asignarle un nombre al personaje
    public PersonajeCombate(String nombre) {
        this.nombre = nombre;
    }

    // MÉTODO PLANTILLA: define el orden fijo y obligatorio de la pelea
    public final void pelear() {
        System.out.println("=== Comienza el combate de " + nombre + " ===");

        prepararAtaque(); // Paso 1
        prepararEquipamiento(); // Paso 2
        enfocarObjetivo(); // Paso 3
        accionOpcional(); // Paso 4: HOOK (opcional)
        ejecutarAtaque(); // Paso 5
        evaluarResultado(); // Paso 6: Común

        System.out.println("=== Fin del combate de " + nombre + " ===\n");
    }

    // 1. PASOS ABSTRACTOS (Obligatorios: las subclases deben programarlos sí o sí)
    protected abstract void prepararAtaque();
    protected abstract void prepararEquipamiento();
    protected abstract void enfocarObjetivo();
    protected abstract void ejecutarAtaque();

    // 2. HOOK (Opcional: tiene llaves vacías {}, no obliga a nadie pero permite extender)
    protected void accionOpcional() {
        // Por defecto no hace nada
    }

    // 3. PASO COMÚN CONCRETO (Factorizado: es igual para todos, se escribe una sola vez)
    public void evaluarResultado() {
        System.out.println("[Común] " + nombre + " evalúa el resultado del combate.");
    }

    public String getNombre() {
        return nombre;
    }

}
