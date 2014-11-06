package com.example.ps5;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.text.Editable;

public class MainActivity extends Activity implements OnItemSelectedListener {
	EditText Initial_Investment;
	EditText Annual_Interest_Rate;
	TextView Future_Value;
	Spinner Years_Value;
	Object TotalYears;

	Integer[] SPNNum = new Integer[20];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Initial_Investment = (EditText) findViewById(R.id.editText1);
		Annual_Interest_Rate = (EditText) findViewById(R.id.editText2);
		Future_Value = (TextView) findViewById(R.id.textView4);
		Initial_Investment.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable editable) {
				Future_Value.setText(" ");
			}

			@Override
			public void beforeTextChanged(CharSequence charSequence, int i,
					int i2, int i3) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i2,
					int i3) {
			}
		});
		Annual_Interest_Rate.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable editable) {
				Future_Value.setText(" ");
			}

			@Override
			public void beforeTextChanged(CharSequence charSequence, int i,
					int i2, int i3) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i2,
					int i3) {
			}
		});
		for (int i = 0; i < 20; i++) {
			SPNNum[i] = i + 1;
		}
		ArrayAdapter<Integer> Adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, SPNNum);
		Years_Value = (Spinner) findViewById(R.id.spinner1);
		Years_Value.setAdapter(Adapter);
		Years_Value.setOnItemSelectedListener(this);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View Bird) {
				double Initial;
				double rate;
				Editable initial = Initial_Investment.getText();
				Editable rt = Annual_Interest_Rate.getText();
				if (initial.length() != 0 && rt.length() != 0) {
					Initial = Double.parseDouble(initial.toString());
					rate = Double.parseDouble(rt.toString());
//------------------------------------------------------------------------------------
					double result = (double) (Initial * Math.pow((1 + rate / 100),
							(Integer) TotalYears));
					result = Math.round(result);
//------------------------------------------------------------------------------------
					Future_Value.setText("Future Value is $ "
							+ ((Double) result).toString());
				}
			}
		});
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = Years_Value.getSelectedItemPosition();
		TotalYears = Years_Value.getItemAtPosition(position);
		Future_Value.setText(" ");
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}