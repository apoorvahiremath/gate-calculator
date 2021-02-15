package com.example.apoorva.myapplication;

/**
 * Created by Apoorva on 8/29/2016.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by Apoorva on 8/9/2016.
 */
public class TextDrawable extends Drawable {
    private String text;
    private Paint paint;

    public TextDrawable(String text){
        this.text = text;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(24f);
        paint.setAntiAlias(true);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, 0, 6, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
