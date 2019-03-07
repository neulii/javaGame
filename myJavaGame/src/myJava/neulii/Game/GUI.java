package myJava.neulii.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GUI implements GameObject{

	private boolean debugViewActive = true;
	
	private boolean moneyWarning = false;
	
	private GameWindow gw;	
	private MaterialManager mm;
	
	private int menuTileWidth = 50;
	private int menuTileHeight = 50;
	
	private ProductionTile elementIsMarked = null;
	private ProductionTile hooveredTile = null;
	
	private ArrayList<ProductionTile> menuTiles = new ArrayList<ProductionTile>();
	private ArrayList<Tile> symbolTiles = new ArrayList<Tile>();
	
	int beginningInfoSymbols = 300;
	
	public GUI(GameWindow gw, MaterialManager mm) {
		this.gw = gw;
		this.mm = mm;
		//testcomment
		ProductionTile coalMine = new ProductionTile(new Tile(10,70,menuTileWidth, menuTileHeight, FieldType.COAL,ImageLoader.loadImage("/coalMineField.png")), FieldType.COAL_MINE, mm);
		ProductionTile ironOreMine = new ProductionTile(new Tile(10,70+50+10,menuTileWidth, menuTileHeight, FieldType.IRON_ORE,ImageLoader.loadImage("/ironOreMineField.png")), FieldType.IRON_ORE_MINE, mm);
		Production_Furnace furnace = new Production_Furnace(new Tile(10,70+50+50+10+10,menuTileWidth, menuTileHeight, FieldType.GRASS,ImageLoader.loadImage("/furnaceField.png")), FieldType.FURNACE,mm);
		
		menuTiles.add(coalMine);
		menuTiles.add(ironOreMine);
		menuTiles.add(furnace);
		
		for (ProductionTile tiles : menuTiles) {
			tiles.setBorderColor(Color.black);
			tiles.setHooveredBorderColor(Color.red);
			tiles.setHooveredBorderThickness(5);
			tiles.setBorderThickness(2);
		}

		Tile coalSymbol;
		Tile ironOreSymbol;
		Tile moneySymbol;
		Tile rawIronSymbol;
		
		coalSymbol = new Tile(20,beginningInfoSymbols+80,30,30,ImageLoader.loadImage("/coalSymbol.png"));
		ironOreSymbol = new Tile(20,beginningInfoSymbols+40,30,30,ImageLoader.loadImage("/ironOreSymbol.png"));
		moneySymbol = new Tile(20,beginningInfoSymbols,30,30,ImageLoader.loadImage("/moneySymbol.png"));
		rawIronSymbol = new Tile(20,beginningInfoSymbols+120,30,30,ImageLoader.loadImage("/rawIron.png"));
		
		symbolTiles.add(coalSymbol);
		symbolTiles.add(ironOreSymbol);
		symbolTiles.add(moneySymbol);
		symbolTiles.add(rawIronSymbol);
	}
	
	@Override
	public void render(Graphics g) {
		
		
		if(debugViewActive) {
			//show FPS

			Tile tempField = gw.getMap().getTileFromCoordinate(gw.getMousePos().x,gw.getMousePos().y);
		
			g.setFont(new Font("default",Font.BOLD,20));
			g.drawString("FPS: " + Integer.toString(gw.getActualFrames()),0,20);
		
			//showField information
		
			String fieldType = null;
			String showResource = null;
			String showCoordinate = null;
			
			if(tempField!=null) {
				
				fieldType = tempField.getFieldType().toString(); 
				showResource = Double.toString(tempField.getResources());
				showCoordinate = gw.getMap().getMapCoordinateFromTile(tempField).x + " / " + gw.getMap().getMapCoordinateFromTile(tempField).y;
			}
			else {
				showResource = "0";
				fieldType = "none";
				showCoordinate = "none";
			}
					
			String infoString1 = "FieldType:  " + fieldType +  " ";
			String infoString2 = "Resource:   " + showResource + " " ;
			String infoString3 = "Coordinate: " + showCoordinate + " " ;
			
			g.setColor(Color.black);
			g.setFont(new Font("default",Font.BOLD,12));
			g.drawString(infoString1 ,gw.getWidth()-200,20);
			g.drawString(infoString2 ,gw.getWidth()-200,40);
			g.drawString(infoString3, gw.getWidth()-200, 60);
		}
		
		//render menu
		for (Tile tile : menuTiles) {
			tile.render(g);
		}
		
		//render symbols
		
		for(Tile tile : symbolTiles) {
			tile.render(g);
		}
		
		//Coal Info
		g.setColor(Color.black);
		g.setFont(new Font("default",Font.BOLD,20));
		g.drawString(Integer.toString((int)(mm.getcoal())),60,beginningInfoSymbols+100);
	
		//iron_ore info
		g.drawString(Integer.toString((int)(mm.getIronOre())),60,beginningInfoSymbols+60);
		
		//money_info
		g.drawString(Integer.toString((int)(mm.getMoney())),60,beginningInfoSymbols+20);
		
		//raw_iron
		g.drawString(Integer.toString((int)(mm.getRawIron())),60,beginningInfoSymbols+140);
		
		//show warning when money is enough
		if(moneyWarning) {
			int x = gw.getMousePos().x;
			int y = gw.getMousePos().y+40;
			
			//when mouse is in the bottom of gamewindow
			if(y+30>gw.getHeight()) {
				y=gw.getHeight()-30;
			}
			else {
				y = gw.getMousePos().y+40;
			}
			
			if(gw.getMousePos()!=null) {
				g.drawString("Check Money!!",x-60, y);			
				
			}
		}
	
	}

	@Override
	public void update(long dT) {
		boolean allOver = false;
		boolean tempIsOverMenu = false;
		
		for (ProductionTile tile : menuTiles) {
			if(tile.isInside(gw.getMousePos())) {
				
				tile.setHoovered(true);
				tempIsOverMenu = true;
				hooveredTile = tile;	
			}
			else {
				tempIsOverMenu = false;
				tile.setHoovered(false);
			}
			
			allOver = tempIsOverMenu || allOver;
			
			//sobald maus nur auf einem feld des menus ist 
			if(allOver) {
				gw.setMouseOverMenu(true);
			}
			else {
				gw.setMouseOverMenu(false);
				hooveredTile = null;
			}
		}
	}
	
	public void showMoneyWarning(boolean warning) {
		moneyWarning = warning;	
		
	}
	
	public void setHooveredTileMarked() {
		
		if(elementIsMarked!=null) {
			elementIsMarked.setBorderColor(Color.black);
			elementIsMarked.setBorderThickness(2);
		}
		elementIsMarked = hooveredTile;
		elementIsMarked.setBorderColor(Color.red);
		elementIsMarked.setBorderThickness(5);
	}
	
	public void clearMarkedTile() {
		
		if(elementIsMarked!=null) {
			elementIsMarked.setBorderColor(Color.black);
			elementIsMarked.setBorderThickness(2);
			
			elementIsMarked = null;
		}
	}
	
	public ProductionTile getElementMarked() {
		return elementIsMarked;
	}
	
	public ProductionTile getHooveredElement() {
		return hooveredTile;
	}
	
	public void switchDebugView() {
		if(debugViewActive) {
			debugViewActive = false;
		}
		else
			debugViewActive = true;
	}
}
