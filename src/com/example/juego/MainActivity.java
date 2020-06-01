package com.example.juego;



import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {
	private TextView texto1;
    private SensorManager sensorManager;
    MiGLSurfaceView superficie;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		superficie = new MiGLSurfaceView(this, this);
		//setContentView(superficie);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		MediaPlayer mediaPlayer=MediaPlayer.create(this, R.raw.coloso);
		mediaPlayer.setVolume(0.5f, 0.5f);
		mediaPlayer.start();

		
		RelativeLayout pantalla = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
	    pantalla.addView(superficie);   
		texto1 = (TextView) pantalla.findViewById(R.id.texto1);
        texto1.bringToFront();
        //texto1.setText("kadhfasd");
        setContentView(pantalla);
    }
	public void actualizaTexto(final String s) {
    	runOnUiThread(new Runnable() {
    		@Override
    		public void run() {
    			texto1.setText(s);
    			}
    		});
    	}
    @Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
    	if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			superficie.renderiza.acelerometroX = e.values[0];
			superficie.renderiza.acelerometroY=e.values[1];
		}
	}
    
    
    @Override
	public void onAccuracyChanged(Sensor e, int arg1) {	}
    @Override
	protected void onResume() {			
		super.onResume();
		superficie.onResume();
		sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {			
		super.onPause();
		superficie.onPause();
		sensorManager.unregisterListener(this);
	}
}
class MiGLSurfaceView extends GLSurfaceView {

	public Renderiza renderiza;
	
	public MiGLSurfaceView(Context contexto, MainActivity ma) {
		super(contexto);
		renderiza = new Renderiza(ma);
		setRenderer(renderiza);
		requestFocus();
		setFocusableInTouchMode(true);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}

