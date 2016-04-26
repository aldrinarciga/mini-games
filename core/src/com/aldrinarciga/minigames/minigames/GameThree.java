package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.CommonBG;
import com.aldrinarciga.minigames.entities.gamethree_entities.GameThreeMissile;
import com.aldrinarciga.minigames.entities.gamethree_entities.GameThreePlayer;
import com.aldrinarciga.minigames.entitymanagers.CommonEntityManager;
import com.aldrinarciga.minigames.entitymanagers.GameThreeEntityManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameThree extends MiniGame{

    public GameThree() {
        super("Press and Hold", "Don't let the plane get hit by the missiles!",
                new Vector2(250, MainGame.HEIGHT / 2), new Vector2(250, (MainGame.HEIGHT / 2) - 40),
                2000, new GameThreeEntityManager());
    }

    @Override
    public void initEntities() {
        entityManager.addEntity(new CommonBG(this));
        entityManager.addEntity(new GameThreePlayer(this));
        entityManager.addEntity(new GameThreeMissile(this));
    }

    @Override
    public void update() {
        super.update();
        if(((GameThreeEntityManager) entityManager).isGameOver){
            gameLost();
        }
    }

    @Override
    protected void renderTime(SpriteBatch spriteBatch){
        spriteBatch.draw(totalTime, timePosition.x, timePosition.y, MainGame.WIDTH, TIME_HEIGHT);
        long diff = Math.abs(System.currentTimeMillis() - gameStartTime);
        double percent = (double)diff/ Game.getInstance().getStageDuration();
        long remain = (long) ((MainGame.WIDTH * percent));
        spriteBatch.draw(remainingTime, timePosition.x, timePosition.y, remain, TIME_HEIGHT);
        if(diff > Game.getInstance().getStageDuration()){
            if(!isGameOver){
                gameWon();
            }
        }
    }

}
