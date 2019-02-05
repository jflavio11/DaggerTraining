package com.jflavio1.daggerexample.testingdagger;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.jflavio1.daggerexample.R;
import com.jflavio1.daggerexample.dagger.testing.Car;
import com.jflavio1.daggerexample.dagger.testing.Motor;
import com.jflavio1.daggerexample.dagger.testing.di.DaggerTestApplication;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * This activity was created only for testing/learning purposes. To work, it must be declared on
 * manifest.
 */
public class MainActivityDaggerTest extends BaseActivityDaggerTest {

    /**
     * We use the @Named annotation to specify that Diesel Motor must be injected here. The
     * definition of this injection is on
     * {@link com.jflavio1.daggerexample.dagger.testing.di.MotorModule}.
     */
    @Named("diesel")
    @Inject
    Motor motor;

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getMotorComponent() is implemented on BaseActivityDaggerTest
        getMotorComponent().inject(this);

        logi("Simple motor injection: " + motor.getMotorType());
        logi("Car motor injection: " + car.getMotor());
    }
}
