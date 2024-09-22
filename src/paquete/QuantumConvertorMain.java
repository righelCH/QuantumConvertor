package paquete;

import java.awt.Color;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

public class QuantumConvertorMain {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.5");

        if (args.length > 0) {
            // si hay 4 argumentos es porque se usara por consola
            if (args.length >= 4) {
                executeCliMode(args);
            } else {
                System.out.println("Uso: java -jar QuantumConvertor.jar <tipoMedicion> <cantidad> <unidadInicial> <unidadFinal>");
            }
        } else {
            // Ejecutar por defecto en modo GUI
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                UIManager.put("Label.foreground", Color.WHITE);
                UIManager.put("TextField.foreground", Color.WHITE);
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            javax.swing.SwingUtilities.invokeLater(() -> {
                new ConversorUniversal().setVisible(true);
            });
        }
    }

    private static void executeCliMode(String[] args) {
       
        String tipoMedicion = args[0].trim();
        double cantidad = tratarStr(args[1].trim());
        String unidadInicial = args[2].trim();
        String unidadFinal = args[3].trim();

        if (tipoMedicion.isEmpty() || unidadInicial.isEmpty() || unidadFinal.isEmpty()) {
            System.out.println("Error: Todos los argumentos deben ser proporcionados.");
            return;
        }

        if (!validacionUnidades(tipoMedicion, unidadInicial, unidadFinal)) {
            System.out.println("Error: Las unidades proporcionadas no son validas para el tipo de medicion especificado.");
            return;
        }

        QuantumConvertor qc = new QuantumConvertor();
        double resultado = qc.convert(tipoMedicion, cantidad, unidadInicial, unidadFinal);
        System.out.println("Resultado: " + resultado);
    }

    private static double tratarStr(String cantidadStr) {
        try {
            return Double.parseDouble(cantidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Error: La cantidad debe ser un numero valido.");
            return -1; // Retorna -1 para indicar error
        }
    }

    private static boolean validacionUnidades(String tipoMedicion, String unidadInicial, String unidadFinal) {
        return ConversionesMap.getTiposConversion(tipoMedicion).containsKey(unidadInicial)
                && ConversionesMap.getTiposConversion(tipoMedicion).containsKey(unidadFinal);
    }
}
