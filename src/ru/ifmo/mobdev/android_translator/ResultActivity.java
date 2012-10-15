package ru.ifmo.mobdev.android_translator;

import java.io.InputStream;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;



public class ResultActivity extends Activity {
	
	TextView textView;
	Bundle extras;
	Translator translator;
	String Expr = null;
	String translation;
	String[] links;
	ImageView picture;
	
	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultactivity);
		
		textView = (TextView) findViewById(R.id.Translation);
		extras = getIntent().getExtras();
		Expr = extras.getString("expr").toString();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		translator = new Translator();
		translation = translator.translate(Expr);
		
		textView.setText(Expr + " - " + translation);
		links = ImageSearcher.searchImages(Expr);
		
		picture = (ImageView) findViewById(R.id.img0);
		picture.setImageDrawable(LoadImage(links[0]));
		
		picture = (ImageView) findViewById(R.id.img1);
		picture.setImageDrawable(LoadImage(links[1]));
		
		picture = (ImageView) findViewById(R.id.img2);
		picture.setImageDrawable(LoadImage(links[2]));
		
		picture = (ImageView) findViewById(R.id.img3);
		picture.setImageDrawable(LoadImage(links[3]));
		
		picture = (ImageView) findViewById(R.id.img4);
		picture.setImageDrawable(LoadImage(links[4]));
		
		picture = (ImageView) findViewById(R.id.img5);
		picture.setImageDrawable(LoadImage(links[5]));
		
		picture = (ImageView) findViewById(R.id.img6);
		picture.setImageDrawable(LoadImage(links[6]));
		
		picture = (ImageView) findViewById(R.id.img7);
		picture.setImageDrawable(LoadImage(links[7]));
		
		picture = (ImageView) findViewById(R.id.img8);
		picture.setImageDrawable(LoadImage(links[8]));
		
		picture = (ImageView) findViewById(R.id.img9);
		picture.setImageDrawable(LoadImage(links[9]));
	}
	
	private Drawable LoadImage(String url) {
 		try{
 			InputStream is = (InputStream) new URL(url).getContent();
 			Drawable d = Drawable.createFromStream(is, "src");
 			return d;
 		}catch (Exception e) {
 			return null;
 		}
 	}
}
