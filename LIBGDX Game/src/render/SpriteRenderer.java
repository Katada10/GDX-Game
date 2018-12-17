package render;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import structs.Sprite;

public class SpriteRenderer {
	private SpriteBatch batch;
	
	public void init()
	{
		batch = new SpriteBatch();
	}

	public void render(Sprite sprite)
	{
		batch.begin();
		
		batch.draw(sprite.getTexture(), sprite.getPos().x, sprite.getPos().y, sprite.scale.x, sprite.scale.y);
		
		batch.end();
	}
	
	public void dispose()
	{
		batch.dispose();
	}
}
