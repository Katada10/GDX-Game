package game;

import java.util.*;

import structs.Grid;
import structs.Path;
import structs.Sprite;

public class EnemyManager {
	boolean waveStarted = false;
	private List<Sprite> aliveEnemies;
	private int waveSize = 2, spacing = 1, speed = 3;

	private Timer timer;

	public EnemyManager() {
		aliveEnemies = new ArrayList<>();
		timer = new Timer();
	}

	public void update() {
		if (!waveStarted) {

			Sprite s = GameMap.addObject("chicken.png", 0, 1, true);
			aliveEnemies.add(s);
			
			waveStarted = true;

		} else {
			for (Sprite sprite : aliveEnemies) {
				lead(sprite);
			}
		}
	}

	public void lead(Sprite sprite) {
		if (Grid.getTile(sprite.position).xCoord < Path.firstSizeX) {
			sprite.position.x += speed;
		} else if (Grid.getTile(sprite.position).yCoord != (Grid.yLen - 1) - (Path.sizeY + 2)) {
			sprite.position.y -= speed;
		} else if (Grid.getTile(sprite.position).xCoord >= Path.firstSizeX
				&& Grid.getTile(sprite.position).xCoord < Grid.xLen - 1) {
			sprite.position.x += speed;
		}
	}

}
