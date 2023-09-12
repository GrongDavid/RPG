package com.nahaha.platformer.framework;

import com.nahaha.platformer.objects.Enemy;
import com.nahaha.platformer.objects.Player;
import com.nahaha.platformer.window.BattleFrame;
import com.nahaha.platformer.window.Handler;

public class StartBattle {
    Handler handler = new Handler();

    public StartBattle(Player player, Enemy enemy){
        BattleFrame BF = new BattleFrame();
        MouseInput mouseInput = new MouseInput();
        System.out.println(player.getHealth());
        BF.setBattleString("Choose Attack");

        while(player.getHealth() > 0 && enemy.getHealth() > 0){
            if((mouseInput.getMouseX() > 100 && mouseInput.getMouseX() < 500) && (mouseInput.getMouseY() > 100 && mouseInput.getMouseY() < 500)){
                System.out.println("working");
                System.out.println(mouseInput.getMouseX());
            }
        }

        if(player.getHealth() <= 0){
            BF.setBattleString("You have died");
        }

        if(enemy.getHealth() <= 0){
            handler.removeObject(enemy);
            BF.setBattleString("Success!");

            if(player.getLevel() <= 5){
                player.addExperience(10 + (enemy.getLevel() * 3));
            }
            else{
                player.addExperience(10 + (enemy.getLevel() * 2));
            }
        }

    }
}
