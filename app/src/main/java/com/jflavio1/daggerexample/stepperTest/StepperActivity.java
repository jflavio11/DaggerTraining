package com.jflavio1.daggerexample.stepperTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.core.components.stepper.NavigationBar;
import com.jflavio1.daggerexample.core.components.stepper.NavigationTab;

public class StepperActivity extends AppCompatActivity implements NavigationBar.OnTabSelected{

    private NavigationBar bar;
    private Button btnReset, btnNext, btnPrevious, btnSubmit;
    private int position = 0;
    private EditText etNumberOfCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);
        etNumberOfCount = findViewById(R.id.et_count);
        btnNext = findViewById(R.id.btn_next);
        btnPrevious = findViewById(R.id.btn_prev);
        btnSubmit = findViewById(R.id.btn_submit);
        btnReset = findViewById(R.id.btn_reset);
        bar = findViewById(R.id.navBar);
        bar.setOnTabSelected(this);
        setup(false, 4);

        btnReset.setOnClickListener(v->{
            setup(true, 4);
            etNumberOfCount.setText("");
        });

        btnNext.setOnClickListener(v-> bar.setCurrentPosition(++position));
        btnPrevious.setOnClickListener(v-> bar.setCurrentPosition(--position));

        btnSubmit.setOnClickListener(v-> setup(true, Integer.parseInt(etNumberOfCount.getText().toString())));
    }

    private void setup(boolean reset, int count) {
        if (reset){
            bar.resetItems();
            position = 0;
        }
        bar.setTabCount(count);
        bar.animateView(3000);
        bar.setCurrentPosition(position <= 0 ? 0 : position);
    }


    @Override
    public void onTabSelected(int touchPosition, NavigationTab prev, NavigationTab NavigationTab) {
        Toast.makeText(this, "Selected position: " + touchPosition, Toast.LENGTH_LONG).show();
    }
}
