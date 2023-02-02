package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pong.game.Application;

import model.Board;
import model.Paddle;
import views.GameScreen;

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
