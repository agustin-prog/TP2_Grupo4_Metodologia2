package patrones.gestionheroes;
import java.time.LocalDate;
/** Ejecutable independiente; desde Main grupal: DemoHeroes.main(args). */
public final class DemoHeroes {
    private DemoHeroes() {}
    public static void main(String[] args) {
        FachadaGremio gremioNorte=new FachadaGremio();
        FachadaGremio gremioSur=new FachadaGremio();
        var fecha=LocalDate.of(2026,11,25);
        gremioNorte.reclutarGuerrero(new DatosGuerrero("Arthas","Lobo",fecha,100,3,10));
        gremioSur.reclutarGuerrero(new DatosGuerrero("Bran","Escudo",fecha,95,5,10));
        gremioSur.reclutarMago(new DatosMago("Merlin","Sabio",fecha,200));
        // Prueba directa del punto de extension generico de la fachada.
        Heroe heroeGenerico = gremioNorte.reclutar(
                new FabricaMago(), new DatosMago("Elara", "Luz", fecha, 180));
        if (!(heroeGenerico instanceof Mago))
            throw new AssertionError("El reclutamiento generico no creo un Mago");
        System.out.println("Reclutamiento generico: " + heroeGenerico.resumen());
        System.out.println("Gestor único: " + (GestorHeroes.getInstancia()==GestorHeroes.getInstancia()));
        System.out.println("Mejor guerrero: " + gremioNorte.consultarMejorGuerrero());
        System.out.println("Reporte compartido:\n" + gremioSur.generarReporteBatalla());
        if (GestorHeroes.getInstancia().cantidadHeroes()!=4) throw new AssertionError("Registro compartido incorrecto");
        if (!gremioNorte.consultarMejorGuerrero().contains("Escudo")) throw new AssertionError("Mejor guerrero incorrecto");
        System.out.println("PRUEBAS DE INTEGRACIÓN: OK");
    }
}
