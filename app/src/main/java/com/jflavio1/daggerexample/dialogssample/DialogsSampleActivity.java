package com.jflavio1.daggerexample.dialogssample;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.alerts.CustomAlertDialog;

public class DialogsSampleActivity extends AppCompatActivity {

    // fow showing custom alert dialogs
    private Button simpleBtn, simpleImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_sample);

        simpleBtn = findViewById(R.id.dialogsSampleActivity_btn_simple);
        simpleImageBtn = findViewById(R.id.dialogsSampleActivity_btn_simpleImage);

        simpleBtn.setOnClickListener(v -> showSimplestDialog());
        simpleImageBtn.setOnClickListener(v -> showSimplestImagesDialog());

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
                .show(getSupportFragmentManager(), "customDialog");

    }

}
