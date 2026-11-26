package patrones.observer;

import java.awt.BorderLayout;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PanelPrincipal extends JPanel implements ObservadorSensor {
    private final Map<String, String> lecturas = new LinkedHashMap<>();
    private final Map<String, Double> ultimosValores = new LinkedHashMap<>();
    private final JTextArea areaDatos = new JTextArea(12, 34);

    public PanelPrincipal() {
        super(new BorderLayout());
        areaDatos.setEditable(false);
        areaDatos.setText("Todavia no se recibieron lecturas.");
        add(new JScrollPane(areaDatos), BorderLayout.CENTER);
    }

    @Override
    public void actualizar(Sensor sensor, double valorAnterior, double valorNuevo) {
        ultimosValores.put(sensor.getIdentificador(), valorNuevo);
        lecturas.put(sensor.getIdentificador(), String.format(Locale.US,
                "%s (%s): %.1f %s", sensor.getTipo(), sensor.getIdentificador(),
                valorNuevo, sensor.getUnidad()));
        String contenido = String.join("\n", lecturas.values());
        if (SwingUtilities.isEventDispatchThread()) {
            areaDatos.setText(contenido);
        } else {
            SwingUtilities.invokeLater(() -> areaDatos.setText(contenido));
        }
        System.out.printf(Locale.US, "[PANEL PRINCIPAL] %s (%s): %.1f %s%n",
                sensor.getTipo(), sensor.getIdentificador(), valorNuevo, sensor.getUnidad());
    }

    public Double consultarUltimoValor(String id) {
        return ultimosValores.get(id);
    }
}
