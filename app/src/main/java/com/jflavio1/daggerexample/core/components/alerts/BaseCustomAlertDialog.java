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
import androidx.annotation.RestrictTo;
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

    // test purpose only
    private boolean isDismissed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(getDialogLayoutResId(), container, false);
        return this.rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.setCancelable(false);

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

    public BaseCustomAlertDialog setAcceptClickListener(@NonNull AcceptClickListener acceptClickListener) {
        this.acceptClickListener = acceptClickListener;
        return this;
    }

    public BaseCustomAlertDialog setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    public BaseCustomAlertDialog setDescription(@NonNull String description) {
        this.description = description;
        return this;
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    public String getTitle() {
        return title;
    }

    public BaseCustomAlertDialog setAcceptButtonText(@NonNull String acceptButtonText) {
        this.acceptButtonText = acceptButtonText;
        return this;
    }

    @Override
    public void dismiss() {
        isDismissed = true;
        super.dismiss();
    }

    abstract int getDialogLayoutResId();

    abstract int getDialogTitleTextViewResId();

    abstract int getDialogImageViewResId();

    abstract int getDialogDescriptionTextViewResId();

    abstract int getAcceptButtonResId();

    public interface AcceptClickListener {
        void onAcceptButtonClick(@NonNull BaseCustomAlertDialog dialog);
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    public String getDescription() {
        return description;
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    public int getDrawableResId() {
        return drawableResId;
    }

    public BaseCustomAlertDialog setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
        return this;
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    public String getAcceptButtonText() {
        return acceptButtonText;
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    public boolean isDismissed() {
        return isDismissed;
    }

}
