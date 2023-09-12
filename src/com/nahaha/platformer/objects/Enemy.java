package com.nahaha.platformer.objects;

import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;
import com.nahaha.platformer.framework.Skills;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {
    Skills[] skills;
    int[] stats = new int[5];

    int level;
    Player player;

    int numId;

    boolean theRight = true;
    boolean theLeft = true;



    public Enemy(float x, float y, float width, float height, ObjectId id, Skills[] skills, int level, Player player) {
        super(x, y, width, height, id);
        this.skills = skills;
        this.level = level;
        this.player = player;
        // Stats guide: Index: 0 = health, 1 = attack modifier, 2 = defence modifier, 3 = energy(mana), 4 = level
        stats[0] = 100;
        stats[1] = 5;
        stats[2] = 3;
        stats[3] = 100;
        stats[4] = level;

        this.numId = numId;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,(int)width,(int)height);
        movement();
    }

    public void movement(){
        velX = 0;

        if((getX() - player.getX() >= -300 && getX() - player.getX() <= 300 ) && (getY() - player.getY() >= -300 && getY() - player.getY() <= 300)){

            /** Player is to the left of enemy */
            if((getX() - player.getX() > 0 && getY() - player.getY() >= -200) && theLeft){
                toTheLeft(true);
                System.out.println("to the left");
                if(getX() - 32 >= player.getX() && theRight){
                    theLeft = false;
                    velX = 0;
                }
            }
            /** Player is to the right of enemy */
             else if((getX() - player.getX() <= 200 && getY() - player.getY() <= 200) && theRight){
                toTheRight(true);
                System.out.println("To the right");

                if(getX() + 32 >= player.getX() && theLeft){
                    theRight = false;
                    velX = 0;
                    //System.out.println("working");
                }

            }

            if(getY() - player.getY() <= 0){
                velY = 3;
                y += velY;
            }

            else if(getY() - player.getY() > 0){
                velY = -3;
                y += velY;
            }

        }


    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }

    public void setNumId(int numID){
        numId = numID;
    }

    public int getNumId(){
        return numId;
    }

    public int getHealth(){
        return stats[0];
    }

    public int getDamageModifier(){
        return stats[1];
    }

    public int getDefenceModifier(){
        return stats[2];
    }

    public int getEnergy(){
        return stats[3];
    }

    public int getLevel(){
        return level;
    }

    public void setHealth(int health){
        stats[0] = health;
    }

    public void setDamageModifier(int damageModifier){
        stats[1] = damageModifier;
    }

    public void setDefenceModifier(int defenceModifier){
        stats[2] = defenceModifier;
    }

    public void setEnergy(int energy){
        stats[3] = energy;
    }




    public void toTheRight(boolean isTrue){
        velX = 3;
        x += velX;
    }

    public void toTheLeft(boolean isTrue){
        velX = -3;
        x += velX;
    }

}
