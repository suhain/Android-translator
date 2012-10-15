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
	private String engExWoutSpaces; 
	private BufferedReader br;

	public synchronized String translate(String engEx) {
		engExWoutSpaces = engEx;
		for (int i = 0; i < engEx.length(); i++) {
			if (engEx.charAt(i) == ' ') {
				engExWoutSpaces = engEx.substring(0, i) + "+" + engEx.substring(i + 1);
			}
		}
		Thread thread = new Thread(new Runnable() {
			public void run() {
				workWithNetwork();
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			return null;
		}

		return rusEx.substring(1, rusEx.length() - 1);
	}

	private void workWithNetwork() {
	try {
			url = new URL(adress + engExWoutSpaces);
			urlCon = url.openConnection();
			urlCon.setConnectTimeout(40000);
			urlCon.connect();
			br = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			rusEx = br.readLine();
		} catch (Exception e) {
			return;
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {

			}
		}
	}
}