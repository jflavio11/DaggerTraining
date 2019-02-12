package com.jflavio1.daggerexample.core.components.alerts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * BaseTwoButtonCustomAlertDialog
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public abstract class BaseTwoButtonCustomAlertDialog extends BaseCustomAlertDialog {

    protected Button negativeButton;

    private String negativeButtonText;

    private NegativeClickListener negativeClickListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getNegativeButtonResId() != 0 && negativeButtonText != null) {
            this.negativeButton = rootView.findViewById(getNegativeButtonResId());
            this.negativeButton.setVisibility(View.VISIBLE);
            this.negativeButton.setOnClickListener(v -> {
                if (negativeClickListener != null) {
                    this.negativeClickListener.onNegativeButtonClick(this);
                } else {
                    throw new RuntimeException("You have defined a negative button ui element but" +
                            " not the click listener for it!");
                }
            });
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("negativeText", this.negativeButtonText);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.negativeButtonText = savedInstanceState.getString("negativeText");
        }
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public void setNegativeClickListener(NegativeClickListener negativeClickListener) {
        this.negativeClickListener = negativeClickListener;
    }

    public abstract int getNegativeButtonResId();

    public interface NegativeClickListener {
        void onNegativeButtonClick(BaseCustomAlertDialog dialog);
    }

}
