package com.jflavio1.daggerexample.dagger.testing;

/**
 * Motor
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class Motor {

    private String motorType;

    public Motor(String motorType) {
        this.motorType = motorType;
    }

    public String getMotorType() {
        return "Tipo motor " + motorType;
    }
}
