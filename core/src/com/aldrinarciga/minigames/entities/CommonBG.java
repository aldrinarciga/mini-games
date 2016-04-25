package com.aldrinarciga.minigames.entities;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class CommonBG extends Entity {
    public CommonBG(MiniGame miniGame) {
        super(miniGame, new Texture("commonbg.jpg"), new Vector2(0,0), null);
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y, MainGame.WIDTH, MainGame.HEIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {

    }
}
