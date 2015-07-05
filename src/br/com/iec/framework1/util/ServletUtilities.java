package br.com.iec.framework1.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtilities {

	public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
			+ "Transitional//EN\">";

	public static String headWithTitle(String title) {
		return (DOCTYPE + "\n" + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE>"
                + "<link href=\"css/my.css\" rel=\"stylesheet\" type=\"text/css\" /></HEAD>\n");
	}

	public static int getIntParameter(HttpServletRequest request,
			String paramName, int defaultValue) {
		String paramString = request.getParameter(paramName);
		int paramValue;
		try {
			paramValue = Integer.parseInt(paramString);
		} catch (NumberFormatException nfe) { // null or bad format
			paramValue = defaultValue;
		}
		return (paramValue);
	}

	// Given a string, this method replaces all occurrences of
	// '<' with '&lt;', all occurrences of '>' with
	// '&gt;', and (to handle cases that occur inside attribute values), all
	// occurrences of
	// double quotes with '&quot;' and all occurrences of '&' with '&amp;'.
	// Without such filtering, an arbitrary string could not safely be inserted
	// in a Web page.
	public static String filter(String input) {
		if (!hasSpecialChars(input)) {
			return (input);
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	private static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i < input.length(); i++) {
				c = input.charAt(i);
				switch (c) {
				case '<':
					flag = true;
					break;
				case '>':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return (flag);
	}
}
