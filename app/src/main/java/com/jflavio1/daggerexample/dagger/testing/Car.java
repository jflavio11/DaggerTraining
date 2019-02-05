package com.jflavio1.daggerexample.dagger.testing;

/**
 * Car
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class Car {

    private Motor motor;

    public Car(Motor motor) {
        this.motor = motor;
    }

    public String getMotor() {
        return "Car with " + motor.getMotorType();
    }
}
