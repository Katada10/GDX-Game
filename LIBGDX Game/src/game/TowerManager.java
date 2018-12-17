package game;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class TowerManager {
	private MapManager map;

	Sprite currentTower = null;
	boolean towerSelected = false;

	public TowerManager(MapManager map) {
		this.map = map;
	}

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !towerSelected) {
			currentTower = map.sprites.get(t.objectIndex);
			towerSelected = true;
		}

		if (towerSelected && Input.dragging) {
			drag(currentTower);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}
	}

	public void drag(Sprite tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) tower.getGridPosition().y][(int) tower.getGridPosition().x];

		if (newTile.isEmpty) {
			if (tower.spriteName == "modelTower") {
				map.addObject(tower.texName, (int) tower.getGridPosition().x, (int) tower.getGridPosition().y, false,
						"modelTower");
				tower.spriteName = "default";
			}
			if (newTile.xCoord != currentTile.xCoord) {
				tower.setPos(newTile.xCoord, (int) tower.getGridPosition().y);
				currentTile.isEmpty = true;
				newTile.isEmpty = false;
				newTile.objectIndex = map.sprites.indexOf(tower);
			}

			if (newTile.yCoord != currentTile.yCoord) {
				tower.setPos((int) tower.getGridPosition().x, newTile.yCoord);
				currentTile.isEmpty = true;
				newTile.isEmpty = false;
				newTile.objectIndex = map.sprites.indexOf(tower);
			}
		}
	}

}
