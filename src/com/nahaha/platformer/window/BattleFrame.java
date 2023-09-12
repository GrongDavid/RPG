package com.nahaha.platformer.window;

import com.nahaha.platformer.framework.MouseInput;
import com.nahaha.platformer.framework.StartBattle;
import com.nahaha.platformer.objects.Enemy;
import com.nahaha.platformer.objects.Player;

import javax.swing.*;
import java.awt.*;

public class BattleFrame extends JPanel{

    public String battleString = "hi";

    public BattleFrame(){
        JFrame frame = new JFrame("Battle");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.createBufferStrategy(3);
        frame.setVisible(true);


        frame.getContentPane().addMouseListener(new MouseInput());

        Graphics g = frame.getGraphics();
        g.drawString(battleString, 200,100);



    }

    public void setBattleString(String message){
        battleString = message;
    }


}
