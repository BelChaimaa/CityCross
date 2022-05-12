package com.example.myfirstapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

public class Orientation implements SensorEventListener {

    public interface Listener {
        void onOrientationChanged(double azimuth);
    }
    //
    double mAzimuth;
    private SensorManager mSensorManager;
    private Sensor mRotationV, mAccelerometer, mMagnetometer;
    boolean haveSensor = false, haveSensor2 = false;
    float[] rMat = new float[9];
    float[] orientation = new float[3];
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;
    //
    private static final int SENSOR_DELAY_MICROS = 1000000; // 16ms

    private final WindowManager mWindowManager;

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
        mSensorManager.registerListener(this, mRotationSensor, SENSOR_DELAY_MICROS, SENSOR_DELAY_MICROS);
        mSensorManager.registerListener(this, mAccelerometer, SENSOR_DELAY_MICROS, SENSOR_DELAY_MICROS);
        mSensorManager.registerListener(this, mMagnetometer, SENSOR_DELAY_MICROS, SENSOR_DELAY_MICROS);
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

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(rMat, event.values);
            mAzimuth = Math.round(Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) * 1000.0) / 1000.0;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }
        if (mLastAccelerometerSet && mLastMagnetometerSet) {
            SensorManager.getRotationMatrix(rMat, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(rMat, orientation);
            mAzimuth = Math.round(Math.toDegrees(SensorManager.getOrientation(rMat, orientation)[0]) * 1000.0) / 1000.0;
        }
        mListener.onOrientationChanged(mAzimuth);
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
        //float azimuth = (float) (Math.toDegrees(SensorManager.getOrientation(rotationMatrix, orientation)[0]) + 360) % 360;

        mListener.onOrientationChanged(azimuth);
    }
}