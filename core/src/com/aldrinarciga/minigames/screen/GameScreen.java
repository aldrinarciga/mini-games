package com.aldrinarciga.minigames.screen;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.camera.OrthoCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameScreen extends Screen {

    Game game;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.update();
        camera.resize();

        game = Game.newGame(camera);
    }

    @Override
    public void update() {
        camera.update();
        camera.resize();
        game.getCurrentGame().update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        switch (game.getCurrentGame().getGameState()){
            case SHOW_INSTRUCTION:
                game.getCurrentGame().render(spriteBatch);
                game.getCurrentGame().renderInstruction(spriteBatch);
                break;
            case POST_STATE:
                game.getCurrentGame().renderPostGame(spriteBatch);
                break;
            case SHOW_PROGRESS:
                game.getCurrentGame().renderProgress(spriteBatch);
                break;
            case FINISH:
                game.getCurrentGame().dispose();
                game.generateGame();
                break;
            default:
                game.getCurrentGame().render(spriteBatch);
                break;
        }
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
