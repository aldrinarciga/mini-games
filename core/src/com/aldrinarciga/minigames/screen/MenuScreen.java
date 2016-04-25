package com.aldrinarciga.minigames.screen;

import com.aldrinarciga.minigames.camera.OrthoCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class MenuScreen extends Screen {

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.update();
        camera.resize();
    }

    @Override
    public void update() {
        camera.update();
        camera.resize();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
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
