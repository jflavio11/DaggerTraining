package com.jflavio1.daggerexample.core.components.stepper;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.jflavio1.daggerexample.R;

import java.util.ArrayList;

import timber.log.Timber;

public class NavigationBar extends View {
    private static final int INDICATOR_ANIM_DURATION = 1500;
    final static int DEFAULT_INIT_COLOR = 0xff777777;
    final static int WRONG_INIT_COLOR = 0xff00ff00;
    final static int RIGHT_INIT_COLOR = 0xff00ff00;
    final static int SKIPPED_INIT_COLOR = 0xff0000ff;
    final static int CURRENT_INIT_COLOR = 0xffff5599;
    final static int PROCESSED_INIT_COLOR = 0xff6699ff;

    int stateColor[] = {DEFAULT_INIT_COLOR, WRONG_INIT_COLOR, RIGHT_INIT_COLOR, SKIPPED_INIT_COLOR, CURRENT_INIT_COLOR, PROCESSED_INIT_COLOR};
    int stateTextColor[] = {DEFAULT_INIT_COLOR, WRONG_INIT_COLOR, RIGHT_INIT_COLOR, SKIPPED_INIT_COLOR, CURRENT_INIT_COLOR, PROCESSED_INIT_COLOR};
    int stateBorderColor[] = {DEFAULT_INIT_COLOR, WRONG_INIT_COLOR, RIGHT_INIT_COLOR, SKIPPED_INIT_COLOR, CURRENT_INIT_COLOR, PROCESSED_INIT_COLOR};

    boolean isBorder = false;
    boolean onlyBorder = false;
    int width;
    int height;
    float borderSize = 2;
    private int currentPosition = 0;
    private int tabCount = 2;
    private float tabTextSize = 20;
    private int indicatorColor = 0xff00ff00;
    private int centralLineColor = 0xff888888;
    private float tabRadius = 30, tabPadding = 20, centralLineWidth;
    private float radiusMin = tabRadius / 3;
    private float headMoveOffset = 0.6f;
    private ArrayList<NavigationTab> navigationTabs;
    private int previousPosition = 0;
    private float offsetPosition;
    private float position;
    private float radiusOffset;
    private float footMoveOffset = 1 - headMoveOffset;
    private Paint indicatorPaint, linePant;
    private Path path;
    private Point headPoint;
    private Point footPoint;
    private OnTabClick onTabClick;
    private OnTabSelected onTabSelected;

    public NavigationBar(Context context) {
        super(context);
        init(null);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    /**
     * Setup all the attributes with the attributes set. Initialize Point's and Paint's.
     *
     * @param attrs {@link values/attrs.xml}
     */
    @SuppressLint("Recycle")
    private void init(AttributeSet attrs) {

        navigationTabs = new ArrayList<>();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NavigationBar);
        onlyBorder = a.getBoolean(R.styleable.NavigationBar_only_border, onlyBorder);
        isBorder = a.getBoolean(R.styleable.NavigationBar_enable_border, isBorder);

        int idItemTextColorId = a.getResourceId(R.styleable.NavigationBar_tab_color_array, 0);
        int idStateTextColor = a.getResourceId(R.styleable.NavigationBar_tab_text_color_array, 0);

        TypedArray colortype = null;
        if (idItemTextColorId != 0)
            colortype = getResources().obtainTypedArray(idItemTextColorId);

        TypedArray typeStateTextColor = null;
        if (idStateTextColor != 0)
            typeStateTextColor = getResources().obtainTypedArray(idStateTextColor);

        if (typeStateTextColor != null && typeStateTextColor.length() >= 6)
            for (int i = 0; i < 6; i++) {
                stateTextColor[i] = typeStateTextColor.getColor(i, stateTextColor[i]);
            }


        if (colortype != null && colortype.length() >= 6)
            for (int i = 0; i < 6; i++) {
                stateColor[i] = colortype.getColor(i, stateColor[i]);
            }


        tabTextSize = a.getDimension(R.styleable.NavigationBar_tab_text_size, tabTextSize);
        tabPadding = a.getDimension(R.styleable.NavigationBar_tab_padding, tabPadding);
        centralLineWidth = a.getDimension(R.styleable.NavigationBar_central_line_height, centralLineWidth);
        centralLineColor = a.getColor(R.styleable.NavigationBar_central_line_color, centralLineColor);
        indicatorColor = a.getColor(R.styleable.NavigationBar_tab_indicator_color, indicatorColor);
        borderSize = a.getDimension(R.styleable.NavigationBar_tab_strok_width, borderSize);
        a.recycle();

        headPoint = new Point();
        footPoint = new Point();
        path = new Path();
        indicatorPaint = new Paint();
        indicatorPaint.setStrokeJoin(Paint.Join.BEVEL);
        indicatorPaint.setStrokeCap(Paint.Cap.ROUND);
        indicatorPaint.setColor(indicatorColor);
        indicatorPaint.setAntiAlias(true);
        indicatorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        indicatorPaint.setStrokeWidth(5);

        linePant = new Paint();
        linePant.setStrokeJoin(Paint.Join.BEVEL);
        linePant.setStrokeCap(Paint.Cap.ROUND);
        linePant.setColor(centralLineColor);
        linePant.setAntiAlias(true);
        linePant.setStyle(Paint.Style.FILL_AND_STROKE);
        linePant.setStrokeWidth(centralLineWidth);

    }

    /**
     * Reset the item and the view.
     */
    public void resetItems() {
        navigationTabs.clear();
        currentPosition = 0;
        previousPosition = 0;
    }

    /**
     * Draw in the screen teh foreground and background colors and the points.
     *
     * @param canvas The canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int marginsLine = 20;
        canvas.drawLine(0f + marginsLine, height >> 1, (width * position) - marginsLine, height >> 1, linePant);
        for (int i = 0; i < navigationTabs.size(); i++)
            navigationTabs.get(i).onDrawBackground(canvas, position);
        drawSpring(canvas);
        for (int i = 0; i < navigationTabs.size(); i++)
            navigationTabs.get(i).onDrawForeground(canvas);
    }

    /**
     * Get the X position of a tab.
     *
     * @param position Position of the tab
     * @return the position of tab in X. Error => 0
     */
    private float getTabX(int position) {
        try {
            if (currentPosition > previousPosition)
                position = previousPosition;
            return navigationTabs.get(position).getPoint().getX();
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * Get the difference between 2 position in the array of steps.
     *
     * @return distance in pixels
     */
    private float getPositionDistance() {
        int c = previousPosition - currentPosition;
        c *= c < 0 ? -1 : 1;
        float tarX = navigationTabs.get(c).getPoint().getX();
        float oriX = navigationTabs.get(0).getPoint().getX();
        return oriX - tarX;
    }

    /**
     * I don't know what happens here xd.
     *
     * @param position Current position
     * @param positionOffset the new position
     */
    public void onPageScrolled(int position, float positionOffset) {
        if (previousPosition == currentPosition) {
            positionOffset = 1;
        }
        if (previousPosition < currentPosition)
            position -= 1;
        else
            positionOffset = 1 - positionOffset;
        if (position == -1) {
            position = 0;
            positionOffset = 1;
        }
        if (navigationTabs == null)
            return;
        if (position < navigationTabs.size() - 1) {
            // radius
            float radiusOffsetHead = 0.5f;
            if (positionOffset < radiusOffsetHead) {
                headPoint.setRadius(radiusMin);
            } else {
                headPoint.setRadius(
                        ((positionOffset - radiusOffsetHead) / (1 - radiusOffsetHead) * radiusOffset + radiusMin));
            }
            float radiusOffsetFoot = 0.5f;
            if (positionOffset < radiusOffsetFoot) {
                footPoint.setRadius(
                        (1 - positionOffset / radiusOffsetFoot) * radiusOffset + radiusMin);
            } else {
                footPoint.setRadius(radiusMin);
            }

            // x
            float headX = 1f;
            float acceleration = 0.5f;
            if (positionOffset < headMoveOffset) {
                float positionOffsetTemp = positionOffset / headMoveOffset;
                headX = (float) ((Math
                        .atan(positionOffsetTemp * acceleration * 2 - acceleration) + (Math
                        .atan(acceleration))) / (2 * (Math.atan(acceleration))));
            }
            headPoint.setX(getTabX(position) - headX * getPositionDistance());
            float footX = 0f;
            if (positionOffset > footMoveOffset) {
                float positionOffsetTemp = (positionOffset - footMoveOffset) / (1 - footMoveOffset);
                footX = (float) ((Math
                        .atan(positionOffsetTemp * acceleration * 2 - acceleration) + (Math
                        .atan(acceleration))) / (2 * (Math.atan(acceleration))));
            }
            footPoint.setX(getTabX(position) - footX * getPositionDistance());

            // reset radius
            if (positionOffset == 0) {
                headPoint.setRadius(tabRadius);
                footPoint.setRadius(tabRadius);
            }
        } else {
            headPoint.setX(getTabX(position));
            footPoint.setX(getTabX(position));
            headPoint.setRadius(tabRadius);
            footPoint.setRadius(tabRadius);
        }
    }


    /**
     * Performs the animation view for the tab point.
     *
     * @param canvas
     */
    private void drawSpring(Canvas canvas) {
        makePath();
        canvas.drawPath(path, indicatorPaint);
        canvas.drawCircle(headPoint.getX(), headPoint.getY(), headPoint.getRadius(),
                indicatorPaint);
        canvas.drawCircle(footPoint.getX(), footPoint.getY(), footPoint.getRadius(),
                indicatorPaint);

    }

    /**
     * Setup to perform the animation between 2 tab points.
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        tabRadius = (height - getPaddingTop() - getPaddingBottom()) / 2;
        width = (int) ((tabRadius * 2 + tabPadding) * tabCount + getPaddingRight() + getPaddingLeft() - tabPadding);
        int y = height >> 1;
        int x = (int) (getPaddingLeft() + (navigationTabs.size() == 0 ? tabRadius : (tabRadius * 2 + tabPadding) * navigationTabs.size() + tabRadius));
        headPoint.setX(x);
        headPoint.setY(y);
        footPoint.setX(x);
        footPoint.setY(y);
        for (int i = navigationTabs.size(); i < tabCount && tabCount > navigationTabs.size(); i++) {
            Point point = new Point();
            point.setX(x);
            point.setY(y);
            point.setRadius(tabRadius);
            NavigationTab navigationTab = new NavigationTab(this, point, (i + 1) + "", borderSize);
            navigationTab.setStates(NavigationTab.STATES.DEFAULT);
            if (i < currentPosition)
                navigationTab.setStates(NavigationTab.STATES.PROCESSED);
            navigationTabs.add(navigationTab);
            x += tabRadius * 2 + tabPadding;
        }

        radiusMin = tabRadius / 3;
        radiusOffset = tabRadius - radiusMin;

        setMeasuredDimension(width, height);
        animateViewR(INDICATOR_ANIM_DURATION, currentPosition);
    }

    /**
     * Map the action over the view.
     *
     * @param e The motion event to catch the action down.
     * @return if its OK => True.
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (onTabClick == null)
            return false;
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int touchPosition = -1, p = -1;
                for (int i = 0; i < navigationTabs.size(); i++) {
                    if (navigationTabs.get(i).getPoint().getRect().contains(x, y)) {
                        touchPosition = i;
                        previousPosition = currentPosition;
                        p = previousPosition;
                        currentPosition = i;
                        if (previousPosition != -1)
                            navigationTabs.get(previousPosition).setStates(NavigationTab.STATES.PROCESSED);
                        navigationTabs.get(i).setStates(NavigationTab.STATES.CURRENT);
                        break;
                    }
                }
                if (touchPosition != -1) {
                    onTabClick.onTabClick(touchPosition, navigationTabs.get(p), navigationTabs.get(touchPosition));
                    animateViewR(INDICATOR_ANIM_DURATION, touchPosition);
                } else
                    invalidate();
                break;
        }
        return true;
    }

    /**
     * Setup the path to define the position and dimension of the view.
     */
    private void makePath() {
        float headOffsetX = (float) (headPoint.getRadius() * Math.sin(Math.atan(
                (footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float headOffsetY = (float) (headPoint.getRadius() * Math.cos(Math.atan(
                (footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float footOffsetX = (float) (footPoint.getRadius() * Math.sin(Math.atan(
                (footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float footOffsetY = (float) (footPoint.getRadius() * Math.cos(Math.atan(
                (footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float x1 = headPoint.getX() - headOffsetX;
        float y1 = headPoint.getY() + headOffsetY;

        float x2 = headPoint.getX() + headOffsetX;
        float y2 = headPoint.getY() - headOffsetY;

        float x3 = footPoint.getX() - footOffsetX;
        float y3 = footPoint.getY() + footOffsetY;

        float x4 = footPoint.getX() + footOffsetX;
        float y4 = footPoint.getY() - footOffsetY;

        float anchorX = (footPoint.getX() + headPoint.getX()) / 2;
        float anchorY = (footPoint.getY() + headPoint.getY()) / 2;

        path.reset();
        path.moveTo(x1, y1);
        path.quadTo(anchorX, anchorY, x3, y3);
        path.lineTo(x4, y4);
        path.quadTo(anchorX, anchorY, x2, y2);
        path.lineTo(x1, y1);
    }


    /**
     * The start animation with BounceInterpolation in certain time.
     *
     * @param time Duration in in milliseconds.
     */
    public void animateView(int time) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "position", 0, 1);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(time);
        objectAnimator.start();
    }


    /**
     * The animation between steps with BounceInterpolation.
     *
     * @param time Duration in milliseconds of the animation
     * @param currentPosition position of the actual step
     */
    public void animateViewR(int time, int currentPosition) {
        if (navigationTabs != null) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "offsetPosition", 0, 1);
            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.setDuration(time);
            objectAnimator.start();
            ObjectAnimator animator = ObjectAnimator.ofInt(getParent(), "scrollX",
                    (int) (navigationTabs.get(currentPosition).getPoint()
                            .getX() - (tabRadius * 2 + tabPadding) * 2));
            animator.setInterpolator(new BounceInterpolator());
            animator.setDuration(time);
            animator.start();
        }
    }


    /**
     * Update the current position and perform the animation.
     *
     * @param currentPosition the current position
     */
    public void setCurrentPosition(int currentPosition) {
        try {
            if (navigationTabs != null && currentPosition < navigationTabs.size()) {
                this.previousPosition = currentPosition == 0 ? 0 : this.currentPosition;
                this.currentPosition = currentPosition;

                if (previousPosition != -1)
                    navigationTabs.get(previousPosition).setStates(NavigationTab.STATES.PROCESSED);
                if (currentPosition > navigationTabs.size() - 1) {
                    currentPosition -= 1;
                }
                navigationTabs.get(currentPosition).setStates(NavigationTab.STATES.CURRENT);
                if (onTabSelected != null)
                    onTabSelected.onTabSelected(currentPosition, navigationTabs.get(previousPosition <= 0 ? 0 : previousPosition),
                            navigationTabs.get(currentPosition <= 0 ? 0 : currentPosition));

                animateViewR(INDICATOR_ANIM_DURATION, currentPosition);
                Timber.i("count", currentPosition );
            } else {
                assert navigationTabs != null;
                onTabSelected.onTabSelected(this.currentPosition, navigationTabs.get(navigationTabs.size() - 2),
                        navigationTabs.get(navigationTabs.size() - 1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Timber.e(e);
        }
    }

    public void setOffsetPosition(float offsetPosition) {
        this.offsetPosition = offsetPosition;
        onPageScrolled(currentPosition, offsetPosition);
        invalidate();
    }

    public void setPosition(float position) {
        this.position = position;
        invalidate();
    }

    public void setTabCount(int tabCount) {
        if (tabCount <= 0)
            tabCount = 1;
        this.tabCount = tabCount;
        requestLayout();
        invalidate();
    }

    public int getTabCount() {
        return tabCount;
    }

    public float getOffsetPosition() {
        return offsetPosition;
    }

    public float getPosition() {
        return position;
    }

    public int getIndicatorColor() {
        return indicatorPaint.getColor();
    }

    public void setIndicatorColor(int color) {
        indicatorPaint.setColor(color);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public float getTabTextSize() { return tabTextSize; }

    public void setTabTextSize(float tabTextSize) {
        this.tabTextSize = tabTextSize;
    }

    public int[] getStateColor() {
        return stateColor;
    }

    public void setStateColor(int[] stateColor) {
        this.stateColor = stateColor;
    }

    public OnTabSelected getOnTabSelected() {
        return onTabSelected;
    }

    public void setOnTabSelected(OnTabSelected tabSelected) {
        this.onTabSelected = tabSelected;
    }

    public int[] getStateTextColor() {
        return stateTextColor;
    }

    public void setStateTextColor(int[] stateTextColor) {
        this.stateTextColor = stateTextColor;
    }

    public boolean isOnlyBorder() {
        return onlyBorder;
    }

    public void setOnlyBorder(boolean onlyBorder) {
        this.onlyBorder = onlyBorder;
    }

    public int[] getStateBorderColor() {
        return stateBorderColor;
    }

    public void setStateBorderColor(int[] stateBorderColor) {
        this.stateBorderColor = stateBorderColor;
    }

    public OnTabClick getOnTabClick() {
        return onTabClick;
    }

    public void setOnTabClick(OnTabClick onTabClick) {
        this.onTabClick = onTabClick;
    }

    public boolean isBorder() {
        return isBorder;
    }

    public void setBorder(boolean border) {
        isBorder = border;
    }


    /**
     * Performs the animation of next and previous.
     */
    public interface OnTabClick {
        void onTabClick(int touchPosition, NavigationTab prev, NavigationTab NavigationTab);
    }

    /**
     * Performs the animation on click any step.
     */
    public interface OnTabSelected {
        void onTabSelected(int touchPosition, NavigationTab prev, NavigationTab NavigationTab);
    }

}
