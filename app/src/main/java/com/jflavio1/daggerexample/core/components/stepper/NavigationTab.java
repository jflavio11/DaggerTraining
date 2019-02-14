package com.jflavio1.daggerexample.core.components.stepper;


import android.graphics.Canvas;
import android.graphics.Paint;

public class NavigationTab {
    public enum STATES {
        DEFAULT, WRONG, RIGHT, SKIPPED, CURRENT, PROCESSED
    }

    STATES states = STATES.DEFAULT;
    private Point point;
    private Paint textPaint;
    private Paint backgroundPaint;
    private Paint borderPaint;
    private String text;
    private NavigationBar navigationBar;
    private float borderSize = 2;
    private int textColor = 0xffffffff;
    private int backgroundColor = 0xffff5599;
    private int borderColor = 0xffff5599;
    private Paint.FontMetrics fontMetrics;

    public NavigationTab(NavigationBar navigationBar, Point point, String text, float borderSize) {
        this.borderSize = borderSize;
        this.text = text;
        this.navigationBar = navigationBar;
        this.point = point;
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(navigationBar.getTabTextSize());
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setLinearText(false);
        textPaint.setAntiAlias(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStrokeWidth(2);
        backgroundPaint.setStrokeJoin(Paint.Join.BEVEL);
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        backgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        backgroundPaint.setAntiAlias(true);

        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderSize);
        borderPaint.setStrokeJoin(Paint.Join.BEVEL);
        borderPaint.setStrokeCap(Paint.Cap.ROUND);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);

        fontMetrics = textPaint.getFontMetrics();
    }

    public void onDrawBackground(Canvas canvas, float p){
        if(navigationBar.isBorder()) {
            canvas.drawCircle(point.getX(), point.getY(), point.getRadius() * p, borderPaint);
        }
        if(!navigationBar.isOnlyBorder()) {
            canvas.drawCircle(point.getX(), point.getY(), point.getRadius() * p, backgroundPaint);
        }
    }

    public void onDrawForeground(Canvas canvas) {
        final float before = -fontMetrics.ascent;
        final float after = fontMetrics.descent + fontMetrics.leading;
        canvas.drawText(text, point.getX(), point.getY() + (before - after) / 2, textPaint);
    }


    public STATES getStates() {
        return states;
    }

    public void setStates(STATES states) {
        this.states = states;
        switch (states) {
            case DEFAULT:
                setBackgroundColor(navigationBar.getStateColor()[0]);
                setBorderColor(navigationBar.getStateBorderColor()[0]);
                setTextColor(navigationBar.getStateTextColor()[0]);
                break;
            case WRONG:
                setBackgroundColor(navigationBar.getStateColor()[1]);
                setBorderColor(navigationBar.getStateBorderColor()[1]);
                setTextColor(navigationBar.getStateTextColor()[1]);
                break;
            case RIGHT:
                setBackgroundColor(navigationBar.getStateColor()[2]);
                setBorderColor(navigationBar.getStateBorderColor()[2]);
                setTextColor(navigationBar.getStateTextColor()[2]);
                break;
            case SKIPPED:
                setBackgroundColor(navigationBar.getStateColor()[3]);
                setBorderColor(navigationBar.getStateBorderColor()[3]);
                setTextColor(navigationBar.getStateTextColor()[3]);
                break;
            case CURRENT:
                setBackgroundColor(navigationBar.getStateColor()[4]);
                setBorderColor(navigationBar.getStateBorderColor()[4]);
                setTextColor(navigationBar.getStateTextColor()[4]);
                break;
            case PROCESSED:
                setBackgroundColor(navigationBar.getStateColor()[5]);
                setBorderColor(navigationBar.getStateBorderColor()[5]);
                setTextColor(navigationBar.getStateTextColor()[5]);
                break;
        }
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        backgroundPaint.setColor(backgroundColor);
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        borderPaint.setColor(borderColor);
    }

    public float getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
