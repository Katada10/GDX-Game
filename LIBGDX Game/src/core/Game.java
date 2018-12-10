package core;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;

import render.Renderer;

public class Game extends ApplicationAdapter{
	private Renderer renderer;
	
	public Game()
	{
		renderer = new Renderer();
	}
	
	@Override
	public void create() {
		renderer.init();
	}

	@Override
	public void dispose() {
		renderer.cleanUp();
	}

	@Override
	public void render() {
		renderer.render();
	}
}
