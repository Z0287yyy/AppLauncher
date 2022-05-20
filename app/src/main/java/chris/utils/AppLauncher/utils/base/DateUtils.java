package chris.utils.AppLauncher.utils.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.format.DateFormat;

public class DateUtils {

	private static final SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static String getMSDate(long datems) {
		Date date = new Date(datems);
		
		return SimpleDateFormat.getDateInstance().format(date);
	}
	
	public static String getCurrentDate() {
		Date date = new Date();
		return fullSdf.format(date);
	}
	
	public static int getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Date getDateFromString(String dateString) {
		return getDateFromString(dateString, null);
	}
	
	public static Date getDateFromString(String dateString, Date defaultDate) {
		Date date = defaultDate;
		try {
			date = fullSdf.parse(dateString);
		} catch (Exception e) {
			try {
				date = sdf.parse(dateString);
			} catch (Exception ex) {
			}
		}
		return date;
	}
	
}
