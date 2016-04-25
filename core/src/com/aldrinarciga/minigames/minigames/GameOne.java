package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.CommonBG;
import com.aldrinarciga.minigames.entities.gameone_entities.GameOneRunner;
import com.aldrinarciga.minigames.entitymanagers.CommonEntityManager;
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
        mainInstructionPosition = new Vector2(50, MainGame.HEIGHT / 2);
        subInstructionPosition = new Vector2(50,  (MainGame.HEIGHT / 2) - 20);
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
        entityManager.addEntity(new GameOneRunner(this));
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
}
