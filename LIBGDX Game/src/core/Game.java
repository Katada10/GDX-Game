package core;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import render.Renderer;

public class Game extends ApplicationAdapter{
	
	/*
	 * This class contains high level logic for running the game, 
	 * initializing, running, and then cleaning up.
	 */
	
	private Renderer renderer;
	
	public Game()
	{
		renderer = new Renderer();
	}
	
	@Override
	public void create() {
		Gdx.input.setInputProcessor(new Input());
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
