package myJava.neulii.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GUI implements GameObject{

	private boolean debugViewActive = true;
	
	private GameWindow gw;	
	private int menuTileWidth = 50;
	private int menuTileHeight = 50;
	
	private ProductionTile elementIsMarked = null;
	private ProductionTile hooveredTile = null;
	
	private ArrayList<ProductionTile> menuTiles = new ArrayList<ProductionTile>();
	
	public GUI(GameWindow gw) {
		this.gw = gw;
		
		ProductionTile coalMine = new ProductionTile(new Tile(10,70,menuTileWidth, menuTileHeight, FieldType.COAL,ImageLoader.loadImage("/coalMineField.png")), FieldType.COAL_MINE);
		ProductionTile ironOreMine = new ProductionTile(new Tile(10,70+50+10,menuTileWidth, menuTileHeight, FieldType.IRON_ORE,ImageLoader.loadImage("/ironOreMineField.png")), FieldType.IRON_ORE_MINE);
		
		menuTiles.add(coalMine);
		menuTiles.add(ironOreMine);

		for (ProductionTile tiles : menuTiles) {
			tiles.setBorderColor(Color.black);
			tiles.setHooveredBorderColor(Color.red);
			tiles.setHooveredBorderThickness(5);
			tiles.setBorderThickness(2);
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		
		if(debugViewActive) {
			//show FPS

			Tile tempField = gw.getMap().getTileFromCoordinate(gw.getMousePos().x,gw.getMousePos().y);
			
			g.setFont(new Font("default",Font.BOLD,20));
			g.drawString("FPS: " + Integer.toString(gw.getActualFrames()),0,20);
		
		
			//showField information
		
			String fieldType = tempField.getFieldType().toString(); 
					
	
			String infoString1 = "FieldType: " + fieldType +  " ";
			String infoString2 = "Resource:  " + tempField.getResources() + " " ;
			
			
			g.setColor(Color.black);
			g.setFont(new Font("default",Font.BOLD,12));
			g.drawString(infoString1 ,gw.getWidth()-200,20);
			g.drawString(infoString2 ,gw.getWidth()-200,40);
		
		}

		//render menu
		for (Tile tile : menuTiles) {
			tile.render(g);
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
