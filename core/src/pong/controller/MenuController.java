package pong.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import pong.Application;

import pong.util.PongState;

public class MenuController extends InputAdapter {

    Application app;
    public MenuController(Application app) {
        this.app = app;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ENTER){
            app.setState(PongState.GAME_ACTIVE);
            Gdx.input.setInputProcessor(null);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        app.setState(PongState.GAME_ACTIVE);
        Gdx.input.setInputProcessor(null);
        return true;
    }
}
