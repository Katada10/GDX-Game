package game;

import java.util.*;

import com.badlogic.gdx.Gdx;

import sprites.Enemy;
import sprites.Sprite;
import structs.Grid;
import structs.Path;

public class EnemyManager {
	boolean waveStarted = false;
	public static List<Enemy> aliveEnemies;
	private static int waveSize = 1;
	
	private int spacing = 1;

	boolean canSpawn = false;

	private Timer timer;

	public EnemyManager() {
		aliveEnemies = new ArrayList<>();
		timer = new Timer();
	}

	public void update() {
		if (!waveStarted) {
			waveStarted = true;
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					canSpawn = true;
				}

			}, 0, spacing * 1000);

		} else {

			if (canSpawn && aliveEnemies.size() <= waveSize) {
				Enemy s =(Enemy)GameMap.addObject(new Enemy(0, 1), true);
				aliveEnemies.add(s);
				canSpawn = false;
			}
			
			for (Enemy sprite : aliveEnemies) {
				lead(sprite);
			}
		}
	}

	public void lead(Enemy sprite) {
		if (Grid.getTile(sprite.position).xCoord < Path.firstSizeX) {
			sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
		} else if (Grid.getTile(sprite.position).yCoord != (Grid.yLen - 1) - (Path.sizeY + 2)) {
			sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
		} else if (Grid.getTile(sprite.position).xCoord >= Path.firstSizeX
				&& Grid.getTile(sprite.position).xCoord < Grid.xLen - 1) {
			sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
		}
	}

	public static void kill(Enemy enemy) {
		aliveEnemies.remove(enemy);
		waveSize--;
		
	}

}
