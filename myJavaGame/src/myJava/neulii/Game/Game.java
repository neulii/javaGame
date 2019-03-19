package myJava.neulii.Game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
	
	private Map gameMap;
	private MapStringGenerator mapGenerator;
	
	private int mapWidth = 50;
	private int mapHeight = 50;
	
	private static int windowWidth = 800;
	private static int windowHeight = 600;
	
	private static Game game;
	private static GUI gui;
	
	private boolean isMouseOverMenu = false;
	private MaterialManager mm;
	private Point mousePosition;
	
	
	public Game()
	{
		super("SteelManager");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		mousePosition = new Point(0,0);
		
		//System.setProperty("sun.java2d.opengl","True");
		mm = new MaterialManager();
		
		mapGenerator =new MapStringGenerator(mapWidth,mapHeight);
		mapGenerator.setMapStructure(2, 2);
		gameMap = new Map(mapWidth,mapHeight,50,50,mapGenerator.getMapString(),this);
		gui = new GUI(this, mm);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		gameMap.update(i);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		gameMap.render(g);
		gui.render(g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			game = new Game();
			appgc = new AppGameContainer(game);
			appgc.setDisplayMode(windowWidth, windowHeight, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public Dimension getGameWindowDimension() {
		return new Dimension(windowWidth,windowHeight);
	}
	
	public Map getMap() {
		return gameMap;
	}
	
	public boolean getMouseOverMenu() {
		return isMouseOverMenu;
	}
	
	public MaterialManager getMaterialManager() {
		return mm;
	}
	
	public void setMousePos(Point p) {
		mousePosition = p;
	}
	
	public Point getMousePos() {
		return mousePosition;
	}
	
	public void setMouseOverMenu(boolean overMenu) {
		isMouseOverMenu = overMenu;
	}
}