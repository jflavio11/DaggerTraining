package com.jflavio1.daggerexample.core.components.keyboard;

import java.util.ArrayList;

/**
 * KeyboardType
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/8/2019
 */
public enum KeyboardType {
    BANK_PASSWORD;

    private ArrayList<String> serverKeyValues = new ArrayList<>();

    KeyboardType() {
    }

    public ArrayList<String> getServerKeyValues() {
        return serverKeyValues;
    }

    public void setServerKeyValues(ArrayList<String> serverKeyValues) {
        this.serverKeyValues = serverKeyValues;
    }
}