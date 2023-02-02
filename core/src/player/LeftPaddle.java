package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import model.Board;
import model.Paddle;
import views.GameScreen;

public class LeftPaddle extends Paddle {
    public LeftPaddle(Board board, float speed) {
        super(board, speed);
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            moveDown();
        }
    }

    @Override
    public int getPaddleEnd() {
        return GameScreen.paddleGoalMargin + GameScreen.paddleWidth;
    }
}
