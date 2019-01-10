package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import structs.Grid;

public class Sprite {
	public Vector2 position;
	private Vector2 gridPosition;	
	
	public float scale;
	
	private Texture texture;

	public int type = 3;
	public String texName;
	
	public static final int TOWER = 0, ENEMY = 1, MODEL = 2;
	
	public Sprite(String path,int gridX, int gridY) {
		gridPosition = new Vector2(gridX, gridY);
		position = new Vector2();

		position.x = Grid.tiles[(int)gridPosition.y][(int)gridPosition.x].x;
		position.y = Grid.tiles[Grid.len - (int)gridPosition.y - 1][(int)gridPosition.x].y;
		
		scale = Grid.tileSize;
		this.texture = new Texture(Gdx.files.internal("assets/"+path));	
		texName = path;
	}
	
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

	public void setPos(int x, int y)
	{
		gridPosition.x = x;
		gridPosition.y = y;
		
		position.x = Grid.tiles[(int)gridPosition.y][(int)gridPosition.x].x;
		position.y = Grid.tiles[(Grid.len - 1) - (int)gridPosition.y][(int)gridPosition.x].y;
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
