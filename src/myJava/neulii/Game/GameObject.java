package myJava.neulii.Game;

import java.awt.Graphics;

public interface GameObject {

	//Method to Render graphics
	void render(Graphics g);
	
	//Method for Updating game logic
	void update(long dT);
}
