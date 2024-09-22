package paquete;

import java.util.Map;
import java.util.function.Function;

public class ConversionStrategy {

	private Map<String, Function<Double, Double>> tipoMedicion;
	private Double cantidad;
	private String unidadInicial;
	private String unidadFinal;

	// CONSTRUCTOR, para no tener q poner todos los parametros en cada metro
	public ConversionStrategy(String tipoMedicion, Double cantidad, String unidadInicial,
			String unidadFinal) {
		this.tipoMedicion = ConversionesMap.getTiposConversion(tipoMedicion);
		this.cantidad = cantidad;
		this.unidadInicial = unidadInicial;
		this.unidadFinal = unidadFinal;
	}

	public double convertir() {

		return conversorUnidadFinal(conversorABase());

	}

	// se aplica la formula de acuerdo a la unidad seleccionada, metodo para
	// convertir cualquier unidad a unidadBase
	double conversorABase() {

		Function<Double, Double> ConversorABase = tipoMedicion.get(unidadInicial);
		 if (ConversorABase == null) 
	            throw new IllegalArgumentException("No se encontro un conversor para la unidad inicial: " + unidadInicial);
	       
		return ConversorABase.apply(cantidad);
	}

	double conversorUnidadFinal(Double n) { // se aplica la formula de la unidadBase a X unidadFinal seleccionada

		if (tipoMedicion == ConversionesMap.getConversionesTemperatura()) {
			Function<Double, Double> ConversorInversa = ConversionesMap.getConversionesTemperaturaInversa()
					.get(unidadFinal);
			 if (ConversorInversa == null) {
	                throw new IllegalArgumentException("No se encontro un conversor para la unidad final de temperatura: " + unidadFinal);
	            }
			return ConversorInversa.apply(n);
		}
		Function<Double, Double> Conversor = tipoMedicion.get(unidadFinal);
		 if (Conversor == null) {
             throw new IllegalArgumentException("No se encontro un conversor para la unidad final : " + unidadFinal);
         }
		return (n / Conversor.apply(1.0));
	}
}
