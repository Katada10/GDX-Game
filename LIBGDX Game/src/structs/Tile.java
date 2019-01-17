package structs;

public class Tile {
	public int x, y;
	public int xCoord, yCoord;
	public boolean isEmpty;
	
	public Tile(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	
		x = xCoord * Grid.tileSize;
		y = yCoord * Grid.tileSize;
		
		isEmpty = true;
	}
}
