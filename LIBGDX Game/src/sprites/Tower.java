package sprites;


import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;

import game.GameMap;
import structs.Grid;

public class Tower extends Sprite{
	
	public String tag;
	
	public Tower(int gridX, int gridY) {
		super("tower.png", gridX, gridY);
	}

	public Tower(int gridX, int gridY, String tag)
	{
		super("tower.png", gridX, gridY);
		this.tag = tag;
	}

	public void fireBullet(Enemy enemy) {
		Sprite bullet = new Sprite("bullet.png", (int) getGridX(), (int) getGridY());
		GameMap.sprites.add(bullet);
		lead(bullet, enemy);
	}

	private void lead(Sprite bullet, Enemy enemy) {
		
		
	}

	public boolean shouldShoot(Enemy enemy) {
		int dx = Math.abs((int) (position.x - enemy.position.x));
		int dy = Math.abs((int) (position.y - enemy.position.y));

		if (dx < (Grid.tileSize * 2) && dy < (Grid.tileSize * 2)) {
			return true;
		}

		return false;
	}
	
}
