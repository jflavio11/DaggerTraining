package com.jflavio1.daggerexample.dagger.testing.di;

import com.jflavio1.daggerexample.dagger.testing.Car;
import com.jflavio1.daggerexample.dagger.testing.Motor;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * MotorModule
 * <p>
 * Modules allows Dagger to know how to provide/inject dependencies into other classes. It can be
 * used to inject concrete implementations of an interface.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
@Module
public class MotorModule {

    /**
     * We use the "provides" keyword in methods to provide a specific dependency. Also, the
     * annotation @Named allows Dagger to identify which dependency to inject.
     *
     * @return a motor diesel object.
     */
    @Named("diesel")
    @Provides
    public Motor providesMotorDiesel() {
        return new Motor("diesel");
    }

    @Named("gasoline")
    @Provides
    public Motor providesMotorGasoline() {
        return new Motor("gasoline");
    }

    /**
     * The Singleton annotation is for unique instance of a dependency. However, it should not be
     * used for activities or views. It would be better to use the {@link PerActivity} interface
     * annotation.
     *
     * @return a electric motor object.
     * @see PerActivity
     */
    @Singleton
    @Provides
    public Motor providesMotorElectric() {
        return new Motor("electric");
    }

    /**
     * Since we have to types of injection for {@link Motor} class (diesel and gasoline), we must
     * specify in the parameter which motor is going to be injected using the @Named annotation.
     *
     * @param motor the motor to be injected into the {@link Car} object.
     * @return a car object.
     */
    @Provides
    public Car providesCar(@Named("gasoline") Motor motor) {
        return new Car(motor);
    }

}
