package com.nahaha.platformer.window;

import com.nahaha.platformer.objects.Enemy;
import com.nahaha.platformer.objects.Portal;
import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;
import com.nahaha.platformer.objects.Platforms;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();

    Level level;
    String levelName;
    public static int curLevel = 0;



    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick(object);
        }
        //System.out.println(object.size());

    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("curLevel: " + curLevel,300,300);
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);

        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public Enemy getEnemy(){
        GameObject enemy;
        GameObject tempObject;

        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);

            if(tempObject.getId() == ObjectId.Enemy){
                enemy = tempObject;
            }
        }
        return null;
    }




    public void setLevel(String nextLevel){
        object.clear();
        levelName = nextLevel;
        level.loadFromFile(nextLevel);
        object.addAll(level.levelObjects);
    }

    public void nextLevel()
    {
        System.out.println(curLevel);
        int next = curLevel + 1;
        setLevel("level"+next+".txt");
        curLevel = next;
    }

    public void backLevel(){
        System.out.println(curLevel);
        int back = curLevel - 1;
        setLevel("level"+back+".txt");
        curLevel = back;
    }

    public int getLevel(){
        return curLevel;

    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void createBasicLevel(){
//        for(int i = 0; i < Game.WIDTH + 32; i += 32) {
//            addObject(new Platforms(i,Game.HEIGHT - 32, 32, 32, ObjectId.Platforms));
//        }


        addObject(new Platforms(0,Game.HEIGHT - 32, Game.WIDTH,32, ObjectId.Platforms));
        addObject(new Platforms(0, 0, 32, Game.HEIGHT, ObjectId.Platforms ));
        addObject(new Platforms(Game.WIDTH - 200, Game.HEIGHT - 430, 75, 32,ObjectId.Platforms));


        addObject(new Platforms(300, Game.HEIGHT - 225,Game.WIDTH - 600,32, ObjectId.Platforms));


        addObject(new Portal(500, 500, 32, 32, ObjectId.Portal));

    }

    public void loadLevel(Level level)
    {
        this.level = level;
        object.addAll(level.levelObjects);
    }
}

