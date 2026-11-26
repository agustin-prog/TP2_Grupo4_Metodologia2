package patrones.observer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InterfazMonitoreo extends JFrame {
    private final Sensor[] sensores = {
        new SensorTemperatura("S-01", 22.0),
        new SensorHumedad("S-02", 45.0),
        new SensorCO2("S-03", 600.0),
        new SensorLuminosidad("S-04", 300.0)
    };
    private final PanelPrincipal panelPrincipal = new PanelPrincipal();
    private final PanelAlertas panelAlertas = new PanelAlertas();
    private final RegistroConsola registro = new RegistroConsola();

    public InterfazMonitoreo() {
        super("Monitoreo ambiental - Patron Observer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        panelAlertas.definirUmbralMaximo("Temperatura", 30.0);
        panelAlertas.definirUmbralMaximo("Humedad", 70.0);
        panelAlertas.definirUmbralMaximo("CO2", 1000.0);
        panelAlertas.definirUmbralMaximo("Luminosidad", 800.0);

        JPanel controles = new JPanel(new GridLayout(0, 1, 5, 5));
        controles.setBorder(BorderFactory.createTitledBorder("Lecturas y suscripciones por sensor"));
        for (Sensor sensor : sensores) {
            JPanel fila = new JPanel();
            fila.add(new JLabel(sensor.getTipo() + " [" + sensor.getUnidad() + "]"));
            JTextField campo = new JTextField(String.format(Locale.US, "%.1f", sensor.getValor()), 6);
            fila.add(campo);
            JButton actualizar = new JButton("Registrar");
            actualizar.addActionListener(e -> {
                try {
                    sensor.registrarValor(Double.parseDouble(campo.getText().trim().replace(',', '.')));
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Ingresar un numero valido: " + ex.getMessage(),
                            "Valor incorrecto", JOptionPane.ERROR_MESSAGE);
                }
            });
            fila.add(actualizar);

            fila.add(crearSuscripcion(sensor, "Principal", panelPrincipal));
            fila.add(crearSuscripcion(sensor, "Alertas", panelAlertas));
            fila.add(crearSuscripcion(sensor, "Consola", registro));
            controles.add(fila);
        }

        JSplitPane vistas = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                envolver("Panel principal", panelPrincipal), envolver("Panel de alertas", panelAlertas));
        vistas.setResizeWeight(0.5);
        add(controles, BorderLayout.NORTH);
        add(vistas, BorderLayout.CENTER);
        add(new JLabel("Activa o desactiva cada observador y registra nuevos valores."), BorderLayout.SOUTH);
        setSize(1120, 650);
        setLocationRelativeTo(null);
    }

    private JCheckBox crearSuscripcion(Sensor sensor, String titulo, ObservadorSensor observador) {
        sensor.suscribir(observador);
        JCheckBox casilla = new JCheckBox(titulo, true);
        casilla.addActionListener(e -> {
            if (casilla.isSelected()) {
                sensor.suscribir(observador);
            } else {
                sensor.desuscribir(observador);
            }
        });
        return casilla;
    }

    private JPanel envolver(String titulo, JPanel contenido) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazMonitoreo().setVisible(true));
    }
}
