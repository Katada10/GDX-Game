package core;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author Katada
 *
 */
public class Main {
	public static final int WIDTH = 800, HEIGHT = 600;
	
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
		cfg.title = "Tower Defense Game";
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
	}
}
