package refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestiona los personajes del videojuego y delega en ellos
 * el cálculo de daño y la construcción de su información.
 */
public class Videojuego {

    private final List<Personaje> personajes;

    /**
     * Inicializa un videojuego con una lista vacía de personajes.
     */
    public Videojuego() {
        this.personajes = new ArrayList<>();
    }

    /**
     * Agrega un personaje al videojuego.
     *
     * @param personaje personaje a incorporar
     */
    public void agregarPersonaje(Personaje personaje) {
        if (personaje == null) {
            throw new IllegalArgumentException("El personaje no puede ser nulo.");
        }
        this.personajes.add(personaje);
    }

    /**
     * Devuelve una vista de solo lectura de los personajes registrados.
     *
     * @return lista inmodificable de personajes
     */
    public List<Personaje> getPersonajes() {
        return Collections.unmodifiableList(this.personajes);
    }

    /**
     * Obtiene el personaje con mayor daño efectivo acumulado.
     *
     * @return personaje con mayor daño total, o null si no hay personajes cargados
     */
    public Personaje obtenerPersonajeConMayorDanio() {
        Personaje personajeConMayorDanio = null;
        double danioMaximo = Double.NEGATIVE_INFINITY;

        for (Personaje personaje : this.personajes) {
            double danioActual = personaje.calcularDanioTotal();
            if (danioActual > danioMaximo) {
                personajeConMayorDanio = personaje;
                danioMaximo = danioActual;
            }
        }
        return personajeConMayorDanio;
    }

    /**
     * Imprime por consola la información de un personaje delegando el armado del texto.
     *
     * @param personaje personaje a mostrar
     */
    public void imprimirInfo(Personaje personaje) {
        if (personaje == null) {
            System.out.println("No hay personaje para mostrar.");
            return;
        }
        System.out.println(personaje.obtenerInformacion());
    }
}
