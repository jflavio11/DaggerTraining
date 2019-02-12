package com.jflavio1.daggerexample.core.components.alerts;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jflavio1.daggerexample.R;

/**
 * WebViewAlertDialog
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/12/2019
 */
public class WebViewAlertDialog extends BaseCustomAlertDialog {

    protected WebView webView;
    private String webViewUrl;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getWebViewResId() != 0) {
            this.webView = rootView.findViewById(getWebViewResId());
            this.webView.loadUrl(this.webViewUrl);
            //this.webView.setScrollBarStyle();
        }

    }

    public void setWebViewUrl(String webViewUrl) {
        this.webViewUrl = webViewUrl;
    }

    @Override
    int getDialogLayoutResId() {
        return R.layout.dialog_custom_web_view;
    }

    @Override
    int getDialogTitleTextViewResId() {
        return R.id.dialogWebView_tv_title;
    }

    @Override
    int getDialogImageViewResId() {
        return 0;
    }

    @Override
    int getDialogDescriptionTextViewResId() {
        return 0;
    }

    @Override
    int getAcceptButtonResId() {
        return R.id.dialogWebView_btn;
    }

    protected int getWebViewResId() {
        return R.id.dialogWebView_wv;
    }

}
