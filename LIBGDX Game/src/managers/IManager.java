package managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import core.Main;
import sprites.Sprite;
import sprites.Tower;

public abstract class IManager<T>{
	public List<T> list;
	
	public IManager()
	{
		list = new ArrayList<>();
	}
	
	public abstract void update();
}