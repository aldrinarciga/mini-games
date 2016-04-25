package com.aldrinarciga.minigames.screen;

import com.aldrinarciga.minigames.camera.OrthoCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by perpetualwave on 18/04/16.
 */
public abstract class Screen {

    OrthoCamera camera;

    public abstract void create();

    public abstract void update();

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void resize(int width, int height);

    public abstract void dispose();

    public abstract void pause();

    public abstract void resume();
}
