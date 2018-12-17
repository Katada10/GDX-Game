package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import game.GameMap;
import game.MapManager;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class Renderer extends SpriteRenderer{
	MapManager mapManager;
	
	public void init()
	{
		super.init();
		mapManager = new MapManager();
		Sprite sponge = new Sprite("spongebob.png", 5, 1);
		Sprite sponge2 = new Sprite("spongebob.png", 3, 1);
		
		mapManager.addObject(sponge, false);
		mapManager.addObject(sponge2, false);
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		mapManager.checkClicked();
		
		for (Sprite sprite : mapManager.sprites) {
			super.render(sprite);
		}
	}
	
	
	public void cleanUp()
	{
		super.dispose();
		mapManager.dispose();
	}
}
