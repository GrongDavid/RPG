package com.nahaha.platformer.window;

import com.nahaha.platformer.framework.KeyInput;
import com.nahaha.platformer.framework.MouseInput;
import com.nahaha.platformer.objects.Player;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    public static int WIDTH, HEIGHT;
    private static Window WINDOW = null;

    public static final Handler handler = new Handler();

    Level level = new Level();


    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        thread.stop();
    }

    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();




        //BufferedImageLoader loader = new BufferedImageLoader();
        //level = loader.loadImage("src/levelOne.png");

        //Level level = new Level();
        level.loadFromFile("level"+Handler.curLevel+".txt");
        level.createLevel();

        //handler.addObject(new Player(1000,100, 32,64, handler, ObjectId.Player));
        System.out.println("List size: " + handler.object.size());
        handler.loadLevel(level);
        //handler.createBasicLevel();

        this.addKeyListener(new KeyInput(handler));



    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            level.createLevel();

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick(){
        handler.tick();

    }

    private void render(){
        BufferStrategy buffstrat = this.getBufferStrategy();
        if(buffstrat == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffstrat.getDrawGraphics();
        ///////////////Draw here////////////////
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(),getHeight());
        handler.render(g);

        ////////////////////////////////////////

        g.dispose();
        buffstrat.show();



    }

//    private void loadImageLevel(BufferedImage image){
//        int w = image.getWidth();
//        int h = image.getHeight();
//
//        for(int xx = 0; xx < w; xx++){
//            for(int yy = 0; yy < h; yy++){
//                int pixel = image.getRGB(xx, yy);
//               int red = (pixel >> 16) & 0xff;
//                int green = (pixel >> 8) & 0xff;
//                int blue = (pixel) & 0xff;
//
//                if(red == 0 && green == 0 && blue == 0){
//                   handler.addObject(new Platforms(xx * 32, yy * 32, 32, 32, ObjectId.Platforms));
//               }
//
//            }
//        }
//    }

    public static void main(String[] args){
        Game game = new Game();
        if(Player.death()){
            game = new Game();
            game.start();
        }
        WINDOW = new Window(1696,896,"Platformer", game);


    }


}
