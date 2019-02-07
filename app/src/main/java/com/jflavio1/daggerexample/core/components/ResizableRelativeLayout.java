package com.jflavio1.daggerexample.core.components;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;


/**
 * ResizableRelativeLayout
 * <p>
 * Sauce thanks to Don.Brody created on 7/18/18.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/6/2019
 */
public abstract class ResizableRelativeLayout extends RelativeLayout {

    public ResizableRelativeLayout(Context context) {
        super(context);
    }

    public ResizableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetContent();
    }

    /**
     * Adding a delay gives the parent activity time to handle its configuration change prior to
     * us handling ours. Otherwise we run into several issues, including the screen size property
     * of our parent window not updating prior to us accessing it.
     */
    @CallSuper
    public final void resetContent() {
        removeAllViews();
        new Handler().postDelayed(this::configureSelf, 50);
    }

    protected abstract void configureSelf();

}
