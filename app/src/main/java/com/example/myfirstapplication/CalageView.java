package com.example.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class CalageView extends View {
    Paint paint;

    private ArrayList<Object[]> v;
    private double dir;

    public CalageView(Context context, ArrayList<Object[]> villes, double direction) {
        super(context);

        paint = new Paint();
        //paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        v = villes;
        dir = direction;
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

        int longueurFleche = 250;
        paint.setColor(Color.YELLOW);
        drawArrow(canvas, getWidth()/2, (getHeight()-350)/2+350, getWidth()/2, (float) ((getHeight()-350)/2+350-longueurFleche), paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth()/2, (getHeight()-350)/2+350, longueurFleche + 50, paint);
        paint.setTextSize(40);

        int n = 0;
        Object[] last = v.get(0);

        for(Object[] ville: v){
            float angle = (float) (Double.parseDouble(ville[1].toString()) - dir);
            String nom = (String) ville[0];
            canvas.save();
            canvas.rotate(angle,getWidth()/2, (getHeight()-350)/2+350);
            if(Math.abs(dir - Double.parseDouble(last[1].toString())) > 3){
                n = 0;
            }
            if(Math.abs(angle) < 10 && n == 0){
                n = 1;
                last = ville;
                paint.setColor(Color.YELLOW);
                canvas.restore();
                canvas.drawText(nom, (float) (getWidth()/2) - paint.measureText(nom)/2, (float) ((getHeight()-350)/2 + 350 - (longueurFleche+200)), paint);
            } else {
                paint.setColor(Color.BLUE);
                canvas.drawText(nom, (float) (getWidth()/2) - paint.measureText(nom)/2, (float) ((getHeight()-350)/2 + 350 - (longueurFleche+100)), paint);
                canvas.restore();
            }
        }
    }

}