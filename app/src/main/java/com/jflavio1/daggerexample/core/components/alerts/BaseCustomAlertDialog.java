package com.jflavio1.daggerexample.core.components.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * BaseCustomAlertDialog
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
public abstract class BaseCustomAlertDialog extends DialogFragment {

    protected View rootView;

    // basic ui views
    protected TextView titleTextView;
    protected TextView descriptionTextView;
    protected ImageView dialogImageView;
    protected Button acceptButton;
    // callbacks
    protected AcceptClickListener acceptClickListener;
    // utilities for ui items
    private String title;
    private String description;
    private int drawableResId;
    private String acceptButtonText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = LayoutInflater.from(container.getContext())
                .inflate(getDialogLayoutResId(), container, false);
        return this.rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getDialogTitleTextViewResId() != 0 && title != null) {
            this.titleTextView = rootView.findViewById(getDialogTitleTextViewResId());
            this.titleTextView.setVisibility(View.VISIBLE);
            this.titleTextView.setText(title);
        }

        if (getDialogDescriptionTextViewResId() != 0 && description != null) {
            this.descriptionTextView = rootView.findViewById(getDialogDescriptionTextViewResId());
            this.descriptionTextView.setVisibility(View.VISIBLE);
            this.descriptionTextView.setText(description);
        }

        if (getDialogImageViewResId() != 0 & drawableResId != 0) {
            this.dialogImageView = rootView.findViewById(getDialogImageViewResId());
            this.dialogImageView.setVisibility(View.VISIBLE);
            this.dialogImageView.setImageResource(drawableResId);
        }

        if (getAcceptButtonResId() != 0 && acceptButtonText != null) {
            this.acceptButton = rootView.findViewById(getAcceptButtonResId());
            this.acceptButton.setVisibility(View.VISIBLE);
            this.acceptButton.setText(acceptButtonText);
            this.acceptButton.setOnClickListener(v -> {
                if (acceptClickListener != null) {
                    acceptClickListener.onAcceptButtonClick(this);
                } else {
                    throw new RuntimeException("You have defined a button ui element but not the " +
                            "click listener for it!");
                }
            });
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", this.title);
        outState.putString("description", this.description);
        outState.putString("acceptButtonText", this.acceptButtonText);
        outState.putInt("drawableResId", this.drawableResId);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.title = savedInstanceState.getString("title");
            this.description = savedInstanceState.getString("description");
            this.acceptButtonText = savedInstanceState.getString("acceptButtonText");
            this.drawableResId = savedInstanceState.getInt("drawableResId");
        }
    }

    public void setAcceptClickListener(AcceptClickListener acceptClickListener) {
        this.acceptClickListener = acceptClickListener;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public void setAcceptButtonText(String acceptButtonText) {
        this.acceptButtonText = acceptButtonText;
    }

    abstract int getDialogLayoutResId();

    abstract int getDialogTitleTextViewResId();

    abstract int getDialogImageViewResId();

    abstract int getDialogDescriptionTextViewResId();

    abstract int getAcceptButtonResId();

    public interface AcceptClickListener {
        void onAcceptButtonClick(BaseCustomAlertDialog dialog);
    }

}
