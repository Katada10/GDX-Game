package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import core.Main;
import structs.Grid;

public class Sprite {
	public Vector2 scale;

	public Vector2 position;
	private Vector2 gridPosition;
	private Texture texture;
	
	public String texName;
	
	public Sprite(String path,int gridX, int gridY) {
		gridPosition = new Vector2(gridX, gridY);
		scale = new Vector2(Grid.tileSize, Grid.tileSize);
		position = new Vector2();

		position.x = Grid.tiles[(int)gridPosition.y][(int)gridPosition.x].x;
		position.y = Grid.tiles[(Grid.yLen - 1) - (int)gridPosition.y][(int)gridPosition.y].y;
		
		this.texture = new Texture(Gdx.files.internal("assets/"+path));	
		texName = path;
	}
	
	public Sprite(String backgroundName) {
		scale = new Vector2(Main.WIDTH, Main.HEIGHT);
		position = new Vector2();
		
		this.texture = new Texture(Gdx.files.internal("assets/"+backgroundName));	
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
		position.y = Grid.tiles[(Grid.yLen - 1) - (int)gridPosition.y][(int)gridPosition.x].y;
	}

	public int getGridX() {
		return (int)gridPosition.x;
	}
	
	public int getGridY() {
		return (int)gridPosition.y;
	}
}
