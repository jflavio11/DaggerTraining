package com.jflavio1.daggerexample.testingdagger;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.dagger.testing.di.DaggerTestApplication;
import com.jflavio1.daggerexample.dagger.testing.di.MotorComponent;

/**
 * BaseActivityDaggerTest
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public abstract class BaseActivityDaggerTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected MotorComponent getMotorComponent(){
        return ((DaggerTestApplication) getApplication()).getMotorComponent();
    }

    protected void logi(String text){
        Log.i("LearningDagger", text);
    }

}
