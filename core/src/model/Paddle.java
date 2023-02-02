package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import views.GameScreen;

public class Paddle {
    PongModel model;
    private int botY, topY, score, startPos;
    private final boolean isLeft;
    private final float paddleSpeed;


    public Paddle(PongModel model, int bottomPaddleY, float paddleSpeed, boolean isLeft) {
        this.model = model;
        this.paddleSpeed = paddleSpeed;
        this.startPos = bottomPaddleY;
        this.botY = bottomPaddleY;
        this.topY = bottomPaddleY + GameScreen.paddleHeight;
        this.score = 0;
        this.isLeft = isLeft;
    }

    public void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if(isLeft){
                moveUp();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if(isLeft){
                moveDown();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(!isLeft){
                moveUp();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(!isLeft){
                moveDown();
            }
        }
    }

    public void moveUp(){
        if (model.canMoveUp(topY)){
            topY += paddleSpeed;
            botY += paddleSpeed;
        }
    }

    public void moveDown(){
        if (model.canMoveDown(botY)){
            topY -= paddleSpeed;
            botY -= paddleSpeed;
        }
    }

    public boolean isLeft() {
        return isLeft;
    }

    public int getBotY() {
        return botY;
    }

    public int getTopY() {
        return topY;
    }

    public void increaseScore(){
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void reset(){
        this.score = 0;
        this.botY = startPos;
        this.topY = botY +  + GameScreen.paddleHeight;
    }
}
