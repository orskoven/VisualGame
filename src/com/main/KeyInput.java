package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        char key = e.getKeyChar();
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyChar();

    }

    public void keyPressed(Object keyEvent) {
        System.out.println(keyEvent.toString().charAt(0));
    }
}

