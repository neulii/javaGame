package myJava.neulii.Game;

import org.newdawn.slick.Graphics;

public interface GameObject {

	//Method to Render graphics
	public void render(Graphics g);
	
	//Method for Updating game logic
	public void update(long dT);
}
