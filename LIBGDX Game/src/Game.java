import com.badlogic.gdx.ApplicationListener;

import core.Renderer;

public class Game implements ApplicationListener{

	private Renderer renderer;
	
	public Game()
	{
		renderer = new Renderer();
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		renderer.init();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		renderer.cleanUp();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		renderer.render();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	

}
