package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.Main;
import game.GameMap;
import structs.Grid;
import structs.Sprite;

public class Renderer {
	TwoRender render;
	GameMap map;
	
	public void init()
	{
		render = new TwoRender();
		map = new GameMap(render);
		
		Sprite back = new Sprite("background.jpg", "background");
		back.scale.x = Main.WIDTH;
		back.scale.y = Main.HEIGHT;
		
		render.sprites.add(back);
		map.drawGrid();
		
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
