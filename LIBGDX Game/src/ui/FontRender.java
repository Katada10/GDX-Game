package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import structs.Grid;

public class FontRender {

	private static Stage stage;
	
	public FontRender()
	{
		stage = new Stage();
	}
	
	public void render()
	{
		stage.draw();
		stage.act();
	}
	
	public static Label addFont(String text, float x, float y)
	{
		BitmapFont font = new BitmapFont();
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		Label label = new Label(text, style);
		label.setFontScale(2);
		label.setPosition(x, y);
		stage.addActor(label);
		return label;
	}
	
	public void dispose()
	{
		stage.dispose();
	}
}
