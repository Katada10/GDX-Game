package structs;

import com.badlogic.gdx.graphics.Texture;

public class Tile {
	private Texture texture;
	public static int sizeX = 50, sizeY = 50;
	
	public Tile(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}
}
