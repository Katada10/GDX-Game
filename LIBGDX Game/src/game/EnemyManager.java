package game;

import java.util.*;

import com.badlogic.gdx.Gdx;

import core.Main;
import sprites.Enemy;
import sprites.Sprite;
import structs.Grid;
import structs.Path;

public class EnemyManager {
	boolean waveStarted = false;
	public static List<Enemy> aliveEnemies;
	private static int waveSize = 0;

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
				Enemy s = (Enemy) GameMap.addObject(new Enemy(0, 1), true);
				aliveEnemies.add(s);
				canSpawn = false;
			}

			for (Enemy sprite : aliveEnemies) {
				lead(sprite);
			}
		}
	}

	public void lead(Enemy sprite) {
		try {
			if (Grid.getTile(sprite.position).xCoord < Path.firstSizeX) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (Grid.len - Grid.getTile(sprite.position).yCoord - 1 < 4) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (Grid.getTile(sprite.position).xCoord >= Path.firstSizeX
					&& Grid.getTile(sprite.position).xCoord < 4) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (Grid.len - Grid.getTile(sprite.position).yCoord - 1 <= 7) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void kill(Enemy enemy) {
		aliveEnemies.remove(enemy);
		waveSize--;

	}

}
