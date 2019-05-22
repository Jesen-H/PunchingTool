package com.hjq.punching.weight.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AndroidView extends View {
    private RectF rectF = new RectF();
    private Paint paint = new Paint();

    public AndroidView(Context context) {
        super(context);
    }

    public AndroidView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AndroidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#B3E723"));

        //头部
        rectF.set(getWidth() / 2 - 50, getHeight() / 2 - 50, getWidth() / 2 + 50, getHeight() / 2 + 50);
        canvas.drawArc(rectF, 0, -180, true, paint);

        //眼睛
        paint.setColor(Color.WHITE);
        canvas.drawCircle(getWidth() / 2 - 25, getHeight() / 2 - 25, 5, paint);
        canvas.drawCircle(getWidth() / 2 + 25, getHeight() / 2 - 25, 5, paint);

        //触角
        paint.setColor(Color.parseColor("#B3E723"));
        paint.setStrokeWidth(5f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine((getWidth() / 2) - 35, (getHeight() / 2) - 60, (getWidth() / 2) - 5, (getHeight() / 2) - 10, paint);
        canvas.drawLine((getWidth() / 2) + 35, (getHeight() / 2) - 60, (getWidth() / 2) + 5, (getHeight() / 2) - 10, paint);

        //身体
        rectF.set((getWidth() / 2) - 50, (getHeight() / 2) + 8, (getWidth() / 2) + 50, (getHeight() / 2) + 60);
        canvas.drawRect(rectF, paint);
        rectF.set((getWidth() / 2) - 50, (getHeight() / 2) + 50, (getWidth() / 2) + 50, (getHeight() / 2) + 90);
        canvas.drawRoundRect(rectF, 10, 10, paint);

        //手
        rectF.set((getWidth() / 2) - 75, (getHeight() / 2) + 6, (getWidth() / 2) - 55, (getHeight() / 2) + 80);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        rectF.set((getWidth() / 2) + 55, (getHeight() / 2) + 6, (getWidth() / 2) + 75, (getHeight() / 2) + 80);
        canvas.drawRoundRect(rectF, 10, 10, paint);

        //腿
        rectF.set((getWidth() / 2) - 33, (getHeight() / 2) + 80, (getWidth() / 2) - 10, (getHeight() / 2) + 135);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        rectF.set((getWidth() / 2) + 10, (getHeight() / 2) + 80, (getWidth() / 2) + 33, (getHeight() / 2) + 135);
        canvas.drawRoundRect(rectF, 10, 10, paint);
    }
}
