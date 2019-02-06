package com.jflavio1.daggerexample.core.components.keyboard.controller;

import android.view.inputmethod.InputConnection;
import com.jflavio1.daggerexample.core.components.keyboard.KeyboardListener;

import java.util.ArrayList;

/**
 * KeyboardController
 * <p>
 * Source thanks to Don.Brody created on 7/18/18.
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/6/2019
 */
public abstract class KeyboardController {

    private ArrayList<KeyboardListener> listeners = new ArrayList<>();
    private int cursorPosition = 0;
    private String inputText = "";

    private InputConnection inputConnection;

    public KeyboardController(InputConnection inputConnection) {
        this.inputConnection = inputConnection;
    }

    private static String deleteCharacter(String text, int index) {
        return new StringBuilder(text).deleteCharAt(index).toString();
    }

    private static String addCharacter(String text, Character addit, int index) {
        return text.substring(0, index) + addit + text.substring(index);
    }

    /**
     * When a key is clicked.
     *
     * @param c Character that represents the key clicked.
     */
    public synchronized void onKeyStroke(Character c) {
        updateMembers();
        handleKeyStroke(c);
        for (KeyboardListener listener : listeners) {
            listener.charactecClicked(c);
        }
    }

    /**
     * When a special key of the keyboard is clicked.
     *
     * @param key Key that was clicked.
     */
    public synchronized void onKeyStroke(SpecialKey key) {
        updateMembers();
        handleKeyStroke(key);
        for (KeyboardListener listener : listeners) {
            listener.specialKeyClicked(key);
        }
    }

    protected void deleteAllCharacters() {
        if (cursorPosition == 0) {
            return;
        }
        inputConnection.deleteSurroundingText(cursorPosition, inputText.length() - cursorPosition);
        inputText = "";
    }

    protected void clearAll() {
        while (cursorPosition < inputText.length()) {
            deleteNextCharacter();
        }
        while (cursorPosition > 0) {
            deletePreviousCharacter();
        }
    }

    protected void deleteNextCharacter() {
        if (cursorPosition > inputText.length()) {
            return;
        }

        inputConnection.deleteSurroundingText(0, 1);
        inputText = deleteCharacter(inputText, cursorPosition);
    }

    protected void deletePreviousCharacter() {
        if (cursorPosition == 0) {
            return;
        }
        inputConnection.deleteSurroundingText(1, 0);
        inputText = deleteCharacter(inputText, --cursorPosition);
    }

    protected void addCharacter(Character c) {
        if (cursorPosition >= maxCharacters()) {
            return;
        }
        inputConnection.commitText(c.toString(), 1);
        if (cursorPosition++ >= inputText.length()) {
            inputText += c;
        } else {
            addCharacter(inputText, c, cursorPosition - 1);
        }
    }

    abstract int maxCharacters();

    private void updateMembers() {
        String beforeText = beforeCursor();
        String afterText = afterCursor();
        cursorPosition = beforeText.length();
        inputText = beforeText + afterText;
    }

    private String beforeCursor() {
        return inputConnection.getTextBeforeCursor(100, 0).toString();
    }

    private String afterCursor() {
        return inputConnection.getTextAfterCursor(100, 0).toString();
    }

    abstract void handleKeyStroke(Character c);

    abstract void handleKeyStroke(SpecialKey key);

    public enum SpecialKey {
        DONE,
        DELETE_ALL
    }
}
