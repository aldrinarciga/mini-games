package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.CommonBG;
import com.aldrinarciga.minigames.entities.gameone_entities.GameOneRunner;
import com.aldrinarciga.minigames.entitymanagers.CommonEntityManager;
import com.aldrinarciga.minigames.entitymanagers.EntityManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameOne extends MiniGame {

    public GameOne() {
        super("RUN!", "Tap as fast as you can",
                new Vector2(50, MainGame.HEIGHT / 2), new Vector2(50,  (MainGame.HEIGHT / 2) - 40),
                2000, new CommonEntityManager());
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

        commonFont.draw(spriteBatch, "POST GAME : " + (hasWon ? "WON" : "LOST"), 20, MainGame.HEIGHT - 20);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
