package paquete;

//Encapsulamiento
public class QuantumConvertor {
	public double convert(String tipoMedicion, double cantidad, String unidadInicial, String unidadFinal) {
        ConversionStrategy strategy = new ConversionStrategy(tipoMedicion, cantidad, unidadInicial, unidadFinal);
        return strategy.convertir();
    }
}
