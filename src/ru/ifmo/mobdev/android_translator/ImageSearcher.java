package ru.ifmo.mobdev.android_translator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageSearcher {
	private static final String queryPrefix = 
			"https://www.google.ru/search?tbm=isch&tbs=isz:m&q=";
	private static final String begFlag = "imgurl=";
	private static final String endFlag = "&amp;";

	private String sourceString = null;
	private int cur = 0;
	
	public ImageSearcher(String queryWord) {
		init(queryWord);
	}
	
	private void init(String queryWord) {
		String query = queryPrefix;
		for (int i = 0; i != queryWord.length(); i++) {
			query += (queryWord.charAt(i) == ' ' ? '+' : queryWord.charAt(i));
		}
		URL url;
		try {
			url = new URL(query);
		} catch (MalformedURLException e) {
			return;
		}
		StringBuilder source = null;
		try {
			BufferedReader sourceReader = new BufferedReader(
					new InputStreamReader(url.openStream()));
			source = new StringBuilder();
			String line = sourceReader.readLine();
			while (line != null) {
				source.append(line);
				line = sourceReader.readLine();
			}
		} catch (IOException e) {
			return;
		}
		sourceString = source.toString();
	}
	
	public String nextUrl() {
		if (sourceString == null) {
			return null;
		}
		cur = sourceString.indexOf(begFlag, cur);
		if (cur == -1) {
			return null;
		}
		cur += begFlag.length();
		int end = sourceString.indexOf(endFlag, cur);
		if (end != -1) {
			return sourceString.substring(cur, end);
		} else {
			return null;
		}
	}
}