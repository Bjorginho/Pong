package pong.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import pong.Application;

import pong.controller.MenuController;

public class MenuScreen extends ScreenAdapter {

    BitmapFont font;
    MenuController controller;
    public MenuScreen(Application app) {
        this.font = new BitmapFont();
        controller = new MenuController(app);
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(72, 71, 74, (float) 3);
        Application.batch.begin();
        font.draw(Application.batch, "Welcome to Pong!", (float) Application.screenWidth / 2, (float) Application.screenHeight / 2);
        font.draw(Application.batch, "Touch anywhere to start!", (float) Application.screenWidth / 2, (float) Application.screenHeight / 2 - 30);
        Application.batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
