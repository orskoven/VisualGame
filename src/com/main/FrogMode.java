package com.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrogMode extends MouseAdapter {

    private static final Game.STATE STATE = Game.STATE.Game;
    private Game game;
    private Handler handler;


    // handler.addObject(new Face(100,100,ID.Player));

    public FrogMode(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        game.gameState = Game.STATE.MovieMode;

        }



    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
            Font font = new Font("arial", 1, 25);
            g.setFont(font);
            g.setColor(Color.white);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    g.drawString("FROG", j += 50, i += 50);
                }


            }
        }

}

