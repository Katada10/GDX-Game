package render;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import structs.Sprite;

public class TwoRender {
	
	private SpriteBatch batch;
	
	public List<Sprite> sprites;
	
	public TwoRender()
	{
		batch = new SpriteBatch();
		sprites = new ArrayList<>();
	}

	public void render()
	{
		batch.begin();
		
		for (Sprite sprite : sprites) {
			batch.draw(sprite.getTexture(), sprite.position.x, sprite.position.y, sprite.scale.x, sprite.scale.y);
		}
		
		batch.end();
	}
	
	public void dispose()
	{
		batch.dispose();
		sprites.clear();
	}
}
