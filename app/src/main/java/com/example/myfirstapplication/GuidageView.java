package com.example.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GuidageView extends View {
    Paint paint;

    private double az;
    private double dir;

    public GuidageView(Context context, double azimuth, double direction) {
        super(context);

        paint = new Paint();
        //paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        az = azimuth;
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

        double angle;
        try {
            angle = Math.toRadians(az - dir);
        } catch(NullPointerException e) {
            angle = 0;
            Log.d("angle", String.valueOf(e));
        }
        int longueurFleche = 250;
        paint.setColor(Color.BLUE);
        canvas.drawLine(getWidth()/2, 350, getWidth()/2, getHeight()-50, paint);
        paint.setColor(Color.YELLOW);
        drawArrow(canvas, getWidth()/2, (getHeight()-350)/2+350, (float) (getWidth()/2+longueurFleche*Math.sin(angle)), (float) ((getHeight()-350)/2+350-longueurFleche*Math.cos(angle)), paint);
    }
}
