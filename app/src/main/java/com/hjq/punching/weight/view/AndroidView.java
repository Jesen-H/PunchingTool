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

        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        canvas.drawColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#B3E723"));

        //头部
        rectF.set(halfWidth - 50, halfHeight - 50, halfWidth + 50, halfHeight + 50);
        canvas.drawArc(rectF, 0, -180, true, paint);

        //眼睛
        paint.setColor(Color.WHITE);
        canvas.drawCircle(halfWidth - 25, halfHeight - 25, 5, paint);
        canvas.drawCircle(halfWidth + 25, halfHeight - 25, 5, paint);

        //触角
        paint.setColor(Color.parseColor("#B3E723"));
        paint.setStrokeWidth(5f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(halfWidth - 35, halfHeight - 60, halfWidth - 5, halfHeight - 10, paint);
        canvas.drawLine(halfWidth + 35, halfHeight - 60, halfWidth + 5, halfHeight - 10, paint);

        //身体
        rectF.set(halfWidth - 50, halfHeight + 8, halfWidth + 50, halfHeight + 60);
        canvas.drawRect(rectF, paint);
        rectF.set(halfWidth - 50, halfHeight + 50, halfWidth + 50, halfHeight + 90);
        canvas.drawRoundRect(rectF, 10, 10, paint);

        //手
        rectF.set(halfWidth - 75, halfHeight + 6, halfWidth - 55, halfHeight + 80);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        rectF.set(halfWidth + 55, halfHeight + 6, halfWidth + 75, halfHeight + 80);
        canvas.drawRoundRect(rectF, 10, 10, paint);

        //腿
        rectF.set(halfWidth - 33, halfHeight + 80, halfWidth - 10, halfHeight + 135);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        rectF.set(halfWidth + 10, halfHeight + 80, halfWidth + 33, halfHeight + 135);
        canvas.drawRoundRect(rectF, 10, 10, paint);
    }
}
