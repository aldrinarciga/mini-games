package com.aldrinarciga.minigames.entitymanagers;

import com.aldrinarciga.minigames.entities.CommonBG;
import com.aldrinarciga.minigames.entities.Entity;
import com.aldrinarciga.minigames.entities.gamethree_entities.GameThreeMissile;
import com.aldrinarciga.minigames.entities.gamethree_entities.GameThreePlayer;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by perpetualwave on 26/04/2016.
 */
public class GameThreeEntityManager extends EntityManager {

    private GameThreePlayer player;
    private GameThreeMissile missile;
    private CommonBG bg;

    public boolean isGameOver;

    @Override
    public void checkCollisions() {
        if(!isPaused) {
            if (player.getBounds().overlaps(missile.getBounds())) {
                isGameOver = true;
            }
        }
    }
    public void update(){
        if(!isPaused) {
            player.update();
            missile.update();
            bg.update();
            checkCollisions();
        }
    }

    public void render(SpriteBatch spriteBatch){
        bg.render(spriteBatch);
        player.render(spriteBatch);
        missile.render(spriteBatch);
    }

    public void addEntity(Entity entity){
        if(entity instanceof GameThreePlayer){
            this.player = (GameThreePlayer) entity;
        }else if(entity instanceof GameThreeMissile){
            this.missile = (GameThreeMissile) entity;
        }else if(entity instanceof CommonBG){
            this.bg = (CommonBG) entity;
        }
    }

    @Override
    public void removeEntity(Entity entity) {

    }

    @Override
    public void dispose() {

    }
}
