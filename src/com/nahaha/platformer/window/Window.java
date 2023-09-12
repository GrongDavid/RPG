package com.nahaha.platformer.window;

import com.nahaha.platformer.framework.MouseInput;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(int w, int h, String title, Game game){
        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w,h));
        game.setMinimumSize(new Dimension(w,h));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.createBufferStrategy(3);
        frame.setVisible(true);



        game.start();
    }

}