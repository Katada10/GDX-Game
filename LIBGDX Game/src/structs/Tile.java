package structs;

public class Tile {
	public int x, y;
	public int xCoord, yCoord;
	
	public boolean isEmpty;
	public int objectIndex;
	
	public static int sizeX = 100, sizeY = 100;
	
	public Tile(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	
		x = xCoord * sizeX;
		y = yCoord * sizeY;
		
		isEmpty = true;
	}
}
