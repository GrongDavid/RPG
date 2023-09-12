package com.nahaha.platformer.objects;

import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Portal extends GameObject {
    public Portal(float x, float y, float width, float height, ObjectId id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, (int)width,(int)height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width,(int)height);
    }
}
