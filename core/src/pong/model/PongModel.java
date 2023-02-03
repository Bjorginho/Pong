package pong.model;

import com.badlogic.gdx.Gdx;
import pong.Application;

import pong.object.Ball;
import pong.object.Board;
import pong.object.Paddle;
import pong.player.LeftPaddle;
import pong.player.RightPaddle;
import pong.screen.GameScreen;

public class PongModel {
    private final Board board;
    private final Paddle leftPlayer;
    private final Paddle rightPlayer;
    private final Ball ball;
    public float paddleSpeed, ballSpeed;

    public PongModel() {
        this.paddleSpeed = 450 * Gdx.graphics.getDeltaTime();
        this.ballSpeed = 300 * Gdx.graphics.getDeltaTime();

        this.board = new Board(Application.screenWidth, Application.screenHeight, GameScreen.wallHeight);

        this.ball = new Ball(this, board, Application.screenWidth / 2, Application.screenHeight / 2, ballSpeed);
        this.leftPlayer = new LeftPaddle(board, paddleSpeed);
        this.rightPlayer = new RightPaddle(board, paddleSpeed);
    }

    public void update(){
        leftPlayer.update();
        rightPlayer.update();
        ball.update();

        if (ball.getLeftX() <= 0){
            rightPlayer.increaseScore();
            ball.resetPosition();
        } else if (ball.getRightX() >= board.getWidth()) {
            leftPlayer.increaseScore();
            ball.resetPosition();
        }
    }

    public boolean ballPaddleCollision() {
        // Collide with left paddle
        if (ball.getBotY() >= leftPlayer.getBotY() && ball.getTopY() <= leftPlayer.getTopY() && ball.getLeftX() <= leftPlayer.getPaddleEnd()){
            return true;
        }
        // Collide with right paddle
        else if(ball.getBotY() >= rightPlayer.getBotY() && ball.getTopY() <= rightPlayer.getTopY() && ball.getRightX() >= rightPlayer.getPaddleEnd()){
            return true;
        }

        return false;
    }

    public Paddle getLeftPlayer() {
        return leftPlayer;
    }

    public Paddle getRightPlayer() {
        return rightPlayer;
    }

    public Ball getBall() {
        return ball;
    }
}
