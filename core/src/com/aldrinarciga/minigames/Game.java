package com.aldrinarciga.minigames;

import com.aldrinarciga.minigames.camera.OrthoCamera;
import com.aldrinarciga.minigames.minigames.GameOne;
import com.aldrinarciga.minigames.minigames.GameTwo;
import com.aldrinarciga.minigames.minigames.MiniGame;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class Game {

    private static Game instance;
    private int score;
    private int lives;
    private int stageDuration;
    private MiniGame currentGame;
    private int numGames;
    private OrthoCamera camera;

    private Game(){
        score = 0;
        lives = 3;
        stageDuration = 5000;
        numGames = 0;
    }

    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

    public static Game newGame(OrthoCamera camera){
        instance = new Game();
        instance.camera = camera;
        return instance;
    }

    public void generateGame(){
        int rnd = (int) (Math.random() * 2) + 1;
        switch (rnd){
            case 1:
                currentGame = new GameOne();
                break;
            case 2:
                currentGame = new GameTwo();
                break;
            default:
                currentGame = new GameOne();
                break;
        }

        currentGame = new GameTwo();

        numGames++;
    }

    public MiniGame getCurrentGame() {
        if(currentGame == null)
            generateGame();
        return currentGame;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getStageDuration() {
        return stageDuration;
    }

    public void setStageDuration(int stageDuration) {
        this.stageDuration = stageDuration;
    }

    public int decreaseLives(){
        lives--;
        return lives;
    }

    public int addScore(int toAdd){
        score+= toAdd;
        return score;
    }

    public int getNumGames() {
        return numGames;
    }

    public OrthoCamera getCamera() {
        return camera;
    }
}
