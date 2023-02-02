package views;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pong.game.Application;

import model.PongModel;

public class GameScreen extends ScreenAdapter {
    Application app;
    PongModel model;
    BitmapFont font;
    ShapeRenderer ball, topWall, bottomWall, leftPaddle, rightPaddle;
    public static final int paddleHeight = Application.screenHeight / 4;
    public static final int paddleWidth = paddleHeight / 8;
    public static final int wallHeight = paddleWidth;
    public static final int paddleGoalMargin = paddleHeight / 8;
    public static final int pongMapTop = Application.screenHeight - wallHeight;
    public static final int pongMapBottom = wallHeight;

    public GameScreen(Application app) {
        this.app = app;
        this.font = new BitmapFont();
        this.ball = new ShapeRenderer();
        this.topWall = new ShapeRenderer();
        this.bottomWall = new ShapeRenderer();
        this.leftPaddle = new ShapeRenderer();
        this.rightPaddle = new ShapeRenderer();

        ball.setColor(Color.WHITE); topWall.setColor(Color.WHITE); bottomWall.setColor(Color.WHITE); leftPaddle.setColor(Color.WHITE); rightPaddle.setColor(Color.WHITE);
        this.model = new PongModel(app, pongMapTop, pongMapBottom);
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
        topWall.begin(ShapeType.Filled);
        topWall.rect(0, Application.screenHeight - wallHeight, Application.screenWidth, wallHeight / 2);
        topWall.end();

        bottomWall.begin(ShapeType.Filled);
        bottomWall.rect(0, wallHeight / 2, Application.screenWidth, wallHeight / 2);
        bottomWall.end();
    }

    private void drawBall(){
        ball.begin(ShapeType.Filled);
        ball.circle(model.getBall().getX(), model.getBall().getY(), model.getBall().getRadius());
        ball.end();
    }

    private void drawPaddles(){
        leftPaddle.begin(ShapeType.Filled);
        leftPaddle.rect(paddleGoalMargin, model.getLeftPaddle().getBotY(), paddleWidth, paddleHeight);
        leftPaddle.end();

        rightPaddle.begin(ShapeType.Filled);
        rightPaddle.rect(Application.screenWidth - paddleGoalMargin - paddleWidth, model.getRightPaddle().getBotY(),  paddleWidth, paddleHeight);
        rightPaddle.end();
    }

    private void drawScore() {
        font.draw(
                Application.batch,
                model.getLeftPaddle().getScore() + " --- " + model.getRightPaddle().getScore(),
                Application.screenWidth / 2,
                100
        );
    }

    @Override
    public void dispose() {
        font.dispose();
        ball.dispose();
        topWall.dispose();
        bottomWall.dispose();
        leftPaddle.dispose();
        rightPaddle.dispose();
    }
}
