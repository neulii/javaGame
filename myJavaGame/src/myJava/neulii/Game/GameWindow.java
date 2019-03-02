package myJava.neulii.Game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameWindow extends Canvas implements GameObject{
	
	private static final long serialVersionUID = 1L;
	
	private final int MAPWIDTH = 25;
	private final int MAPHEIGHT = 16;
	
	private final int TILE_WIDTH = 50;
	private final int TILE_HEIGHT = 50;

	private int windowHeight;
	private int windowWidth;
	
	private boolean gameIsRunning;
	
	private boolean isMouseOverMenu = false;
	
	private int actualFrames = 0;
	
	private Dimension windowSize;
	private JFrame gameWindow;
	private BufferStrategy bs;
	private Graphics graphics;
	
	private MaterialManager mm;
	
	private Point mousePosition;
	
	private InputListener inputListener;
	
	private int[] mapString = {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
							   0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,0
	};
	
	private Map gameMap;
	private GUI gui;

	public GameWindow(int width, int height) {
		windowWidth = 15 * TILE_WIDTH;
		windowHeight =10 * TILE_HEIGHT;

		windowSize = new Dimension(windowWidth, windowHeight);
		
		initializeObjects();
		initializeWindow();
		
		gameWindow.setVisible(true);
		
		gameIsRunning = true;
	
		//Game Loop
		final int FPS = 60;
		
		long startTime = System.nanoTime();
		long deltaTime = 0;
		long frameCounterTime;
		double timePerFrame = 1000000000.0/FPS;
		
		int frames = 0;
		frameCounterTime = System.currentTimeMillis();
		
		while(gameIsRunning) {
			deltaTime = System.nanoTime()-startTime;
						
			if(deltaTime>=timePerFrame) {
				
				//update Methode
				update(deltaTime);
				
				frames++;
				deltaTime = 0;
				startTime = System.nanoTime();	
			}
		
			//Rendermethode
			render(graphics);
			
			//Frame Counter
			if(System.currentTimeMillis()-frameCounterTime>=1000) {
//				System.out.println(frames);
				actualFrames = frames;
				frames = 0;
				frameCounterTime = System.currentTimeMillis();
			}
		}
	}

	// Render Method
	public void render(Graphics g) {
		
		bs = getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();	       
		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		//==================    Add Drawing Stuff here    =====================
		
	
		gameMap.render(g);
		gui.render(g);
	
	
		//==================     End of drawing zone      ===================== 
		
		g.dispose();
		bs.show();
	}
	
	//Update Method
	public void update(long dT) {
		gui.update(dT);
		gameMap.update(dT);
	}
	
	//initialize Window
	public void initializeWindow() {
		gameWindow = new JFrame("Game");
		//gameWindow.setSize(windowWidth, windowHeight);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
		this.setFocusable(true);

		
		gameWindow.add(this);		
		gameWindow.pack();
		inputListener = new InputListener(this,gui);
		this.addMouseMotionListener(inputListener);
		this.addMouseListener(inputListener);
		this.addKeyListener(inputListener);
		
		gameWindow.setLocationRelativeTo(null);
	}
	
	public void initializeObjects() {
		mm = new MaterialManager();
		
		MapStringGenerator mapStringGen = new MapStringGenerator(20, 20);
		
		mapStringGen.printMapStringToConsole();
		
		gameMap = new Map(MAPWIDTH,MAPHEIGHT,TILE_WIDTH,TILE_HEIGHT,mapString);
		gui = new GUI(this, mm);
		mousePosition = new Point(0,0);
	}
	
	public int getActualFrames() {
		return actualFrames;
	}
	
	public Map getMap() {
		return gameMap;
	}
	
	public Point getMousePos() {
		return mousePosition;
	}
	
	public void setMousePos(Point p) {
		mousePosition = p;
	}
	
	public void setMouseOverMenu(boolean overMenu) {
		isMouseOverMenu = overMenu;
	}
	
	public boolean getMouseOverMenu() {
		return isMouseOverMenu;
	}
	
	public MaterialManager getMaterialManager() {
		return mm;
	}
	
	public int getHeight() {
		return this.windowHeight;
	}
	
}
