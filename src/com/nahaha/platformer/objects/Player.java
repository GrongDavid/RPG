package com.nahaha.platformer.objects;

import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;
import com.nahaha.platformer.framework.Skills;
import com.nahaha.platformer.framework.StartBattle;
import com.nahaha.platformer.window.BattleFrame;
import com.nahaha.platformer.window.Game;
import com.nahaha.platformer.window.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player extends GameObject{

    private float gravity = 0.0f;
    private final float terminalVelocity = 15;
    float startX;
    float startY;
    public static boolean isDead;
    Skills[] playerSkills;
    ArrayList<Float> fallingList = new ArrayList<>();


    public boolean enemyCollision = false;

    int stats[] = new int[6];



    public Player(float x, float y, float width, float height, ObjectId id, Skills[] playerSkills) {
        super(x, y, width, height, id);
        startX = x;
        startY = y;
        this.playerSkills = playerSkills;

        // Stats guide: Index: 0 = health, 1 = attack modifier, 2 = defence modifier, 3 = energy(mana), 4 = level, 5 = experience
        stats[0] = 100;
        stats[1] = 8;
        stats[2] = 3;
        stats[3] = 100;
        stats[4] = 1;
        stats[5] = 0;
    }


    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
        collision(object);
        enemyCollision(object);

        if(falling)
        {
            velY += gravity;

            if(velY > terminalVelocity){
                velY = terminalVelocity;
                //System.out.println("falling");
            }
        }
        if(jumping){
            //System.out.println("jumping");
        }
        if(y >= 896){
            x = startX;
            y = startY;
            setDead(true);

        }
        fallingList.add(velY);
        if(fallingList.size() >= 2) {
            if (fallingList.get(1) - fallingList.get(0) < 0) {
                falling = true;
                //System.out.println(fallingList.get(1));
               // System.out.println("fallingList");
            }
        }
        if(fallingList.size() > 2){
            fallingList.remove(0);
        }
       // System.out.println(fallingList.get(0));
    }

    public static boolean death(){
        return isDead;
    }

    public void setDead(boolean dead){
        isDead = dead;
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
        return  stats[4];
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

    public void setEnemyCollision(boolean collision){
        enemyCollision = collision;
    }

    public boolean getEnemyCollision(){
        return enemyCollision;
    }

    public void addExperience(int experience){
        stats[5] += experience;

        if(stats[5] >= 100 + stats[4]){
            int overflow = stats[5] - 100 + stats[4];
            stats[4] = 0;
            stats[4] += overflow;
            levelUp();
        }
    }

    public void levelUp(){
        stats[0] += 10;
        stats[1] += 2;
        stats[2] += 1;
        stats[3] += 5;
        stats[4] += 1;
    }


    public void collision(LinkedList<GameObject> object){
        Enemy enemyOne = new Enemy(200, 100, 32, 64, ObjectId.Enemy, null, 1, this);
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(getBoundsTop().intersects(tempObject.getBounds())) {
                y = tempObject.getY() + (height);
                velY = 0;

            }


            if(tempObject.getId() == ObjectId.Portal){
                if(getBounds().intersects(tempObject.getBounds())) {
                    Game.handler.nextLevel();
                }
            }

            if(tempObject.getId() == ObjectId.Obstacles){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    Obstacles.setColor(Color.yellow);
                }

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    Obstacles.setColor(Color.yellow);
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    Obstacles.setColor(Color.yellow);
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    Obstacles.setColor(Color.yellow);
                }
            }




            if(tempObject.getId() == ObjectId.ObstaclesTwo){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesTwo.setColor(Color.red);
                }

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesTwo.setColor(Color.red);
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesTwo.setColor(Color.red);
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesTwo.setColor(Color.red);
                }
            }

//            if(tempObject.getId() == ObjectId.Enemy){
//                if(getBounds().intersects(tempObject.getBounds())){
//                    setEnemyCollision(true);
//                    System.out.println(getEnemyCollision());
//                }
//
//                if(getBoundsTop().intersects(tempObject.getBounds())){
//                    setEnemyCollision(true);
//                    System.out.println(getEnemyCollision());
//                }
//
//                if(getBoundsLeft().intersects(tempObject.getBounds())){
//                    setEnemyCollision(true);
////                    new StartBattle(this, enemyOne);
//                    System.out.println(getEnemyCollision());
//                }
//
//                if(getBoundsRight().intersects(tempObject.getBounds())){
//                    setEnemyCollision(true);
//                    System.out.println(getEnemyCollision());
//                }
//            }




            if(tempObject.getId() == ObjectId.ObstaclesThree){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesThree.setColor(Color.green);
                }

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesThree.setColor(Color.green);
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesThree.setColor(Color.green);
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesThree.setColor(Color.green);
                }
            }




            if(tempObject.getId() == ObjectId.ObstaclesFour){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesFour.setColor(Color.cyan);
                }

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesFour.setColor(Color.cyan);
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesFour.setColor(Color.cyan);
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    y = startY;
                    x = startX;
                    setDead(true);
                    ObstaclesFour.setColor(Color.cyan);
                }
            }


//            if(tempObject.getId() == ObjectId.Platforms){
//                if(!getBounds().intersects(tempObject.getBounds())){
//                    jumping = false;
//                    System.out.println("not touching");
//                }
//            }
//
//            else
//            {
//                jumping = true;
//            }






            if(tempObject.getId() == ObjectId.Platforms){
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else{
                    falling = true;
                }
            }



            if(getBoundsRight().intersects(tempObject.getBounds())) {
                x = tempObject.getX() - width;

            }

            if(getBoundsLeft().intersects(tempObject.getBounds())) {
                x = tempObject.getX() + tempObject.getWidth();

            }
        }


    }

    public GameObject enemyCollision(LinkedList<GameObject> object){
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if(tempObject.getId() == ObjectId.Enemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    setEnemyCollision(true);
                    System.out.println(getEnemyCollision());
                }

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    setEnemyCollision(true);
                    System.out.println(getEnemyCollision());
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    setEnemyCollision(true);
                    System.out.println(getEnemyCollision());
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    setEnemyCollision(true);
                    System.out.println(getEnemyCollision());
                }
                return tempObject;
            }
        }
        return null;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,(int)width,(int)height);

        Graphics2D g2d = (Graphics2D) g;


    }

    public void setPlayerSkill(Skills skill, int index){
        playerSkills[index] = skill;
    }

    public Skills getPlayerSkill(int index){
        return playerSkills[index];
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (width/2) -(width/4)), (int) ((int)y + (height/2)),(int)width/2,(int)height/2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (width/2) - (width/4)), (int)y,(int)width/2,(int)height/2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int)5, (int) height - 10);
    }

}
