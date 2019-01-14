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
	
	public SpriteRenderer()
	{
		batch = new SpriteBatch();
	}

	public void render(Sprite sprite)
	{
		batch.begin();
		
		batch.draw(sprite.getTexture(), sprite.getPos().x, sprite.getPos().y, sprite.scale, sprite.scale);
		
		batch.end();
	}
	
	public void dispose()
	{
		batch.dispose();
	}
}
