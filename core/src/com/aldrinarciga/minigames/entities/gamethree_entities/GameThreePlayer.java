package com.aldrinarciga.minigames.entities.gamethree_entities;

import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entities.Entity;
import com.aldrinarciga.minigames.minigames.MiniGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 26/04/2016.
 */
public class GameThreePlayer extends Entity {

    private static final int PLANE_GRAVITY = -150, FLY_GRAVITY = 150;
    private static final int PLANE_HEIGHT = 150, PLANE_WIDTH = 200;


    public GameThreePlayer(MiniGame miniGame) {
        super(miniGame, new Texture("gamethreePlane.png"), new Vector2(20, MainGame.HEIGHT), new Vector2(0, PLANE_GRAVITY));
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, PLANE_WIDTH, PLANE_HEIGHT);
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y, PLANE_WIDTH, PLANE_HEIGHT);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()){
            setDirection(0, FLY_GRAVITY);
        }
    }

    @Override
    public void update() {
        handleInput();
        position.add(direction);
        setDirection(0, PLANE_GRAVITY);

        if(position.y > MainGame.HEIGHT - texture.getHeight()){
            position.y = MainGame.HEIGHT - texture.getHeight();
        }else if(position.y < 0 ){
            position.y = 0;
        }
    }
}
