package paquete;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ConversionesMap {

	private static final Map<String, Function<Double, Double>> CONVERSIONES_LONGITUD = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Kilometro", value -> value * 1000.0);
			put("Metro", value -> value);
			put("Centimetro", value -> value * 0.01);
			put("Milimetro", value -> value * 0.001);
			put("Yarda", value -> value * 0.9144);
			put("Pie", value -> value * 0.3048);
			put("Pulgada", value -> value * 0.0254);
			put("Milla", value -> value * 1609.34);
			put("Milla Nautica", value -> value * 1852.0);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_TIEMPO = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Segundo", value -> value);
			put("Minuto", value -> value * 60.0);
			put("Hora", value -> value * 3600.0);
			put("Dia", value -> value * 86400.0);
			put("Semana", value -> value * 604800.0);
			put("Mes", value -> value * 2592000.0);
			put("Anio", value -> value * 31536000.0);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_AREA = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Metro Cuadrado", value -> value); // Base
			put("Kilometro Cuadrado", value -> value * 1_000_000);
			put("Centimetro Cuadrado", value -> value * 0.0001);
			put("Milimetro Cuadrado", value -> value * 0.000001);
			put("Hectarea", value -> value * 10_000);
			put("Acre", value -> value * 4046.86);
			put("Pie Cuadrado", value -> value * 0.092903);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_VOLUMEN = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Litro", value -> value);
			put("Mililitro", value -> value * 0.001);
			put("Metro Cubico", value -> value * 1000);
			put("Centimetro Cubico", value -> value * 0.001);
			put("Galon US", value -> value * 3.78541);
			put("Galon UK", value -> value * 4.54609);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_PESO = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Kilogramo", value -> value);
			put("Gramo", value -> value * 0.001);
			put("Tonelada", value -> value * 1000);
			put("Libra", value -> value * 0.453592);
			put("Onza", value -> value * 0.0283495);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_VELOCIDAD = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Metros por Segundo", value -> value);
			put("Kilometros por Hora", value -> value * 3.6);
			put("Millas por Hora", value -> value * 2.23694);
			put("Nudos", value -> value * 1.94384);
		}
	};
	private static final Map<String, Function<Double, Double>> CONVERSIONES_DATOS = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Bit", value -> value);
			put("Byte", value -> value * 8);
			put("Kilobyte", value -> value * 8 * 1024);
			put("Megabyte", value -> value * 8 * 1024 * 1024);
			put("Gigabyte", value -> value * 8 * 1024 * 1024 * 1024);
		}
	};

	private static final Map<String, Function<Double, Double>> CONVERSIONES_PRESION = new HashMap<>() {
		{
			put("Pascal", value -> value); // Base
			put("Bar", value -> value * 0.00001);
			put("PSI", value -> value * 0.000145038);
			put("Torr", value -> value * 0.00750062);
			put("atm", value -> value * 0.00000986923);
		}
	};

	private static final Map<String, Function<Double, Double>> CONVERSIONES_ANGULO_PLANO = new HashMap<>() {
		{
			put("Grado", value -> value); // Base
			put("Radian", value -> value * (Math.PI / 180));
			put("Minuto de arco", value -> value * (1.0 / 60));
			put("Segundo de arco", value -> value * (1.0 / 3600));
		}
	};

	private static final Map<String, Function<Double, Double>> CONVERSIONES_FRECUENCIA = new HashMap<>() {
		{
			put("Hertz", value -> value); // Base
			put("Kilohertz", value -> value * 1000);
			put("Megahertz", value -> value * 1_000_000);
			put("Gigahertz", value -> value * 1_000_000_000);
		}
	};

//Medidas No Lineales, diferente implementacion
//DE X UNIDAD INICIAL A CELSIUS (CONVERSOR A UNIDAD ESTANDAR)
	private static final Map<String, Function<Double, Double>> CONVERSIONES_TEMPERATURA = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Celsius", value -> value);
			put("Fahrenheit", value -> (value - 32.0) * 5.0 / 9.0);
			put("Kelvin", value -> value - 273.15);
		}
	};
	// DE CELSIUS A CUALQUIER UNIDAD FINAL (CONVERSOR UNIDAD FINAL)
	private static final Map<String, Function<Double, Double>> CONVERSIONES_TEMPERATURA_INVERSA = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Celsius", value -> value);
			put("Fahrenheit", value -> (value * 9.0 / 5.0) + 32.0);
			put("Kelvin", value -> value + 273.15);
		}
	};

	private static final Map<String, Map<String, Function<Double, Double>>> TIPOS_CONVERSION = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; // POR DEFAUlt

		{// Se pone este bloque de llave porque se indica q es static
			put("Longitud", CONVERSIONES_LONGITUD);
			put("Area", CONVERSIONES_AREA);
			put("Volumen", CONVERSIONES_VOLUMEN);
			put("Peso", CONVERSIONES_PESO);
			put("Datos", CONVERSIONES_DATOS);
			put("Tiempo", CONVERSIONES_TIEMPO);
			put("Velocidad", CONVERSIONES_VELOCIDAD);
			put("Temperatura", CONVERSIONES_TEMPERATURA);
			put("Frecuencia", CONVERSIONES_FRECUENCIA);
			put("Angulo Plano", CONVERSIONES_ANGULO_PLANO);
			put("Presion", CONVERSIONES_PRESION);

		}
	};

	public static String[] itemsConversiones() {
		return TIPOS_CONVERSION.keySet().toArray(new String[0]);
	}

	public static Map<String, Function<Double, Double>> getTiposConversion(String tipo) {
		return TIPOS_CONVERSION.get(tipo);
	}

	// GETTERS

	public static Map<String, Function<Double, Double>> getConversionesLongitud() {
		return CONVERSIONES_LONGITUD;
	}

	public static Map<String, Function<Double, Double>> getConversionesArea() {
		return CONVERSIONES_AREA;
	}

	public static Map<String, Function<Double, Double>> getConversionesVolumen() {
		return CONVERSIONES_VOLUMEN;
	}

	public static Map<String, Function<Double, Double>> getConversionesPeso() {
		return CONVERSIONES_PESO;
	}

	public static Map<String, Function<Double, Double>> getConversionesVelocidad() {
		return CONVERSIONES_VELOCIDAD;
	}

	public static Map<String, Function<Double, Double>> getConversionesDatos() {
		return CONVERSIONES_DATOS;
	}

	public static Map<String, Function<Double, Double>> getConversionesTiempo() {
		return CONVERSIONES_TIEMPO;
	}

	public static Map<String, Function<Double, Double>> getConversionesTemperatura() {
		return CONVERSIONES_TEMPERATURA;
	}

	public static Map<String, Function<Double, Double>> getConversionesTemperaturaInversa() {
		return CONVERSIONES_TEMPERATURA_INVERSA;
	}

	public static Map<String, Map<String, Function<Double, Double>>> getTiposConversion() {
		return TIPOS_CONVERSION;
	}

	public static void main(String[] args) {
	    // Valor a convertir
	    double valor = 1.0;

	    // Iterar sobre cada tipo de conversión
	    for (String tipo : ConversionesMap.itemsConversiones()) {
	        System.out.println("Conversiones para: " + tipo);
	        
	        // Obtener el mapa de conversiones para el tipo actual
	        Map<String, Function<Double, Double>> conversiones = ConversionesMap.getTiposConversion(tipo);
	        
	        // Iterar sobre cada unidad en el mapa de conversiones
	        for (String unidad : conversiones.keySet()) {
	            // Realizar la conversión
	            double resultado = conversiones.get(unidad).apply(valor);
	            System.out.printf("%s: %.5f%n", unidad, resultado);
	        }
	        
	        System.out.println(); // Línea en blanco para separar tipos de conversiones
	    }
	}

}
