package pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import pong.util.PongState;
import pong.screen.GameScreen;
import pong.screen.MenuScreen;

public class Application extends ApplicationAdapter {
	public static SpriteBatch batch;
	private Screen screen;
	public static int screenWidth, screenHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screen = new MenuScreen(this);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}

	public void setState(PongState state) {
		if(state == PongState.MENU){
			screen = new MenuScreen(this);
		}
		else if(state == PongState.GAME_ACTIVE){
			screen = new GameScreen();
		}
	}
}
