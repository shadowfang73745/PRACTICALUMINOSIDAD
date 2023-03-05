package com.example.practicasensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity {

    ConstraintLayout fondopantallaS2;
    Button Vuelve;
    Sensor sensorS;
    SensorManager admistrarsensor;
    SensorEventListener Eventoss;
    TextView etiquetaresultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lumini);
        fondopantallaS2 = findViewById(R.id.fonpantalla2);
        etiquetaresultados = findViewById(R.id.Result);
        admistrarsensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorS = admistrarsensor.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (sensorS == null) {
            Toast.makeText(MainActivity.this, "No se detecto el sensor ", Toast.LENGTH_SHORT).show();
            //  finish();
        } else {
            Toast.makeText(MainActivity.this, "El sensor se a detectado", Toast.LENGTH_SHORT).show();
        }
        Eventoss = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] );
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        inicializarSensor();
        Vuelve= findViewById(R.id.Regresa);
        Vuelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void inicializarSensor(){
        admistrarsensor.registerListener(Eventoss,sensorS,SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void  detenerSensor(){
        admistrarsensor.unregisterListener(Eventoss,sensorS);
    }

    @Override
    protected void onResume() {
        inicializarSensor();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }

}