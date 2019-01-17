package sprites;

public class Enemy extends Sprite{
	/*
	 * Defines an extension of a sprite, and enemy with a shared speed and image among all enemies.
	 */
	public float speed;
	
	public Enemy(int gridX, int gridY) {
		super("chicken.png", gridX, gridY);
		speed = 0.05f;
		type = ENEMY;
	}

}
