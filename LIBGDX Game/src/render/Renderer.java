package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import game.GameMap;
import sprites.Sprite;
import structs.Grid;
import structs.Tile;

public class Renderer extends SpriteRenderer{
	GameMap gameMap;
	
	public void init()
	{
		super.init();
		gameMap = new GameMap();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		gameMap.update();
		
		for (Sprite sprite : GameMap.sprites) {
			super.render(sprite);
		}
	}
	
	
	public void cleanUp()
	{
		super.dispose();
		gameMap.dispose();
	}
}
