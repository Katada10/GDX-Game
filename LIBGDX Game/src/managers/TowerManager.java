package managers;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import map.GameMap;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Tile;

public class TowerManager extends IManager<Tower> {
	boolean towerSelected = false;
	Tower toDrag = null;

	public TowerManager() {
		super();
	}

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (Input.dragging && !towerSelected) {
			if (t.xCoord == GameMap.sprites.get(1).getGridX() && t.yCoord == GameMap.sprites.get(1).getGridY()) {
				toDrag = (Tower) GameMap.addObject(new Tower(4, 0, Sprite.TOWER));
				list.add(toDrag);
				towerSelected = true;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(toDrag);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}
	}

	public void drag(Tower tower) {
			Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
			Tile currentTile = Grid.tiles[(int) toDrag.getGridY()][(int) toDrag.getGridX()];

			if (newTile.xCoord != currentTile.xCoord) {
				toDrag.setPos(newTile.xCoord, (int) toDrag.getGridY());
			}
			if (newTile.yCoord != currentTile.yCoord) {
				toDrag.setPos((int) toDrag.getGridX(), newTile.yCoord);
			}
	}
}
