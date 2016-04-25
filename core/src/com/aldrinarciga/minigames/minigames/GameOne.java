package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entitymanagers.GameOneEntityManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameOne extends MiniGame {

    @Override
    public void initGame() {
        mainInstruction = "RUN!";
        subInstruction = "Tap as fast as you can";
        mainInstructionPosition = new Vector2(MainGame.WIDTH / 2, MainGame.HEIGHT / 2);
        subInstructionPosition = new Vector2(MainGame.WIDTH / 2 + 50, MainGame.HEIGHT / 2);
        postGameDuration = 2000;
        font = new BitmapFont();
        font.setColor(Color.RED);
        entityManager = new GameOneEntityManager();
        gameStartTime = System.currentTimeMillis();

        initEntities();
    }

    @Override
    public void initEntities() {

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

        if(!isProgressUpdated){
            if(hasWon){
                Game.getInstance().addScore(100);
            }else{
                Game.getInstance().decreaseLives();
            }
            isProgressUpdated = true;
        }

        font.draw(spriteBatch, "POST GAME", 20, MainGame.HEIGHT - 20);
    }

    @Override
    public void dispose() {
        entityManager.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
