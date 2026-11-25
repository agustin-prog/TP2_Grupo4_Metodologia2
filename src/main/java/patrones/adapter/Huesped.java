package patrones.adapter;

import java.util.Objects;

public final class Huesped {
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String numeroTarjeta;

    public Huesped(String nombre, String apellido, String email, String numeroTarjeta) {
        this.nombre = Objects.requireNonNull(nombre, "nombre");
        this.apellido = Objects.requireNonNull(apellido, "apellido");
        this.email = Objects.requireNonNull(email, "email");
        this.numeroTarjeta = numeroTarjeta; // Puede ser null para pagar con billetera.
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getNumeroTarjeta() { return numeroTarjeta; }
}
