package structs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import core.Main;

public class Sprite {
	public Vector2 position;
	public Vector2 scale;
	public String name;
	public Vector2 gridPosition;
	
	
	private Texture texture;
	
	public Sprite(String path, Vector2 scale, String name) {
		position = new Vector2(1, 1);
		
		this.scale = scale;
		this.texture = new Texture(Gdx.files.internal("assets/"+path));
		this.name = name;
	}
	
	public Sprite(String path, Vector2 scale, int gridX, int gridY, String name) {
		gridPosition = new Vector2(gridX, gridY);
		position = new Vector2();
		
		this.name = name;
		this.scale = scale;		
		this.texture = new Texture(Gdx.files.internal("assets/"+path));	
	}
	
	public Sprite(String path, Vector2 scale) {
		position = new Vector2(1, 1);
		this.scale = scale;
		name = "default";
				
		this.texture = new Texture(Gdx.files.internal("assets/"+path));	
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
}
