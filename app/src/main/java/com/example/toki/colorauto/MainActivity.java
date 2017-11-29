package com.example.toki.colorauto;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    double RAD2DEG=180/Math.PI;

    float[] rotationMatrix=new float[9];
    float[] gravity=new float[3];
    float[] geomagnetic =new float[3];
    float[] attitude=new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    textView=findViewById(R.id.tv);
        sensorManager= (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent)
                ;
            }
        });

    }
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch(sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
                geomagnetic= sensorEvent.values;
                break;

                case Sensor.TYPE_MAGNETIC_FIELD:
                    gravity=sensorEvent.values;
                    break;
        }
        if(geomagnetic!=null && gravity!=null)
        {

            SensorManager.getRotationMatrix(rotationMatrix,null,gravity,geomagnetic);
            SensorManager.getOrientation(rotationMatrix,attitude);

            int a= ((int)(attitude[0]*RAD2DEG)*(250/360));
            int b= ((int)(attitude[1]*RAD2DEG)*(250/360));
            int c= ((int)(attitude[2]*RAD2DEG)*(250/360));

            textView.setBackgroundColor(Color.rgb(a,b,c));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
