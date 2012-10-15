package ru.ifmo.mobdev.android_translator;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;



public class ResultActivity extends Activity {
	
	TextView textView;
	Bundle extras;
	Translator translator;
	ImageSearcher ImgSrch;
	String Expr = null;
	String translation = null;
	
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
		
		ImgSrch = new ImageSearcher(Expr);
		
		DrawImages();
	}
	
	private void DrawImages () {
		new DownloadImageTask((ImageView) findViewById(R.id.img0)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img1)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img2)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img3)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img4)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img5)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img6)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img7)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img8)).execute(ImgSrch.nextUrl());
		new DownloadImageTask((ImageView) findViewById(R.id.img9)).execute(ImgSrch.nextUrl());
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
		    this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
		    String urldisplay = urls[0];
		    Bitmap mIcon11 = null;
		    try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		    } catch (Exception e) {
		        Log.e("Error", e.getMessage());
		        e.printStackTrace();
		    }
		    return mIcon11;
		}

		protected void onPostExecute(Bitmap res) {
		    bmImage.setImageBitmap(res);
		}
	}
	
}
