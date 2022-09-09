package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;

public class Mapa extends AppCompatActivity {

    Button botaovoltardois;

    {
        Bundle savedInstanceState = null;
        super.onCreate(null);
        setContentView(R.layout.activity_mapa);

        botaovoltardois = findViewById(R.id.botaovoltardois);

        botaovoltardois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Mapa.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtCidade;
    private TextView txtEstado;
    private TextView txtPais;

    private Location location;
    private LocationManager locationManager;

    private Address endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
        txtEstado = (TextView) findViewById(R.id.txtEstado);
        txtPais = (TextView) findViewById(R.id.txtPais);

        double latitude = 0.0;
        double longitude = 0.0;

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Solicitar permissão ao usuário.

    } else {
        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        location =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if (location != null) {

            longitude = location.getLongitude();
            latitude = location.getLatitude();

        }

        txtLongitude.setText("Longitude: " + longitude);
        txtLatitude.setText("Latitude: " + latitude);

        try {

            endereco = buscarEndereco(latitude, longitude);

            txtCidade.setText("Cidade: " + endereco.getLocality());
            txtEstado.setText("Estado: " + endereco.getAdminArea());
            txtPais.setText("País..: " + endereco.getCountryName());

        } catch (IOException e) {

            Log.i("GPS", e.getMessage());
        }
    }

        public Address buscarEndereco(double latitude, double longitude)
            throws IOException {

            Geocoder geocoder;
            Address address = null;
            List<Address> addresses;

            geocoder = new Geocoder(getApplicationContext());

            addresses = geocoder.getFromLocation(latitude,
                    longitude,1 );
            if (addresses.size() > 0) {
                address = addresses.get(0);
            }
            return address;
        }

    private ImageView imageView;
    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private float azimuth = 0f;
    private float currectAzimuth = 0f;
    private SensorManager mSensorManager;

    {
         Bundle savedInstanceState = null;
         super.onCreate(null);
        setContentView(R.layout.activity_mapa);

        imageView = (ImageView) findViewById(R.id.bussola);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        final float alpha = 0.97f;
        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[0];
                mGravity[1] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[1];
                mGravity[2] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[2];
            }
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[0];
                mGeomagnetic[1] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[1];
                mGeomagnetic[2] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[2];
            }

            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;


                Animation anim = new RotateAnimation(-currectAzimuth, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                currectAzimuth = azimuth;

                anim.setDuration(500);
                anim.setRepeatCount(0);
                anim.setFillAfter(true);

                imageView.startAnimation(anim);
            }
        }
    }

}
