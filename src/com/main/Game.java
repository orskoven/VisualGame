package com.main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private MENU menu;
    private FrogMode frogMode;

    public enum STATE {
        Menu, Game, Game2, Car,MovieMode,FrogMode
    }

    public STATE gameState = STATE.Menu;
    public STATE gameStateMovie = STATE.MovieMode;

    public Game() {

        frogMode = new FrogMode(this, handler);
        menu = new MENU(this, handler);
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "HANG MAN", this);
        play("resources/introsounds.wav");

    }

    public static void main(String[] args) throws InterruptedException {
        Game firstGame = new Game();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println();
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
        } else if (gameState == STATE.Menu) {
            menu.tick();
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 1000, 1000);

        handler.render(g);
        if (gameState == STATE.FrogMode) {
            frogMode.render(g);
        } else if (gameState == STATE.Menu) {
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            ((Clip) clip).open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

}
