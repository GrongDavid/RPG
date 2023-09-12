package com.nahaha.platformer.window;

import com.nahaha.platformer.framework.Skills;
import com.nahaha.platformer.framework.StartBattle;
import com.nahaha.platformer.objects.Enemy;
import com.nahaha.platformer.objects.Platforms;
import com.nahaha.platformer.framework.GameObject;
import com.nahaha.platformer.framework.ObjectId;
import com.nahaha.platformer.objects.Player;

import java.io.*;
import java.util.LinkedList;

public class Level {
    Handler handler = new Handler();
    String[] levelParameters;
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;
    StringBuffer stringBuffer;
    String line;
    Skills skills = new Skills();
    public LinkedList<GameObject> levelObjects = new LinkedList<GameObject>();
    Player player = new Player(1000, 100, 32, 64, ObjectId.Player, null);

    public Level(){
    }

    public boolean loadFromFile(String levelFile) {
        levelObjects.clear();
        try {
            file = new File("src/"+levelFile);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("#")) {
                    levelParameters = line.split(",");

                    if (levelParameters.length >= 5) {
                        float x = Float.parseFloat(levelParameters[0]);
                        float y = Float.parseFloat(levelParameters[1]);
                        float width = Float.parseFloat(levelParameters[2]);
                        float height = Float.parseFloat(levelParameters[3]);
                        int type = Integer.parseInt(levelParameters[4]);
                        System.out.println("X="+x+",Y="+y+"");
                        levelObjects.add((GameObject) ObjectId.values()[type].createFrom(x, y, width, height));

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public void tick(LinkedList<GameObject> object) {
        if (!player.getEnemyCollision()) {
            System.out.println("not working");
        }
        if (player.getEnemyCollision()) {
            System.out.println("working");
//            new StartBattle(player, player.enemyCollision(object));
        }
    }

    public void createLevel(){
        //System.out.println("Called");
        //Player player = new Player(1000, 100, 32, 64, ObjectId.Player, null);

        levelObjects.add(player);
        if(handler.getLevel() == 0){
            Enemy enemyOne = new Enemy(200, 100, 32, 64, ObjectId.Enemy, null, 1, player);
            levelObjects.add(enemyOne);
            System.out.println(player.getEnemyCollision());
            if(!player.getEnemyCollision()){
                System.out.println("not working");
            }
            if(player.getEnemyCollision()){
                System.out.println("working");
//                new StartBattle(player, enemyOne);
            }
        }
    }
}





