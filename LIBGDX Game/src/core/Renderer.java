package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import structs.Sprite;

public class Renderer {
	TwoRender render;
	
	public void init()
	{
		render = new TwoRender();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		render.render();
	}
	
	
	public void cleanUp()
	{
		render.dispose();
	}
}
