package com.nahaha.platformer.objects;

import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class ObstaclesTwo extends GameObject {
    static Color hcolor;

    public ObstaclesTwo(float x, float y, float width, float height, ObjectId id) {
        super(x, y, width, height, id);
        hcolor = Color.black;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    public static void setColor(Color color){
        hcolor = color;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(hcolor);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
}
