package com.jflavio1.daggerexample.dagger.testing.di;

import com.jflavio1.daggerexample.testingdagger.MainActivityDaggerTest;
import dagger.Component;

/**
 * MotorComponent
 * <p>
 * A component is an interface that specifies which modules to use and what objects to return for
 * our code. It is like a bridge between modules and classes that requires the dependencies. A
 * Component can has many modules.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@PerActivity
@Component(modules = {MotorModule.class})
public interface MotorComponent {
    void inject(MainActivityDaggerTest mainActivityDaggerTest);
}
