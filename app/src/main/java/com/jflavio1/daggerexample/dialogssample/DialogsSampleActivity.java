package com.jflavio1.daggerexample.dialogssample;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.alerts.CustomAlertDialog;
import com.jflavio1.daggerexample.core.components.alerts.WebViewAlertDialog;

public class DialogsSampleActivity extends AppCompatActivity {

    // fow showing custom alert dialogs
    private Button simpleBtn, simpleImageBtn, webViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_sample);

        simpleBtn = findViewById(R.id.dialogsSampleActivity_btn_simple);
        simpleImageBtn = findViewById(R.id.dialogsSampleActivity_btn_simpleImage);
        webViewBtn = findViewById(R.id.dialogsSampleActivity_btn_webView);

        simpleBtn.setOnClickListener(v -> showSimplestDialog());
        simpleImageBtn.setOnClickListener(v -> showSimplestImagesDialog());
        webViewBtn.setOnClickListener(v -> showWebViewDialog());

    }

    private void showWebViewDialog() {
        new WebViewAlertDialog()
                .setWebViewUrl("http://rubenhernandez.es/eltiempo/smartphone/")
                .setTitle("Web view alert dialog")
                .setAcceptButtonText("Aceptar")
                .setAcceptClickListener(DialogFragment::dismiss)
                .show(getSupportFragmentManager(), "webviewDialog");
    }

    public void showSimplestDialog() {

        new CustomAlertDialog()
                .setTitle("titulo de ejemplo")
                .setDescription("descripcionnn")
                .setAcceptButtonText("aceptar")
                .setAcceptClickListener(DialogFragment::dismiss)
                .show(getSupportFragmentManager(), "customDialog");

    }


    public void showSimplestImagesDialog() {

        new CustomAlertDialog()
                .setTitle("titulo de ejemplo")
                .setDescription("descripcionnn")
                .setAcceptButtonText("aceptar")
                .setDrawableResId(R.drawable.ic_launcher_background)
                .setAcceptClickListener(DialogFragment::dismiss)
                .show(getSupportFragmentManager(), "imageDialog");

    }

}
