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

    @Override
    public void initGame() {
        mainInstruction = "DONT";
        subInstruction = "Press the Red Button";
        mainInstructionPosition = new Vector2(GameTwoButton.BUTTON_SIZE + 100, MainGame.HEIGHT / 2);
        subInstructionPosition = new Vector2(GameTwoButton.BUTTON_SIZE + 100, (MainGame.HEIGHT / 2) - 20);
        postGameDuration = 2000;
        font = new BitmapFont();
        font.setColor(Color.RED);
        entityManager = new CommonEntityManager();
        gameStartTime = System.currentTimeMillis();

        initEntities();
    }

    @Override
    public void initEntities() {
        entityManager.addEntity(new CommonBG(this));
        entityManager.addEntity(new GameTwoButton(this));
    }

    @Override
    public void update() {
        entityManager.update();
        checkStatus();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        entityManager.render(spriteBatch);
        renderTime(spriteBatch);
    }

    @Override
    public void renderPostGame(SpriteBatch spriteBatch) {
        if(postGameStartTime == 0){
            postGameStartTime = System.currentTimeMillis();
        }

        font.draw(spriteBatch, "POST GAME : " + (hasWon ? "WON" : "LOST"), 20, MainGame.HEIGHT - 20);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

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
