package com.nahaha.platformer.framework;

import com.nahaha.platformer.objects.IGameObject;
import com.nahaha.platformer.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject implements IGameObject {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float velX;
    protected float velY;
    protected boolean falling = true;
    protected boolean jumping = false;


    protected ObjectId id;

    public GameObject(float x, float y, float width, float height, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public  float getX(){
        return x;
    }
    public  float getY(){
        return y;
    }
    public  void setX(float x){
        this.x = x;
    }
    public  void setY(float y){
        this.y = y;
    }

    public  float getVelX(){
        return velX;
    }
    public  float getVelY(){
        return velY;
    }
    public  void setVelX(float velX){
        this.velX = velX;
    }
    public  void setVelY(float velY){
        this.velY = velY;
    }

    public  ObjectId getId(){
        return id;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public String toString()
    {
        return "{X="+x+",Y="+y+",Width="+width+",Height="+height+",Type="+id.toString()+"}";
    }
}
