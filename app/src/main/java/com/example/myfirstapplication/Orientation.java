package com.example.myfirstapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class Orientation implements SensorEventListener {

    public interface Listener {
        void onOrientationChanged(float azimuth);
    }

    private static final int SENSOR_DELAY_MICROS = 100000 * 1000; // 16ms

    private final WindowManager mWindowManager;

    private final SensorManager mSensorManager;

    @Nullable
    private final Sensor mRotationSensor;

    private int mLastAccuracy;
    private Listener mListener;

    public Orientation(Activity activity) {
        mWindowManager = activity.getWindow().getWindowManager();
        mSensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);

        // Can be null if the sensor hardware is not available
        mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    public void startListening(Listener listener) {
        if (mListener == listener) {
            return;
        }
        mListener = listener;
        if (mRotationSensor == null) {
            return;
        }
        mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY_MICROS);
    }

    public void stopListening() {
        mSensorManager.unregisterListener(this);
        mListener = null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (mLastAccuracy != accuracy) {
            mLastAccuracy = accuracy;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mListener == null) {
            return;
        }
        if (mLastAccuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
            return;
        }
        // si le capteur dont la valeur a été modifiée est le capteur de rotation
        if (event.sensor == mRotationSensor) {
            // event.values renvoi le vecteur de rotationmesuré par le capteur de rotation
            updateOrientation(event.values);
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private void updateOrientation(float[] rotationVector) {
        // Matrice de rotation des coordonnées du téléphone aux coordonnées du monde définies comme tel: x -> Est, y -> Nord magnétique et z -> ciel (base orthonormale)
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector);

        float[] orientation = new float[3];
        SensorManager.getOrientation(rotationMatrix, orientation);

        // conversion en degrés
        float azimuth = (float)Math.toDegrees(orientation[0]);

        mListener.onOrientationChanged(azimuth);
    }
}
