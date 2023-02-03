package pong.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import pong.Application;

import pong.object.Board;
import pong.object.Paddle;
import pong.screen.GameScreen;

public class RightPaddle extends Paddle {
    public RightPaddle(Board board, float speed) {
        super(board, speed);
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            moveUp();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            moveDown();
        }
    }

    @Override
    public int getPaddleEnd() {
        return Application.screenWidth - (GameScreen.paddleGoalMargin + GameScreen.paddleWidth);
    }
}
