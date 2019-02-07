package com.jflavio1.daggerexample.core.components.keyboard.controller;

import android.view.inputmethod.InputConnection;

/**
 * DefaultKeyboardController
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/6/2019
 */
public class DefaultKeyboardController extends KeyboardController {

    public DefaultKeyboardController(InputConnection inputConnection) {
        super(inputConnection);
    }

    @Override
    void handleKeyStroke(Character c) {
        addCharacter(c);
    }

    @Override
    void handleKeyStroke(SpecialKey key) {
        switch (key) {
            case DONE: {
                // TODO: implement
            }

            case DELETE_ALL: {
                //deleteAllCharacters();
                clearAll();
            }
        }
    }
}
