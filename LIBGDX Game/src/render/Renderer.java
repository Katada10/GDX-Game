package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import game.GameMap;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class Renderer {
	TwoRender render;
	GameMap map;
	
	public void init()
	{
		render = new TwoRender();
		map = new GameMap(render, "background.jpg");
		Sprite sponge = new Sprite("spongebob.png", new Vector2(Tile.sizeX,Tile.sizeY), 10, 10);
		
		map.drawGrid();
		map.addObject(sponge);
		
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
