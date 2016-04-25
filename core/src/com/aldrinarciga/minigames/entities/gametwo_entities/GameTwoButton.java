package com.aldrinarciga.minigames.entities.gametwo_entities;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.camera.OrthoCamera;
import com.aldrinarciga.minigames.entities.Entity;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameTwoButton extends Entity {

    public static final int TOUCH_BOUNDS = 5, BUTTON_SIZE = 400;

    public GameTwoButton(MiniGame miniGame) {
        super(miniGame, new Texture("gametwoRedbutton.png"), new Vector2(0, 10), null);
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y, BUTTON_SIZE, BUTTON_SIZE);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0);
            camera.unproject(vec);
            Rectangle touchRect = new Rectangle(vec.x + 150, vec.y, TOUCH_BOUNDS, TOUCH_BOUNDS);
            if(touchRect.overlaps(getBounds())){
                miniGame.gameLost();
            }
        }
    }

    @Override
    public void update() {
        handleInput();
    }
}
