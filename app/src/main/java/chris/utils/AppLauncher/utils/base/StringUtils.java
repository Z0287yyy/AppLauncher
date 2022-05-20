package chris.utils.AppLauncher.utils.base;

import java.net.URLEncoder;

public class StringUtils {
	public static final String EMPTY = "";
	
	private StringUtils() {}
	
	public static final boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}
	
	public static final boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static final String concat(String... strings) {
		StringBuilder builder = new StringBuilder();
		for(String str : strings) {
			builder.append(str);
		}
		return builder.toString();
	}
	
	public static final String nvl(String src, String defaultStr) {
		return isBlank(src) ? defaultStr : src;
	}
	
	public static final String ellipses(String src, int length) {
		if(isBlank(src) || src.length() <= length) {
			return src;
		}
		
		return src.substring(0, length) + "...";
	}
	
	public static final String toString(Object obj) {
		return (obj == null) ? EMPTY : String.valueOf(obj);
	}
	
	public static final String convertStringToHex(String str) {
		char[] chars = str.toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}
		return strBuffer.toString();
	}
	
	public static String padTwoDigitInt(final int value) {
		return ((value < 10) ? "0" : "") + value; 
	}
	
	public static final String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}
	
	public static int convertStringToInt(String str, int defVal) {
		int res = defVal;
		if(str != null) {
			String tStr = str.trim();
			if(tStr.length() > 0) {
				try {
					res = Integer.valueOf(tStr);
				} catch (NumberFormatException e) {
					//do nothing
				}
			}
		}
		return res;
	}
	
	public static long convertStringToLong(String str, long defVal) {
		long res = defVal;
		if(str != null) {
			String tStr = str.trim();
			if(tStr.length() > 0) {
				try {
					res = Long.valueOf(tStr);
				} catch (NumberFormatException e) {
					//do nothing
				}
			}
		}
		return res;
	}
	
	public static String encodeParam(String param) {
		String res = "";
		try {
			res = URLEncoder.encode(param, "iso-8859-1");
		} catch (Exception e) {
		}
		return res;
	}
	
	public static String getLastStr(String str, int len) {
		String res = str;
		try {
			res = str.substring(str.length() - len);
		} catch (Exception e) {
		}
		return res;
	}
	
	public static String objToStr(Object obj, String defaultStr) {
		String res = defaultStr;
		if (obj != null) {
			res = String.valueOf(obj);
		}
		return res;
	}
}
