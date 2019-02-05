package com.jflavio1.daggerexample.dagger.testing.di;

import android.app.Application;

/**
 * DaggerTestApplication
 *
 * This class was created only for testing/learning purposes. To work, it must be declared on
 * manifest as a name of application.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class DaggerTestApplication extends Application {

    private MotorComponent motorComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        motorComponent = DaggerMotorComponent.builder().motorModule(new MotorModule()).build();

    }

    public MotorComponent getMotorComponent() {
        return motorComponent;
    }
}
