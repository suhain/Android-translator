package ru.ifmo.mobdev.android_translator;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.widget.EditText;;

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
        EditText editText = (EditText) findViewById(R.id.editText1);
        ImageSearcher imgSearcher = new ImageSearcher("weasel"); 
        for (int i = 0; i != 10; ++i) {
        	String url = imgSearcher.nextUrl();
        	editText.append(url + "\n\n");
        	Log.i("Link", url);
        }
    }
}
