package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import map.GameMap;
import sprites.Sprite;
import structs.Grid;
import ui.FontRender;

public class Renderer{
	
	SpriteRenderer spriterender;
	FontRender fontrender;
	GameMap gameMap;
	
	public void init()
	{
		fontrender = new FontRender();
		spriterender = new SpriteRenderer();
		gameMap = new GameMap();

		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		gameMap.update();
		for (Sprite sprite : GameMap.sprites) {
			spriterender.render(sprite);
		}
		
		fontrender.render();
	}
	
	
	public void cleanUp()
	{
		fontrender.dispose();
		spriterender.dispose();
		gameMap.dispose();
	}
}
