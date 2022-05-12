package com.example.myfirstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;


public class CalageView extends View {
    Paint paint;

    private final static int longueurFleche = 350;
    private final static int rayon = longueurFleche + 50;
    private final static int tailleText = 40;

    private TextView txtS12;

    private ArrayList<Object[]> v;
    private double dir;

    public CalageView(Context context, TextView txtS12, ArrayList<Object[]> villes, double direction) {
        super(context);

        paint = new Paint();
        //paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        v = villes;
        dir = direction;

        this.txtS12 = txtS12;
    }

    public static void drawArrow(Canvas canvas, float startX, float startY,
                                 float stopX, float stopY, Paint paint) {

        float dx = (stopX - startX);
        float dy = (stopY - startY);
        float rad = (float) Math.atan2(dy, dx);

        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.drawLine(stopX, stopY,//from   w w  w .ja v  a2 s.c om
                (float) (stopX + Math.cos(rad + Math.PI * 0.75) * 20),
                (float) (stopY + Math.sin(rad + Math.PI * 0.75) * 20),
                paint);
        canvas.drawLine(stopX, stopY,
                (float) (stopX + Math.cos(rad - Math.PI * 0.75) * 20),
                (float) (stopY + Math.sin(rad - Math.PI * 0.75) * 20),
                paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.YELLOW);
        drawArrow(canvas, getWidth()/2, (getHeight()-350)/2+350, getWidth()/2, (float) ((getHeight()-350)/2+350-longueurFleche), paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth()/2, (getHeight()-350)/2+350, rayon, paint);
        paint.setTextSize(tailleText);

        boolean oriente = false;

        for(Object[] ville: v){
            float angle = (float) (Double.parseDouble(ville[1].toString()) - dir);
            String nom = (String) ville[0];
            canvas.save();
            canvas.rotate(angle,getWidth()/2, (getHeight()-350)/2+350);
            if(Math.abs(angle) < 10){
                oriente = true;
                paint.setColor(Color.YELLOW);
                canvas.restore();
                canvas.drawText(nom, (float) (getWidth()/2) - paint.measureText(nom)/2, (float) ((getHeight()-350)/2 + 250 - rayon), paint);
                txtS12.setVisibility(VISIBLE);
                txtS12.setText(getResources().getString(R.string.distance, ville[2]));
            } else {
                paint.setColor(Color.BLUE);
                RectF rect = new RectF(getWidth()/2 - rayon, (getHeight()-350)/2+350 - rayon, getWidth()/2 + rayon, (getHeight()-350)/2+350 + rayon);
                Path path = new Path();
                path.addArc(rect, -90.0f, 270.0f);
                canvas.drawPath(path, paint);
                canvas.drawTextOnPath(nom, path, 0.0f, -20.0f, paint);
                //canvas.drawText(nom, (float) (getWidth()/2) - paint.measureText(nom)/2, (float) ((getHeight()-350)/2 + 350 - (longueurFleche+100)), paint);
                canvas.restore();
            }
        }
        if(!oriente){
            txtS12.setVisibility(INVISIBLE);
        }
    }

}