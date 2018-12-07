package core;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TwoRender {
	private SpriteBatch batch;
	
	private List<Texture> textures;
	
	public TwoRender()
	{
		batch = new SpriteBatch();
		textures = new ArrayList<>();
	}
	
	public void addTexture(String name)
	{
		textures.add(new Texture(Gdx.files.internal("assets/"+name + ".jpg")));
	}

	public void render()
	{
		batch.begin();
		
		//Need to implement "sprites" with positions
		
		batch.end();
	}
}
