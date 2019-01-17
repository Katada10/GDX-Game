package render;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import core.Main;
import sprites.Sprite;
import structs.Grid;

public class SpriteRenderer {
	private SpriteBatch batch;
	
	/*
	 * 
	 * This class only handles rendering individual sprites, then another class loops through
	 * a list of sprites and calls the render method on each one. 
	 * 
	 */
	
	public SpriteRenderer()
	{
		batch = new SpriteBatch();
		
	}
	
	/**
	 * Renders a sprite onto the screen.
	 * @param sprite
	 */
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
