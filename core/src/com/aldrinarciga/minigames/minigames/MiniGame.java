package com.aldrinarciga.minigames.minigames;

import com.aldrinarciga.minigames.Game;
import com.aldrinarciga.minigames.MainGame;
import com.aldrinarciga.minigames.entitymanagers.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public abstract class MiniGame {

    public enum GameState{
        SHOW_INSTRUCTION, PLAY_STATE, POST_STATE, SHOW_PROGRESS, FINISH
    }

    public static final long INSTRUCTION_DURATION = 2000;
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
    protected BitmapFont commonFont, fontForMainInstruction, fontForSubInstruction;
    protected FreeTypeFontGenerator generator;
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public MiniGame(String mainInstruction, String subInstruction,
                    Vector2 mainInstructionPosition, Vector2 subInstructionPosition,
                    long postGameDuration, EntityManager entityManager){
        //INIT
        gameStartTime = System.currentTimeMillis();
        this.mainInstruction = mainInstruction;
        this.subInstruction = subInstruction;
        this.mainInstructionPosition = mainInstructionPosition;
        this.subInstructionPosition = subInstructionPosition;
        this.postGameDuration = postGameDuration;
        this.entityManager = entityManager;

        //FONTS
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/avenir.ttc"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;
        parameter.shadowOffsetX = 3;
        fontForMainInstruction = generator.generateFont(parameter);
        parameter.size = 25;
        fontForSubInstruction = generator.generateFont(parameter);
        commonFont = generator.generateFont(parameter);

        initEntities();
    }

    //METHODS
    public abstract void initEntities();

    public void pause(){
        entityManager.pause();
    }

    public void resume(){
        entityManager.resume();
    }


    public void update(){
        entityManager.update();
        checkStatus();
    }

    public void render(SpriteBatch spriteBatch){
        entityManager.render(spriteBatch);
        renderTime(spriteBatch);
    }

    public void renderPostGame(SpriteBatch spriteBatch){
        if(postGameStartTime == 0){
            postGameStartTime = System.currentTimeMillis();
        }

        commonFont.draw(spriteBatch, "POST GAME : " + (hasWon ? "WON" : "LOST"), 20, MainGame.HEIGHT - 20);
    }

    public void gameWon(){
        isGameOver = true;
        hasWon = true;
        pause();
    }

    public void gameLost(){
        isGameOver = true;
        hasWon = false;
        pause();
    }

    public void dispose(){
        commonFont.dispose();
        totalTime.dispose();
        remainingTime.dispose();
        entityManager.dispose();
        generator.dispose();
    }

    public void renderInstruction(SpriteBatch spriteBatch){
        if(instructionStartTime == 0){
            instructionStartTime = System.currentTimeMillis();
        }
        fontForMainInstruction.draw(spriteBatch, mainInstruction, mainInstructionPosition.x, mainInstructionPosition.y);
        fontForSubInstruction.draw(spriteBatch, subInstruction, subInstructionPosition.x , subInstructionPosition.y);
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

        commonFont.draw(spriteBatch, "Score: " + Game.getInstance().getScore(), 20, MainGame.HEIGHT - 20);
        commonFont.draw(spriteBatch, "Lives: " + Game.getInstance().getLives(), 20, MainGame.HEIGHT - 50);
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
                gameLost();
            }
        }
    }
}
