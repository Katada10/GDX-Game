package render;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.Main;
import sprites.Sprite;
import structs.Grid;

public class SpriteRenderer {
	private SpriteBatch batch;
	private float objectScale = Grid.tileSize;
	
	
	public void init()
	{
		batch = new SpriteBatch();
		
	}

	public void render(Sprite sprite, boolean isBackground)
	{
		batch.begin();
		
		if(isBackground)
		{
			objectScale = Main.SCALE;
		}
		else
		{
			objectScale = Grid.tileSize;
		}
		
		batch.draw(sprite.getTexture(), sprite.getPos().x, sprite.getPos().y, objectScale, objectScale);
		
		
		batch.end();
	}
	
	public void dispose()
	{
		batch.dispose();
	}
}
