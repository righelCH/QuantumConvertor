package paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.SystemColor;

public class ConversorUniversal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t_inicial;
	private JLabel ltitle;
	private JComboBox<String> ctipoMedicion;
	private JComboBox<String> c_unidadInicial = new JComboBox<String>();// pasando array a comboBox con <>
	private JComboBox<String> c_unidadFinal = new JComboBox<String>();
	private JButton bconversor;
	private JLabel laviso;
	private JTextField tfinal;
	private Double cantidad;
	private String unidadInicial;
	private String unidadFinal;
	private String aux;
	private String auxf;

	private QuantumConvertor quantumConvertor = new QuantumConvertor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.5");

		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
			UIManager.put("Label.foreground", Color.WHITE);
			UIManager.put("TextField.foreground", Color.WHITE);

		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ConversorUniversal frame = new ConversorUniversal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConversorUniversal() {
		setTitle("QuantumConvertor⚡ - @righelCH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 314);
		setLocationRelativeTo(null);// si se coloca antes de setBounds no funciona
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ltitle = new JLabel("Conversor\r\n  de ");
		ltitle.setFont(new Font("JetBrains Mono NL ExtraLight", Font.PLAIN, 12));
		ltitle.setHorizontalAlignment(SwingConstants.RIGHT);
		ltitle.setBounds(49, 14, 144, 20);
		contentPane.add(ltitle);

		t_inicial = new JTextField();
		t_inicial.setFont(new Font("JetBrains Mono NL Medium", Font.PLAIN, 13));
		t_inicial.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 64)));
		t_inicial.setBounds(34, 64, 155, 28);
		contentPane.add(t_inicial);
		t_inicial.setColumns(10);

		ctipoMedicion = new JComboBox<>(ConversionesMap.itemsConversiones());
		ctipoMedicion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 255, 255)));
		ctipoMedicion.setBackground(SystemColor.controlDkShadow);
		ctipoMedicion.setFont(new Font("JetBrains Mono NL ExtraLight", Font.PLAIN, 12));
		ctipoMedicion.setBounds(203, 10, 168, 28);
		contentPane.add(ctipoMedicion);

		bconversor = new JButton("Resetear");
		bconversor.setBorder(new LineBorder(new Color(204, 204, 204)));
		bconversor.setFont(new Font("JetBrains Mono NL ExtraLight", Font.PLAIN, 11));
		bconversor.addActionListener(e -> {
			t_inicial.setText(null);
			tfinal.setText(null);
		});
		bconversor.setBounds(183, 155, 109, 21);
		contentPane.add(bconversor);

		laviso = new JLabel("");
		laviso.setFont(new Font("JetBrains Mono NL ExtraLight", Font.PLAIN, 11));
		laviso.setHorizontalAlignment(SwingConstants.CENTER);
		laviso.setBorder(new LineBorder(new Color(204, 204, 204), 2));
		laviso.setBounds(138, 204, 205, 25);
		contentPane.add(laviso);
		c_unidadInicial.setFont(new Font("JetBrains Mono ExtraLight", Font.PLAIN, 11));
		c_unidadInicial.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 255)));

		c_unidadInicial.setBounds(34, 96, 155, 26);
		contentPane.add(c_unidadInicial);
		c_unidadFinal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 255)));
		c_unidadFinal.setFont(new Font("JetBrains Mono NL ExtraLight", Font.PLAIN, 11));
		c_unidadFinal.setBounds(254, 96, 155, 26);
		contentPane.add(c_unidadFinal);

		tfinal = new JTextField();
		tfinal.setText("0.0");
		tfinal.setEnabled(false);
		tfinal.setFont(new Font("JetBrains Mono NL Medium", Font.PLAIN, 13));
		tfinal.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 255, 64)));
		tfinal.setColumns(10);
		tfinal.setBounds(254, 64, 155, 29);
		contentPane.add(tfinal);

		obtenerItemsComboBox();
		realizarConversion();
		// se procede a la conversion si se detecta cambion en la eleccion de unidad y
		// cantidad insertada
		ctipoMedicion.addActionListener(e -> {
			obtenerItemsComboBox();
			realizarConversion(); // para q coga los valores por defecto en la primera inicialización
		});
		c_unidadInicial.addActionListener(e -> realizarConversion());
		c_unidadFinal.addActionListener(e -> realizarConversion());

		t_inicial.getDocument().addDocumentListener((DocumentListenerSimplificado) e -> realizarConversion());
	}

	void obtenerItemsComboBox() {

		try {
			Map<String, Function<Double, Double>> medidaSeleccionada = tipoMedicion();

			String[] items = medidaSeleccionada.keySet().toArray(new String[0]); // Se obtienen los nombres de las
																					// medidas con KEY
			c_unidadInicial.setModel(new JComboBox<>(items).getModel()); // getModel para
			c_unidadFinal.setModel(new JComboBox<>(invertirArray(items)).getModel());

			// aqui se establece por defecto los valores de los items del comboBox, para que
			// no aparezca la caja vacia
			// los item nulos obtienen el 1º valor del comboBox
			// se asigna estos valores cada vez q se cambia de tipo de medida
			c_unidadInicial.setSelectedIndex(0);
			c_unidadFinal.setSelectedIndex(0);

			System.out.println("asignacion default en nulos");
		} catch (NullPointerException e) {
			System.out.println("error en obtenerItem..");
		}
	}

	String[] invertirArray(String[] array) { // el reverse lo tiene Collections asi q lo convierto a lista y luego a
												// array
		List<String> u = Arrays.asList(array);
		Collections.reverse(u); // aplica el cambio a lista
		return u.toArray(new String[0]);
	}

	Map<String, Function<Double, Double>> tipoMedicion() {
		if (ctipoMedicion.getSelectedItem() == null) {
			ctipoMedicion.setSelectedIndex(0);
		}
		String seleccion = (String) ctipoMedicion.getSelectedItem();
		return ConversionesMap.getTiposConversion(seleccion);
	}

	void realizarConversion() {
		// intercambiarSiSonIguales();
		// Tanto unidadInicial como final ya tienen guardado la opcion anterior, por eso
		// en intercambiarSiSonIguales() se vuelve a coger la actual unidad seleccionada
		// que seria la ACTUAL
		// la unidad sera intercambiada por la anterior guardada en aux

		try {

			aux = unidadInicial;
			auxf = unidadFinal;

			unidadInicial = (String) c_unidadInicial.getSelectedItem();
			unidadFinal = (String) c_unidadFinal.getSelectedItem();

			intercambiarSiSonIguales(unidadInicial, unidadFinal);

			cantidad = Double.parseDouble(t_inicial.getText());
			String tipoMedicion = (String) ctipoMedicion.getSelectedItem();
			laviso.setText(null); // paea borrar el aviso anterior
			Double resultado = quantumConvertor.convert(tipoMedicion, cantidad, unidadInicial, unidadFinal);
		
			tfinal.setText(String.format("%.7f",resultado));
		} catch (NumberFormatException e) { // en caso de espacio blanco, vacio o caracter especial o alfabetico
			laviso.setText("Introduce un valor valido");
			tfinal.setText(null);
		}
	}

	// si son iguales se intercambian los items asi siempre estaran en unidades
	// distintas, por eso ademas el metodo de arrayinverso como complemento en la
	// visualizacion
	void intercambiarSiSonIguales(String uInicial, String uFinal) {

		if (uInicial.equals(uFinal)) {
			c_unidadInicial.setSelectedItem(auxf);
			c_unidadFinal.setSelectedItem(aux);// se reemplaza por la origina inicial ya q la nuev ainicial es igual a
												// final
		}
	}

}