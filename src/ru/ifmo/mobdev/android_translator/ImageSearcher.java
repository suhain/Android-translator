package ru.ifmo.mobdev.android_translator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageSearcher {
	private static final String queryPrefix = "https://www.google.ru/search?tbm=isch&q=";
	private static final String begFlag = "imgurl=";
	private static final String endFlag = "&amp;";

	private static String query = null; // defined here to let execQuery() work in a new thread
	private static volatile StringBuilder sourceString = null; // same

	public static synchronized String[] searchImages(String queryWord) {
		query = queryPrefix;
		for (int i = 0; i != queryWord.length(); i++) {
			query += (queryWord.charAt(i) == ' ' ? '+' : queryWord.charAt(i));
		}
		Thread thread = new Thread(new Runnable() {
			public void run() {
				execQuery();
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			return null;
		}
		if (sourceString == null) { // must be changed in another thread
			return null;
		}

		String[] links = new String[10];
		int cur = sourceString.indexOf(begFlag);
		for (int i = 0; cur != -1 && i != 10; i++) {
			cur += begFlag.length();
			links[i] = sourceString.substring(cur,
					sourceString.indexOf(endFlag, cur));
			cur = sourceString.indexOf(begFlag, cur);
		}
		return links;
	}

	private static void execQuery() {
		URL url;
		try {
			url = new URL(query);
		} catch (MalformedURLException e) {
			sourceString = null;
			return;
		}
		try {
			BufferedReader sourceReader = new BufferedReader(
					new InputStreamReader(url.openStream()));
			sourceString = new StringBuilder();
			String line = sourceReader.readLine();
			while (line != null) {
				sourceString.append(line);
				line = sourceReader.readLine();
			}
		} catch (IOException e) {
			sourceString = null;
			return;
		}
	}
}
