package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
	private CameraManager camManager;
	
	public void init()
	{
		camManager = new CameraManager();
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
	}
	
	
	public void cleanUp()
	{
		
	}
}
