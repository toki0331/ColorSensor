package com.example.toki.colorauto;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener{
TextView textView;
SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.withText);
        sensorManager= (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION)
        {
            int a= (int)(sensorEvent.values[0]);
            int b =(int)(sensorEvent.values[1]);
            int c=(int)(sensorEvent.values[2]);
            textView.setBackgroundColor(Color.rgb(a,b,c));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
