package model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.pong.game.Application;

import views.GameScreen;

public class PongModel {
    Application app;
    Ball ball;
    Paddle leftPaddle, rightPaddle;
    public float paddleSpeed, ballSpeed;
    public int pongTop, pongBot;

    public PongModel(Application app, int pongTop, int pongBot) {
        this.app = app;
        this.pongTop = pongTop;
        this.pongBot = pongBot;
        this.paddleSpeed = 450 * Gdx.graphics.getDeltaTime();
        this.ballSpeed = 300 * Gdx.graphics.getDeltaTime();

        this.ball = new Ball(app, this, ballSpeed);
        int paddleStartPos = Application.screenHeight / 2 - GameScreen.paddleHeight / 2;
        this.leftPaddle = new Paddle(this, paddleStartPos, paddleSpeed, true);
        this.rightPaddle = new Paddle(this, paddleStartPos, paddleSpeed, false);
    }

    public void update(){
        leftPaddle.update();
        rightPaddle.update();
        ball.update();
    }

    protected boolean ballWallCollision() {
        return (ball.getY() - ball.getRadius() <= pongBot || // Collide with bottom wall
                ball.getY() + ball.getRadius() >= pongTop); //  // Collide with top wall
    }

    public boolean ballPaddleCollision() {
        int leftPaddleTop = leftPaddle.getTopY();
        int leftPaddleBot = leftPaddle.getBotY();
        int leftPaddleX = GameScreen.paddleGoalMargin + GameScreen.paddleWidth;

        int rightPaddleTop = rightPaddle.getTopY();
        int rightPaddleBot = rightPaddle.getBotY();
        int rightPaddleX = Application.screenWidth - (GameScreen.paddleGoalMargin + GameScreen.paddleWidth);

        int ballX = ball.getX();
        int ballY = ball.getY();

        // Collide with left paddle
        if (ballY >= leftPaddleBot && ballY <= leftPaddleTop && ballX <= leftPaddleX + ball.getRadius()){
            return true;
        }
        // Collide with right paddle
        else if(ballY >= rightPaddleBot && ballY <= rightPaddleTop && ballX >= rightPaddleX - ball.getRadius()){
            return true;
        }

        return false;
    }

    public boolean canMoveUp(int paddleTop) {
        return paddleTop + paddleSpeed < Application.screenHeight - GameScreen.wallHeight;
    }

    public boolean canMoveDown(int paddleBottom) {
        return paddleBottom - paddleSpeed > GameScreen.wallHeight;
    }
    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Ball getBall() {
        return ball;
    }
}
