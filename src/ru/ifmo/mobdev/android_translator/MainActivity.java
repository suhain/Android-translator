package ru.ifmo.mobdev.android_translator;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] result = ImageSearcher.searchImages("weasel");
        EditText editText = (EditText) findViewById(R.id.editText1);
        if (result != null) {
	        for (int i = 0; i != 10 && result[i] != null; ++i) {
	        	editText.append(result[i] + "\n\n");
	        }
        } else {
        	editText.setText("error!");
        }
    }
}
