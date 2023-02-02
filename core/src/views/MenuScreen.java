package views;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pong.game.Application;

import controller.MenuController;

public class MenuScreen extends ScreenAdapter {

    Application app;
    BitmapFont font;
    MenuController controller;
    public MenuScreen(Application app) {
        this.app = app;
        this.font = new BitmapFont();
        controller = new MenuController(app);
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(72, 71, 74, (float) 3);
        Application.batch.begin();
        font.draw(Application.batch, "Welcome to Pong!", Application.screenWidth / 2, Application.screenHeight / 2);
        font.draw(Application.batch, "Touch anywhere to start!", Application.screenWidth / 2, Application.screenHeight / 2 - 30);
        Application.batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
