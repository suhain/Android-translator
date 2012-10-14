package ru.ifmo.mobdev;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "ru.ifmo.mobdev.MESSAGE";

    @SuppressLint({ "NewApi", "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    
    public void sendMessage(View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	Translator translator = new Translator();
    	message = translator.translate(message);
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
}
