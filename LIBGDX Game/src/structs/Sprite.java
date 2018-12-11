package structs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sprite {
	public Vector2 position;
	public Vector2 scale;
	public String name;
	
	private Texture texture;
	
	public Sprite(String path, String name) {
		position = new Vector2(0, 0);
		scale = new Vector2(0, 0);
		
		this.texture = new Texture(Gdx.files.internal("assets/"+path));
		this.name = name;
	}
	
	public Sprite(String path, Vector2 scale) {
		position = new Vector2(0, 0);
		this.scale = scale;
		name = "default";
		
				
		this.texture = new Texture(Gdx.files.internal("assets/"+path));	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
}
