package fr.eurecom.android.showsensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sm;
    private Sensor smLight;
    private Sensor gravity;
    private Sensor stepcounter;
    private Sensor motion;
    float sLightCurrentValue0=0.0f;
    private TextView textLight;
    private TextView textLight1;
    private TextView textLight2;
    private TextView textLight3;
    private TextView textLight4;
    private TextView textLight5;
    private TextView textLight6;
    private TextView textLight7;
    private TextView textLight8;
    private TextView textLight9;
    private TextView textLight10;
    private Sensor pressure;
    private Sensor magnetic;
    private Boolean isPressureSensorAvailable;
    private Sensor accelerometer;
    private int stepcount=0;
    boolean running= false;
    float gravity1;
    float motion1;
    float acc1;
    float acc2;
    float acc3;
   // String sensor_error = getResources().getString(R.string.error_no_sensor);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList) {
            sensorText.append(currentSensor.getName()).
                    append(System.getProperty("line.separator"));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        sm.unregisterListener(this);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                textLight.setText("value:"+ event.values[0]);
                sLightCurrentValue0=event.values[0];
                if(sLightCurrentValue0<5000){
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
                if(sLightCurrentValue0>=5000 && sLightCurrentValue0<10000){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                if(sLightCurrentValue0>=10000 && sLightCurrentValue0<15000){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                if(sLightCurrentValue0>=15000 && sLightCurrentValue0<20000){
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
                if(sLightCurrentValue0>=20000 && sLightCurrentValue0<25000){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                if(sLightCurrentValue0>=25000 && sLightCurrentValue0<30000){
                    getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
                }
                if(sLightCurrentValue0>=30000 && sLightCurrentValue0<35000){
                    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                }
                if(sLightCurrentValue0>=35000 && sLightCurrentValue0<=40000){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }

                break;
            case Sensor.TYPE_ACCELEROMETER:
                textLight1.setText("value:"+ event.values[0]);
                textLight2.setText("value:"+event.values[1]);
                textLight3.setText("value:"+ event.values[2]);
                break;
            case Sensor.TYPE_PRESSURE:
                textLight4.setText(event.values[0]+ "hPa");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                textLight5.setText("value:"+ event.values[0]);
                textLight6.setText("value:"+event.values[1]);
                textLight7.setText("value:"+ event.values[2]);
                break;
            case Sensor.TYPE_GRAVITY:
                textLight8.setText("value:"+ event.values[0]);

                break;
            case Sensor.TYPE_STEP_COUNTER:
                stepcount= (int) event.values[0];
                textLight9.setText("value:"+ String.valueOf(stepcount));
                break;
            case Sensor.TYPE_MOTION_DETECT:
                textLight10.setText("value:"+ event.values[0]);
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void getData(View view) {
        switch (view.getId()) {
            case R.id.button1:
                textLight = (TextView) findViewById(R.id.editTextTextPersonName6);
                smLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
                sm.registerListener(this, smLight,SensorManager.SENSOR_DELAY_NORMAL);
                if (smLight == null) {
                    textLight.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE LIGHT SENSOR",Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.button2:
                textLight8 = (TextView) findViewById(R.id.editTextTextPersonName11);
                gravity=sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
                sm.registerListener(MainActivity.this,gravity,SensorManager.SENSOR_DELAY_NORMAL);
                if (gravity == null) {
                    textLight8.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE GRAVITY SENSOR",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button3:
                textLight9 = (TextView) findViewById(R.id.editTextTextPersonName7);

                stepcounter=sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                if(stepcounter ==null)
                {
                    textLight9.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE STEPCOUNTER SENSOR",Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.button4:
                textLight10 = (TextView) findViewById(R.id.editTextTextPersonName4);
                motion=sm.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);
                if(motion ==null)
                {
                    textLight10.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE MOTION SENSOR",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.button5:
                textLight1 = (TextView) findViewById(R.id.editTextTextPersonName8);
                textLight2 = (TextView) findViewById(R.id.editTextTextPersonName16);
                textLight3 = (TextView) findViewById(R.id.editTextTextPersonName15);
                accelerometer= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sm.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
                if (accelerometer == null) {
                    textLight1.setText("Sensor not found");
                    textLight2.setText("Sensor not found");
                    textLight3.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE ACCELEROMETER SENSOR",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.button6:
                textLight5 = (TextView) findViewById(R.id.editTextTextPersonName10);
                textLight6 = (TextView) findViewById(R.id.editTextTextPersonName17);
                textLight7 = (TextView) findViewById(R.id.editTextTextPersonName18);
                magnetic=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sm.registerListener(MainActivity.this,magnetic,SensorManager.SENSOR_DELAY_NORMAL);
                if (magnetic == null) {
                    textLight5.setText("Sensor not found");
                    textLight6.setText("Sensor not found");
                    textLight7.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE MAGNETIC SENSOR",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button7:
                textLight4 = (TextView) findViewById(R.id.editTextTextPersonName9);
                pressure=sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
                sm.registerListener(this,pressure,SensorManager.SENSOR_DELAY_NORMAL);
                if(pressure ==null)
                {
                    textLight4.setText("Sensor not found");
                    Toast.makeText(this,"YOUR DEVICE DOESN'T HAVE PRESSURE SENSOR",Toast.LENGTH_LONG).show();
                }
                break;


        }
    }

}