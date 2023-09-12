package com.nahaha.platformer.framework;

import com.nahaha.platformer.window.Handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput extends MouseAdapter {

    Handler handler;

    public int mouseX;
    public int mouseY;

    public MouseInput(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println(e.getPoint());
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
