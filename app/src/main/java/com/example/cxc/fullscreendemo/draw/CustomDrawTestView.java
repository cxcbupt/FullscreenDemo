package com.example.cxc.fullscreendemo.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义绘制
 * drawPath() and 圆弧
 * <p>
 * arcTo(RectF oval, float startAngle, float sweepAngle)
 * startAngle：开始角度，时钟3点钟为0度
 * sweepAngle:扫过的角度，正数表示顺时针方向，负数表示逆时针方向
 */
public class CustomDrawTestView extends View {
    private static final String TAG = "CustomDrawTestView";
    private Paint paint;

    public CustomDrawTestView(Context context) {
        this(context, null);
    }


    public CustomDrawTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDrawTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();

        path.moveTo(100, 100);

        //左下角
        RectF roundRectT = new RectF(100, 400, 200, 500);
        path.arcTo(roundRectT, 180, -90);


        //右下角
        roundRectT.set(400, 400, 500, 500);
        path.arcTo(roundRectT, 90, -90);

        //右上角
        roundRectT.set(400, 100, 500, 200);
        path.arcTo(roundRectT, 0, -90);

        //闭合
        path.close();

        canvas.drawPath(path, paint);
    }
}
