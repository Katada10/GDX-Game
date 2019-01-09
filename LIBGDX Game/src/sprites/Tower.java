package sprites;

import java.util.List;

import com.badlogic.gdx.Gdx;

import game.GameMap;
import managers.EnemyManager;
import structs.Grid;

public class Tower extends Sprite {

	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		this.type = type;
	}
}
