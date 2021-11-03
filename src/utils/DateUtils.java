package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 
	 * @param mesesAdelante Cuantos meses en el futuro respecto de la fecha actual se desea calcular. Admite números negativos o 0 para hoy o fechas anteriores
	 * @return Devuelve un array con 2 elementos donde el primero es el primer dia del mes que se seleccione, mientras que el segundo
	 * es el último día de dicho mes
	 */
	public static String[] obtenerMes(int mesesAdelante) {		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, mesesAdelante);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = sdf.format(cal.getTime());
		
	    int res = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, res);
		String endDate = sdf.format(cal.getTime());
		
		return new String[] {startDate, endDate};
	}
	
	public static String[] obtenerMesDeDate(Date periodo) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(periodo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDate = sdf.format(cal.getTime());
		
	    int res = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, res);
		String endDate = sdf.format(cal.getTime());
		
		return new String[] {startDate, endDate};
	}
	
	public static Date formatAnoMes(String fecha) throws ParseException {
		return new SimpleDateFormat("yyyy-MM").parse(fecha);
	}
	
	public static String formatAnoMes(Date fecha) throws ParseException {
		return new SimpleDateFormat("yyyy-MM").format(fecha);
	}
	
	public static String formatAnoMesDia(Date fecha) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
	}

}
