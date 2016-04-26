package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.CommonBG;
import com.aldrinarciga.minigames.entities.gametwo_entities.GameTwoButton;
import com.aldrinarciga.minigames.entitymanagers.CommonEntityManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameTwo extends MiniGame {

    public GameTwo() {
        super("DONT", "Press the Red Button",
                new Vector2(GameTwoButton.BUTTON_SIZE + 50, MainGame.HEIGHT / 2), new Vector2(GameTwoButton.BUTTON_SIZE + 50, (MainGame.HEIGHT / 2) - 40),
                2000, new CommonEntityManager());
    }

    @Override
    public void initEntities() {
        entityManager.addEntity(new CommonBG(this));
        entityManager.addEntity(new GameTwoButton(this));
    }

    @Override
    protected void renderTime(SpriteBatch spriteBatch){
        spriteBatch.draw(totalTime, timePosition.x, timePosition.y, MainGame.WIDTH, TIME_HEIGHT);
        long diff = Math.abs(System.currentTimeMillis() - gameStartTime);
        double percent = (double)diff/Game.getInstance().getStageDuration();
        long remain = (long) ((MainGame.WIDTH * percent));
        spriteBatch.draw(remainingTime, timePosition.x, timePosition.y, remain, TIME_HEIGHT);
        if(diff > Game.getInstance().getStageDuration()){
            if(!isGameOver){
                gameWon();
            }
        }
    }
}
