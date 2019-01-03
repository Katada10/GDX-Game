package sprites;

public class Enemy extends Sprite{
	public float speed;
	
	public Enemy(int gridX, int gridY) {
		super("chicken.png", gridX, gridY);
		speed = 0.04f;
		type = ENEMY;
	}
	

}
