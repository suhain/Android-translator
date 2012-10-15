package ru.ifmo.mobdev.android_translator;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button Translate;
	EditText editText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Translate = (Button) findViewById(R.id.Translate);
		editText = (EditText) findViewById(R.id.expr);
		Translate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToResultScreen();
			}
		});
		editText.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					goToResultScreen();
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Exit")
				.setMessage("Do you want close this app?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MainActivity.super.onBackPressed();
							}
						}).setNegativeButton("No", null);
		builder.create().show();
	}

	
	public void goToResultScreen() {
		if((editText.getText().toString()) != null && !(editText.getText().toString().equals("")) && checkInputExpr(editText.getText().toString())) {
			Intent intent = new Intent(this, ResultActivity.class);
			intent.putExtra("expr", editText.getText().toString());
			startActivity(intent);
			finish();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Incorrect request")
				.setMessage("Type correct request")
				.setNeutralButton("Ok", null);
			builder.create().show();
		}
	}
	
	private boolean checkInputExpr(String inputExpr) {
		boolean ok = true;
		for (int i = 0; i < inputExpr.length(); i++) {
			char cur = inputExpr.charAt(i);
			if (!(('a' <= cur && cur <= 'z') || ('A' <= cur && cur <= 'Z') || cur == ' ')) {
				ok = false;
				break;
			}
		}
		return ok;
	}
}
