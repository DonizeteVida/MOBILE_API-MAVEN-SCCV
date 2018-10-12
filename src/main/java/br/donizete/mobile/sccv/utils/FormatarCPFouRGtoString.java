package br.donizete.mobile.sccv.utils;

public class FormatarCPFouRGtoString {
	
	public static String format(String format) {
		format = format.replace(".", "");
		format = format.replace("-", "");
		return format;
	}

}
