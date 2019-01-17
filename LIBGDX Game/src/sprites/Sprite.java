package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import structs.Grid;

public class Sprite {
	/*
	 * A master class that anything rendered on the screen uses such as a position, and image, and a size.
	 */
	public Vector2 position;
	private Vector2 gridPosition;	
	
	public float scale;
	
	private Texture texture;

	public int type = 3;
	public String textureName;
	
	public static final int TOWER = 0, ENEMY = 1, MODEL = 2;
	
	/**
	 * Creates a sprite that is the size of one tile at the given grid x and y, 
	 * witht he given image path
	 * @param imagePath
	 * @param gridX
	 * @param gridY
	 */
	public Sprite(String imagePath,int gridX, int gridY) {
		gridPosition = new Vector2(gridX, gridY);
		position = new Vector2();

		position.x = Grid.tiles[(int)gridPosition.y][(int)gridPosition.x].x;
		position.y = Grid.tiles[Grid.length - (int)gridPosition.y - 1][(int)gridPosition.x].y;
		
		scale = Grid.tileSize;
		this.texture = new Texture(Gdx.files.internal("assets/"+imagePath));	
		textureName = imagePath;
	}
	
	/**
	 * Special constructor, initializes sprite with given backgroundName to screen dimensions, 
	 * used to create background image.
	 * @param backgroundName
	 */
	public Sprite(String backgroundName) {
		position = new Vector2();
		
		this.texture = new Texture(Gdx.files.internal("assets/"+backgroundName));
		scale = Main.SCALE;
	}

	public Texture getTexture()
	{
		return texture;
	}
	
	
	public Vector2 getPos()
	{
		return position;
	}

	/**
	 * Sets the correct position in pixel and grid coordinates.
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y)
	{
		gridPosition.x = x;
		gridPosition.y = y;
		
		position.x = Grid.tiles[(int)gridPosition.y][(int)gridPosition.x].x;
		position.y = Grid.tiles[(Grid.length - 1) - (int)gridPosition.y][(int)gridPosition.x].y;
	}

	public int getGridX() {
		return (int)gridPosition.x;
	}
	
	public int getGridY() {
		return (int)gridPosition.y;
	}
	
	public float getY()
	{
		return position.y;
	}
}
