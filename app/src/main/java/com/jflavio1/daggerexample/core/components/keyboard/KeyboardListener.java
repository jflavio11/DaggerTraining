package com.jflavio1.daggerexample.core.components.keyboard;

import com.jflavio1.daggerexample.core.components.keyboard.controller.KeyboardController;

/**
 * KeyboardListener
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/6/2019
 */
public interface KeyboardListener {

    void charactecClicked(char c);

    void specialKeyClicked(KeyboardController.SpecialKey key);

}


