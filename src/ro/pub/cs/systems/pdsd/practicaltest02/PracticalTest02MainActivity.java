package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends Activity {
	// Server widgets
	private EditText     serverPortEditText       = null;
	private Button       connectButton            = null;
	
	// Client widgets
	private EditText     operator1    = null;
	private EditText     operator2       = null;
	//private EditText     cityEditText             = null;
	//private Spinner      informationTypeSpinner   = null;
	private Button adunare = null;
	private Button inmultire = null;
	private TextView     weatherForecastTextView  = null;
	
	private ServerThread serverThread             = null;
	private ClientThread clientThread             = null;
	
	private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
	private class ConnectButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String serverPort = serverPortEditText.getText().toString();
			if (serverPort == null || serverPort.isEmpty()) {
				Toast.makeText(
					getApplicationContext(),
					"Server port should be filled!",
					Toast.LENGTH_SHORT
				).show();
				return;
			}
			
			serverThread = new ServerThread(Integer.parseInt(serverPort));
			if (serverThread.getServerSocket() != null) {
				serverThread.start();
			} else {
				Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not creat server thread!");
			}
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_main);
		
		serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
		connectButton = (Button)findViewById(R.id.connect_button);
		connectButton.setOnClickListener(connectButtonClickListener);
		
		operator1 = (EditText)findViewById(R.id.client_address_edit_text);
		operator2 = (EditText)findViewById(R.id.client_port_edit_text);
		adunare = (Button)findViewById(R.id.get_weather_forecast_button);
		inmultire = (Button)findViewById(R.id.get_weather_forecast_button2);
		//adunare.setOnClickListener(adunareClickListener);
		//weatherForecastTextView = (TextView)findViewById(R.id.);
	}
	
	@Override
	protected void onDestroy() {
		if (serverThread != null) {
			serverThread.stopThread();
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_main, menu);
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
