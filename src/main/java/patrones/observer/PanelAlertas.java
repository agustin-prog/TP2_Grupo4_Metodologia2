package patrones.observer;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PanelAlertas extends JPanel implements ObservadorSensor {
    private final Map<String, Double> umbralesMaximos = new HashMap<>();
    private final JTextArea areaAlertas = new JTextArea(12, 34);

    public PanelAlertas() {
        super(new BorderLayout());
        areaAlertas.setEditable(false);
        areaAlertas.setLineWrap(true);
        areaAlertas.setWrapStyleWord(true);
        areaAlertas.setText("Sin alertas registradas.");
        add(new JScrollPane(areaAlertas), BorderLayout.CENTER);
    }

    public void definirUmbralMaximo(String tipo, double umbral) {
        if (tipo == null || tipo.trim().isEmpty() || !Double.isFinite(umbral)) {
            throw new IllegalArgumentException("Tipo y umbral deben ser validos.");
        }
        umbralesMaximos.put(tipo, umbral);
    }

    @Override
    public void actualizar(Sensor sensor, double valorAnterior, double valorNuevo) {
        Double umbral = umbralesMaximos.get(sensor.getTipo());
        if (umbral != null && valorNuevo > umbral) {
            String mensaje = String.format(Locale.US,
                    "%s (%s): %.1f %s supera el umbral %.1f %s",
                    sensor.getTipo(), sensor.getIdentificador(), valorNuevo,
                    sensor.getUnidad(), umbral, sensor.getUnidad());
            if (SwingUtilities.isEventDispatchThread()) {
                agregarAlerta(mensaje);
            } else {
                SwingUtilities.invokeLater(() -> agregarAlerta(mensaje));
            }
            System.out.println("[ALERTA] " + mensaje);
        }
    }

    private void agregarAlerta(String mensaje) {
        if ("Sin alertas registradas.".equals(areaAlertas.getText())) {
            areaAlertas.setText("");
        }
        areaAlertas.append("ALERTA: " + mensaje + "\n\n");
    }
}
