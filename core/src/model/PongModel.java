package model;

import com.badlogic.gdx.Gdx;
import com.pong.game.Application;

import player.LeftPaddle;
import player.RightPaddle;
import views.GameScreen;

public class PongModel {
    Application app;
    private Board board;
    Paddle left, right;
    Ball ball;
    public float paddleSpeed, ballSpeed;

    public PongModel(Application app) {
        this.app = app;
        this.paddleSpeed = 450 * Gdx.graphics.getDeltaTime();
        this.ballSpeed = 300 * Gdx.graphics.getDeltaTime();

        this.board = new Board(Application.screenWidth, Application.screenHeight, GameScreen.wallHeight);

        this.ball = new Ball(this, board, Application.screenWidth / 2, Application.screenHeight / 2, ballSpeed);
        this.left = new LeftPaddle(board, paddleSpeed);
        this.right = new RightPaddle(board, paddleSpeed);
    }

    public void update(){
        left.update();
        right.update();
        ball.update();

        if (ball.getLeftX() <= 0){
            right.increaseScore();
            ball.resetPosition();
        } else if (ball.getRightX() >= board.getWidth()) {
            left.increaseScore();
            ball.resetPosition();
        }
    }

    public boolean ballPaddleCollision() {
        // Collide with left paddle
        if (ball.getBotY() >= left.getBotY() && ball.getTopY() <= left.getTopY() && ball.getLeftX() <= left.getPaddleEnd()){
            return true;
        }
        // Collide with right paddle
        else if(ball.getBotY() >= right.getBotY() && ball.getTopY() <= right.getTopY() && ball.getRightX() >= right.getPaddleEnd()){
            return true;
        }

        return false;
    }

    public Paddle getLeft() {
        return left;
    }

    public Paddle getRight() {
        return right;
    }

    public Ball getBall() {
        return ball;
    }
}
