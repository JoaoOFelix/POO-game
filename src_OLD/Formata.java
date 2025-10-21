import java.util.Locale;
public final class Formata{

	public static String formatar(double valor) {
		return String.format(Locale.US, "%.2f", valor);
	}
	
	public static double formatarDouble(double valor) {
		return Math.round(valor * 100.0) / 100.0;
	}

}