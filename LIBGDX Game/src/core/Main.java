package core;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
		cfg.title = "Amazing Game Engine";
		cfg.width = WIDTH;
		cfg.height = HEIGHT;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
	}

}
