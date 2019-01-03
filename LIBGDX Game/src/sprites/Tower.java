package sprites;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;

import game.GameMap;
import structs.Grid;

public class Tower extends Sprite {

	boolean didDrawBullet = false, hit = false;
	Sprite bullet = null;
	int hitX = 0, hitY = 0;
	

	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		this.type = type;
	}

	public void shoot(List<Enemy> enemies) {

		for (Enemy enemy : enemies) {
			if (!didDrawBullet) {
				bullet = GameMap.addObject(new Sprite("bullet.png", this.getGridX(), this.getGridY()), true);
				didDrawBullet = true;
			} else {
				lead(bullet, enemy);
			}
		}
	}

	private void lead(Sprite bullet, Enemy enemy) {
		float dx = enemy.position.x - bullet.position.x;
		float dy = enemy.position.y - bullet.position.y;
		float bulletSpeed = 10;
		
		if(hitX == 1 && hitY == 1)
		{
			hit = true;
		}
		
		if (!hit) {
			if (Math.abs(dx) > 10) {
				bullet.position.x -= (Math.abs(dx) * Gdx.graphics.getDeltaTime()) * bulletSpeed;
			}
			else
			{
				hitX = 1;
			}
			
			
			if (Math.abs(dy) > 10) {
				bullet.position.y += (Math.abs(dy) * Gdx.graphics.getDeltaTime()) * bulletSpeed;
			}
			else
			{
				hitY = 1;
			}
		}
		else {
			GameMap.sprites.remove(bullet);
			GameMap.sprites.remove(enemy);
		}
	}
}
