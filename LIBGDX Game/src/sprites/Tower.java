package sprites;

import java.util.List;

import com.badlogic.gdx.Gdx;

import managers.EnemyManager;
import map.GameMap;
import structs.Grid;

public class Tower extends Sprite {
	
	public boolean shouldShoot = false, didDrawBullet = false;

	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		this.type = type;
	}
}
