package patrones.template;

public class Mago extends PersonajeCombate {

    public Mago(String nombre) {
        super(nombre);
    }

    // Paso 1: seleccionar un hechizo (según enunciado)
    @Override
    protected void prepararAtaque() {
        System.out.println("[Mago] " + getNombre() + " consulta su grimorio y selecciona una bola de fuego.");
    }

    // Paso 2: preparar el bastón (según enunciado)
    @Override
    protected void prepararEquipamiento() {
        System.out.println("[Mago] " + getNombre() + " alza y canaliza su bastón arcano.");
    }

    // Paso 3: concentrar energía mágica (según enunciado)
    @Override
    protected void enfocarObjetivo() {
        System.out.println("[Mago] " + getNombre() + " mantiene distancia y concentra su energía mágica.");
    }

    // Paso 5: ejecutar un ataque mágico (según enunciado)
    @Override
    protected void ejecutarAtaque() {
        System.out.println("[Mago] " + getNombre() + " lanza el ataque mágico con gran poder de destrucción.");
    }
}
