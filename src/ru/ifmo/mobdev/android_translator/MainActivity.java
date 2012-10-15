package ru.ifmo.mobdev.android_translator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	Button Translate;
	EditText editText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Translate = (Button) findViewById(R.id.Translate);
        
        editText = (EditText) findViewById(R.id.expr);
        
        Translate.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		    case R.id.Translate:
		    	Intent intent = new Intent(this, ResultActivity.class);
		    	intent.putExtra("expr", editText.getText().toString());
		        startActivity(intent); 
		      break;
		    default:
		      break;
		    }
	}
}

