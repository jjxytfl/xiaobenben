package com.example.xiaobenben.control;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class TomatoClockView extends View {
    private static final String TAG = "TomatoClockView";

    private Paint arcPaint;  //圆弧画笔
    private Paint textPaint;  //时间文本画笔
    private int backgroundColor = Color.parseColor("#D1D1D1");
    private int arcColor = Color.BLUE;

    private int width;  //View的宽
    private int height;  //View的高
    private float centerX;  //View中心点的X坐标
    private float centerY;  //View中心点的Y坐标

    private float oldOffsetY;  //上一次MOVE事件结束位置和DOWN事件落点之间Y坐标的偏移量
    private float offsetY;  //本次MOVE事件结束位置和DOWN事件落点之间Y坐标的偏移量
    float touchedY;  //本次DOWN事件落点的Y坐标

    private static final int MAX_TIME = 60;  //最大倒计时长
    private static String textTime = "00:00";  //时间文本
    private long countDownTime;  //倒计时时长（毫秒）
    private float time;  //倒计时时长（分钟）

    private float sweepVelocity = 0;  //动画执行的完成度
    private ValueAnimator valueAnimator;  //属性动画对象

    private boolean isStarted;  //倒计时是否已开始

    private MyTimer mTimeCounter;  //倒计时器

    private class MyTimer extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            textTime = formatCountTime(millisUntilFinished);
            invalidate();

        }

        @Override
        public void onFinish() {
            textTime = "00:00";
            invalidate();
        }
    }

    //view是在JAVA代码中new的，则调用此构造函数
    public TomatoClockView(Context context) {
        super(context);
        init();
    }

    //View是在.xml文件中声明的，则调用此构造函数
    public TomatoClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TomatoClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TomatoClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        //定义LayoutParams为warp_content时的测量宽高，否则wrap_content会失效
        if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT
                && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            width = 400;
            height = 500;
        } else if(getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT){
            width = 400;
        } else if(getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
            height = 400;
        }


        //计算番茄钟的中心点
        centerX = getLeft() + width/2;
        centerY = getTop() + height/2;

        setMeasuredDimension(width, height);  //保存测量宽高
        Log.d(TAG, "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //对padding进行处理，否则padding属性将会失效
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //int radius = Math.min(width-paddingLeft-paddingRight, height-paddingTop-paddingBottom)/2;  //计算半径

        RectF rectF = new RectF();
        rectF.set(centerX-width/2 + paddingLeft, centerY-height/2 + paddingTop, centerX+width/2 - paddingRight, centerY+height/2 - paddingBottom);

        //绘制底部圆弧
        canvas.save();
        arcPaint.setColor(backgroundColor);
        canvas.drawArc(rectF, -90, 360, false, arcPaint);
        canvas.restore();

        //绘制倒计时圆弧
        canvas.save();
        arcPaint.setColor(arcColor);
        canvas.drawArc(rectF, -90, 360 * sweepVelocity, false, arcPaint);
        canvas.restore();

        //绘制时间文本
        canvas.save();
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        float baseline = (metrics.bottom - metrics.top)/2 + centerY - metrics.bottom;
        canvas.drawText(textTime, centerX, baseline, textPaint );
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isStarted){
            return true;
        }

        //获取屏幕高度
        WindowManager manager = (WindowManager) (getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        float screenHeight = metrics.heightPixels;

        float y = event.getY();  //获取触摸事件发生位置的y坐标

        //通过上下滑动来设置倒计时时间
        //原理：MOVE事件结束时的y坐标 - DOWN事件发生的y坐标，MAX_TIME*(所得值/屏幕高度)即为倒计时时间，负减正增
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                touchedY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                offsetY = y - touchedY;

                //可以通过多次滑动来调整时间
                float totalOffsetY = oldOffsetY + offsetY;
                if(totalOffsetY <= 0){
                    totalOffsetY = 0;
                } else if(totalOffsetY >= screenHeight){
                    totalOffsetY = screenHeight;
                }

                time = totalOffsetY/screenHeight*MAX_TIME;  //分钟

                textTime = formatTime((long)time);

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                oldOffsetY = offsetY;  //记录上次滑动的位移量，用以实现多次滑动调整时间
                countDownTime = (long)time * 60 * 1000;  //倒计时时长，毫秒
                break;
        }

        return true;
    }

    private void init(){
        initPaint();
        initValueAnimation();
    }

    private void initPaint(){
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);  //描边
        arcPaint.setStrokeWidth(30);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeWidth(20);
        textPaint.setTextSize(180);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initValueAnimation(){
        valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepVelocity = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    /**
     * 开始倒计时
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void start(){
        if(!isStarted){
            isStarted = true;

            //设置动画时间并开始动画
            valueAnimator.setDuration(countDownTime);
            valueAnimator.start();

            //设置倒计时时间并开始倒计时
            mTimeCounter = new MyTimer(countDownTime, 1000);
            mTimeCounter.start();
        }

    }

    /**
     * 停止倒计时
     */
    public void stop(){
        mTimeCounter.cancel();
        valueAnimator.end();

        isStarted = false;
        time = 0f;
        textTime = "00:00";
        sweepVelocity = 0;
        oldOffsetY = 0;

        invalidate();
    }

    /**
     * 倒计时开始前格式化时间文本
     * @param time
     * @return
     */
    private String formatTime(long time){
        StringBuilder sb = new StringBuilder();

        if(time < 10){
            sb.append("0" + time + ":00");
        } else {
            sb.append(time + ":00");
        }

        return sb.toString();
    }

    /**
     * 在倒计时过程中格式化时间文本
     * @param time
     * @return
     */
    private static String formatCountTime(long time){

        StringBuilder sb = new StringBuilder();

        time = time/1000;  //毫秒转秒

        long min = time/60;  //分钟
        long second = time - min*60;  //秒

        if(min < 10){
            sb.append("0" + min + ":");
        } else {
            sb.append(min + ":");
        }

        if(second < 10){
            sb.append("0" + second);
        } else {
            sb.append(second);
        }


        return sb.toString();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}

