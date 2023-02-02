package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import model.PongModel;
import state.PongState;
import views.GameScreen;
import views.MenuScreen;

public class Application extends ApplicationAdapter {
	public static SpriteBatch batch;
	/** SCREENS **/
	PongState state;
	Screen screen;
	public static int screenWidth, screenHeight;
	public float deltaTime;

	@Override
	public void create () {
		batch = new SpriteBatch();
		state = PongState.MENU;
		screen = new MenuScreen(this);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		deltaTime = Gdx.graphics.getDeltaTime();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		screen.render(deltaTime);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

	public void setState(PongState state) {
		this.state = state;
		if(state == PongState.MENU){
			setScreen(new MenuScreen(this));
		}
		else if(state == PongState.GAME_ACTIVE){
			setScreen(new GameScreen(this));
		}
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
}
