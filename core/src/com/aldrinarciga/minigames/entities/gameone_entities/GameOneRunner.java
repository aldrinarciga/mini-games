package com.aldrinarciga.minigames.entities.gameone_entities;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.Entity;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameOneRunner extends Entity {

    private static final long MIN_WIDTH = 200, MIN_HEIGHT = 300, MAX_WIDTH = 500,
            TOADD_WIDTH = 10, TOADD_HEIGHT = 20, ADD_MULTIPLIER = 5;

    public GameOneRunner(MiniGame miniGame) {
        super(miniGame, new Texture("gameoneRunner.png"), null, null);
        width = MIN_WIDTH;
        height = MIN_HEIGHT;
        position = new Vector2((MainGame.WIDTH / 2) - (width / 2), (MainGame.HEIGHT / 2) - (height / 2));
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y, width, height);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            width += (TOADD_WIDTH * ADD_MULTIPLIER);
            height += (TOADD_HEIGHT * ADD_MULTIPLIER);

            if(width > MAX_WIDTH){
                miniGame.gameWon();
            }
        }
    }

    @Override
    public void update() {
        handleInput();
        position = new Vector2((MainGame.WIDTH / 2) - (width / 2), (MainGame.HEIGHT / 2) - (height / 2));

        width -= (TOADD_WIDTH / 3);
        height -= (TOADD_HEIGHT / 3);

        if(width < MIN_WIDTH){
            width = MIN_WIDTH;
            height = MIN_HEIGHT;
        }
    }
}
