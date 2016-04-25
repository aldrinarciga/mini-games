package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entitymanagers.EntityManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public abstract class MiniGame {

    public enum GameState{
        SHOW_INSTRUCTION, PLAY_STATE, POST_STATE, SHOW_PROGRESS, FINISH
    }

    public static final long INSTRUCTION_DURATION = 1000;
    public static final long PROGRESS_DURATION = 2000;
    public static final int TIME_HEIGHT = 30;
    protected String mainInstruction, subInstruction;
    protected long postGameDuration;
    protected Vector2 mainInstructionPosition, subInstructionPosition;
    protected long gameStartTime, instructionStartTime, postGameStartTime, progressStartTime;
    protected boolean isInstructionShown, isPostGameShown, isGameOver, isProgressShown, hasWon, isProgressUpdated;
    protected EntityManager entityManager;
    protected Texture totalTime = new Texture("white.jpg"), remainingTime = new Texture("black.png");
    protected Vector2 timePosition = new Vector2(0,MainGame.HEIGHT - TIME_HEIGHT);
    protected BitmapFont font;

    //METHODS
    public abstract void initGame();

    public abstract void initEntities();

    public abstract void update();

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void renderPostGame(SpriteBatch spriteBatch);

    public abstract void pause();

    public abstract void resume();

    public void gameWon(){
        isGameOver = true;
        hasWon = true;
    }

    public void gameLost(){
        isGameOver = true;
        hasWon = false;
    }

    public void dispose(){
        font.dispose();
        totalTime.dispose();
        remainingTime.dispose();
        entityManager.dispose();
    }

    public void renderInstruction(SpriteBatch spriteBatch){
        if(instructionStartTime == 0){
            instructionStartTime = System.currentTimeMillis();
        }

        font.draw(spriteBatch, mainInstruction, mainInstructionPosition.x, mainInstructionPosition.y);
        font.draw(spriteBatch, subInstruction, subInstructionPosition.x , subInstructionPosition.y);
    }

    public void renderProgress(SpriteBatch spriteBatch){
        if(progressStartTime == 0){
            progressStartTime = System.currentTimeMillis();
        }

        if(!isProgressUpdated){
            Game game = Game.getInstance();

            if(hasWon){
                game.addScore(100);
            }else{
                game.decreaseLives();
            }

            if(game.getNumGames()%3 == 0 && game.getStageDuration() > 2000){
                game.setStageDuration(game.getStageDuration() - 1000);
            }

            isProgressUpdated = true;
        }

        font.draw(spriteBatch, "Score: " + Game.getInstance().getScore(), 20, MainGame.HEIGHT - 20);
        font.draw(spriteBatch, "Lives: " + Game.getInstance().getLives(), 20 , MainGame.HEIGHT - 40);
        if(progressStartTime != 0 && Math.abs(progressStartTime - System.currentTimeMillis()) > PROGRESS_DURATION){
            isProgressShown = true;
        }
    }

    public GameState getGameState(){
        if(!isInstructionShown){
            return GameState.SHOW_INSTRUCTION;
        }else if(!isGameOver){
            return  GameState.PLAY_STATE;
        }else if(!isPostGameShown){
            return GameState.POST_STATE;
        }else if(!isProgressShown){
            return GameState.SHOW_PROGRESS;
        }else{
            return GameState.FINISH;
        }
    }

    protected void checkStatus(){
        if(instructionStartTime != 0 && Math.abs(System.currentTimeMillis() - instructionStartTime) > INSTRUCTION_DURATION) {
            isInstructionShown = true;
        }

        if(postGameStartTime != 0 && Math.abs(System.currentTimeMillis() - postGameStartTime) > postGameDuration) {
            isPostGameShown = true;
        }
    }

    protected void renderTime(SpriteBatch spriteBatch){
        spriteBatch.draw(totalTime, timePosition.x, timePosition.y, MainGame.WIDTH, TIME_HEIGHT);
        long diff = Math.abs(System.currentTimeMillis() - gameStartTime);
        double percent = (double)diff/Game.getInstance().getStageDuration();
        long remain = (long) ((MainGame.WIDTH * percent));
        spriteBatch.draw(remainingTime, timePosition.x, timePosition.y, remain, TIME_HEIGHT);
        if(diff > Game.getInstance().getStageDuration()){
            if(!isGameOver){
                isGameOver = true;
                hasWon = false;
            }
        }
    }
}
