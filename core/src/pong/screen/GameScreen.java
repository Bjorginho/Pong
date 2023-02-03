package pong.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import pong.Application;

import pong.model.PongModel;

public class GameScreen extends ScreenAdapter {
    PongModel model;
    BitmapFont font;
    ShapeRenderer ball, wall, paddle;
    public static final int paddleHeight = Application.screenHeight / 4;
    public static final int paddleWidth = paddleHeight / 8;
    public static final int paddleGoalMargin = paddleHeight / 8;
    public static final int wallHeight = paddleWidth;

    public GameScreen() {
        this.font = new BitmapFont();
        this.ball = new ShapeRenderer();
        this.wall = new ShapeRenderer();
        this.paddle = new ShapeRenderer();

        ball.setColor(Color.WHITE);
        wall.setColor(Color.WHITE);
        paddle.setColor(Color.WHITE);

        this.model = new PongModel();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        Application.batch.begin();
        drawWalls();
        drawBall();
        drawPaddles();
        drawScore();
        Application.batch.end();

        model.update();
    }

    private void drawWalls() {
        // Top wall
        wall.begin(ShapeType.Filled);
        wall.rect(0, Application.screenHeight - wallHeight, Application.screenWidth, (float) wallHeight / 2);
        wall.end();

        // Bottom wall
        wall.begin(ShapeType.Filled);
        wall.rect(0, (float) wallHeight / 2, Application.screenWidth, (float) wallHeight / 2);
        wall.end();
    }

    private void drawBall(){

        ball.begin(ShapeType.Filled);
        ball.setColor(Color.RED);
        ball.rect(model.getBall().getLeftX(), model.getBall().getBotY(), model.getBall().getRadius() * 2, model.getBall().getRadius() * 2);
        ball.end();

        ball.setColor(Color.WHITE);
        ball.begin(ShapeType.Filled);
        ball.circle(model.getBall().getX(), model.getBall().getY(), model.getBall().getRadius());
        ball.end();
    }

    private void drawPaddles(){
        // Left paddle
        paddle.begin(ShapeType.Filled);
        paddle.rect(paddleGoalMargin, model.getLeftPlayer().getBotY(), paddleWidth, paddleHeight);
        paddle.end();

        // Right paddle
        paddle.begin(ShapeType.Filled);
        paddle.rect(Application.screenWidth - paddleGoalMargin - paddleWidth, model.getRightPlayer().getBotY(),  paddleWidth, paddleHeight);
        paddle.end();
    }

    private void drawScore() {
        font.draw(
                Application.batch,
                model.getLeftPlayer().getScore() + " --- " + model.getRightPlayer().getScore(),
                (float) Application.screenWidth / 2,
                100
        );
    }

    @Override
    public void dispose() {
        font.dispose();
        ball.dispose();
        wall.dispose();
        paddle.dispose();
    }
}
