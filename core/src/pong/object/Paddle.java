package pong.object;

import pong.Application;

import pong.screen.GameScreen;

public abstract class Paddle {
    Board board;
    private int botY, topY, score;
    private final int startPos;
    private final float speed;

    public Paddle(Board board, float speed) {
        this.board = board;
        this.speed = speed;
        this.startPos = (Application.screenHeight / 2) - (GameScreen.paddleHeight / 2);
        this.botY = startPos;
        this.topY = startPos + GameScreen.paddleHeight;
        this.score = 0;
    }

    public abstract void update();

    public abstract int getPaddleEnd();

    protected void moveUp(){
        if( topY + speed <= board.getTop()){
            topY += speed;
            botY += speed;
        }
    }

    protected void moveDown(){
        if (botY - speed >= board.getBot()){
            topY -= speed;
            botY -= speed;
        }
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
