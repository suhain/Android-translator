package ru.ifmo.mobdev.android_translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Translator {
	private URL url = null;
	private URLConnection urlCon;
	private final static String adress = "http://translate.yandex.ru/tr.json/translate?lang=en-ru&text=";
	private String rusEx = "";
	private BufferedReader br;

	public String translate(String engEx) {
		for (int i = 0; i < engEx.length(); i++) {
			if (engEx.charAt(i) == ' ') {
				engEx = engEx.substring(0, i) + "+" + engEx.substring(i + 1);
			}
		}
		try {
			url = new URL(adress + engEx);
			urlCon = url.openConnection();
			urlCon.setConnectTimeout(40000);
			urlCon.connect();
			br = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			rusEx = br.readLine();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {

			}
		}
		return rusEx.substring(1, rusEx.length() - 1);
	}
}
