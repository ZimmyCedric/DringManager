package fr.projet.dringmanager;

import java.util.List;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

public class IntentServiceSound extends IntentService {

	SensorManager sensorManager;
	Sensor accelerometerSensor;
	boolean accelerometerPresent;
	
	// L'icône sera une petite loupe
	int icon = R.drawable.ic_launcher;
	// Le premier titre affiché
	CharSequence tickerText = "Titre de la notification";
	// Daté de maintenant
	long when = System.currentTimeMillis();

	private final static String TAG = "IntentServiceSound";

	public IntentServiceSound() {
		super(TAG);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(sensorList.size() > 0){
			accelerometerPresent = true;
			accelerometerSensor = sensorList.get(0);
		}
		else{
			accelerometerPresent = false;  
		}
		
		if(accelerometerPresent){
			sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);  
		}

	}

	private SensorEventListener accelerometerListener = new SensorEventListener(){

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent arg0) {
			// TODO Auto-generated method stub
			float z_value = arg0.values[2];
			if (z_value < -9){
				Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				// Vibrate for 500 milliseconds
				v.vibrate(200);
			}
		}
	};

}
