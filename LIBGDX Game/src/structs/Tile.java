package structs;

public class Tile {
	public int x, y;
	public boolean empty = true;
	public static int sizeX = 50, sizeY = 50;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
