package game;

import java.util.*;

import com.badlogic.gdx.Gdx;

import structs.Grid;
import structs.Path;
import structs.Sprite;

public class EnemyManager {
	boolean waveStarted = false;
	public List<Sprite> aliveEnemies;
	private int waveSize = 0;
	
	private int spacing = 1;
	public static float speed = 0.05f;

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
				Sprite s = GameMap.addObject("chicken.png", 0, 1, true);
				aliveEnemies.add(s);
				canSpawn = false;
			}
			
			for (Sprite sprite : aliveEnemies) {
				lead(sprite);
			}
		}
	}

	public void lead(Sprite sprite) {
		if (Grid.getTile(sprite.position).xCoord < Path.firstSizeX) {
			sprite.position.x += (speed / Gdx.graphics.getDeltaTime());
		} else if (Grid.getTile(sprite.position).yCoord != (Grid.yLen - 1) - (Path.sizeY + 2)) {
			sprite.position.y -= (speed / Gdx.graphics.getDeltaTime());
		} /*else if (Grid.getTile(sprite.position).xCoord >= Path.firstSizeX
				&& Grid.getTile(sprite.position).xCoord < Grid.xLen - 1) {
			sprite.position.x += speed;
		}*/
	}

}
