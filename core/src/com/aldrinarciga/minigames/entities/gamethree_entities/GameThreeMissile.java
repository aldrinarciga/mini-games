package com.aldrinarciga.minigames.entities.gamethree_entities;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.Entity;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 26/04/2016.
 */
public class GameThreeMissile extends Entity {

    private static final int MISSILE_SPEED = -10;
    private static final int MISSILE_HEIGHT = 40, MISSILE_WIDTH = 60;

    public GameThreeMissile(MiniGame miniGame) {
        super(miniGame, new Texture("gamethreeMissile.gif"), new Vector2(MainGame.WIDTH + 50, MainGame.HEIGHT), new Vector2(MISSILE_SPEED, 0));
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, MISSILE_WIDTH, MISSILE_HEIGHT);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {
        position.add(direction);
        if(position.x < texture.getWidth()){
            position.x = MainGame.WIDTH;
            int rnd = (int) (Math.random() * MainGame.HEIGHT - texture.getHeight()) + 30;
            position.y = rnd;
        }
    }
}
