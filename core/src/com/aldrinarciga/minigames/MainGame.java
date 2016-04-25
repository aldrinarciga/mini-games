package com.aldrinarciga.minigames;

import com.aldrinarciga.minigames.screen.GameScreen;
import com.aldrinarciga.minigames.screen.MenuScreen;
import com.aldrinarciga.minigames.screen.ScreenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {
	public static final int WIDTH = 800, HEIGHT = 480;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(ScreenManager.getCurrentScreen() != null){
			ScreenManager.getCurrentScreen().update();
			ScreenManager.getCurrentScreen().render(batch);
		}
	}

	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
	}

	@Override
	public void resize(int width, int height) {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
	}

	@Override
	public void pause() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
	}

	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
	}
}
