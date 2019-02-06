package com.jflavio1.daggerexample.core.components.expandableView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import com.jflavio1.daggerexample.core.components.ResizableRelativeLayout;
import com.jflavio1.daggerexample.core.components.utils.ComponentUtils;

import java.util.ArrayList;

/**
 * ExpandableView
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/6/2019
 */
public abstract class ExpandableView extends ResizableRelativeLayout {

    private ExpandableState state;
    private ArrayList<ExpandableStateListener> stateListeners = new ArrayList<>();

    public ExpandableView(Context context) {
        super(context);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.state = ExpandableState.EXPANDED;
        this.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // collapse view after initial inflation
        translateLayout();
    }

    public final void registerListener(@NonNull ExpandableStateListener listener) {
        stateListeners.add(listener);
    }

    public final boolean isExpanded() {
        return state == ExpandableState.EXPANDED;
    }

    public final void translateLayout() {
        // Ignore calls that occur during animation (prevents issues from wood-pecker'ing)
        if (state != ExpandableState.EXPANDING && state != ExpandableState.COLLAPSING) {
            float pixels = ComponentUtils.pxToDp(getContext(), 500);

            // translates layout 1px per millisecond
            long millis = Long.parseLong(String.valueOf(pixels));

            float deltaY;

            switch (state) {

                case EXPANDED: {
                    updateState(ExpandableState.COLLAPSING);
                    deltaY = Float.valueOf(String.valueOf(pixels)); // pushes layout down 500 device pixels
                    animate().translationY(deltaY).setDuration(millis).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            updateState(ExpandableState.COLLAPSED);
                            setVisibility(View.INVISIBLE);
                        }
                    }).start();
                }

                case COLLAPSED: {
                    updateState(ExpandableState.EXPANDING);
                    setVisibility(View.VISIBLE);
                    deltaY = 0.0f; // pulls layout back to its original position
                    animate().translationY(deltaY).setDuration(millis).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            updateState(ExpandableState.EXPANDED);

                        }
                    }).start();
                }
            }

        }
    }

    private void updateState(ExpandableState nextState) {
        this.state = nextState;
        for (ExpandableStateListener listener : stateListeners) {
            listener.onStateChange(nextState);
        }
    }


}
