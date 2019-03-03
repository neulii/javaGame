package myJava.neulii.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * This is a map class. it takes fields and create a map
 * 
 * @see Field
 * @author neulii
 *
 */

public class Map implements GameObject {

	//private int[] mapString;
	
	private int width; // width in fields
	//private int height; // width in fields

	//private int fieldWidth;
	//private int fieldHeight;

	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private boolean moveDown;
	
	private GameWindow gw;
	
	private int scrollSpeed = 450;
	
	private ArrayList<Tile> mapTiles = new ArrayList<Tile>();

	/**
	 * 
	 * @param width       in Tiles
	 * @param height      in Tiles
	 * @param fieldWidth  in Pixels
	 * @param fieldHeight in Pixels
	 */
	public Map(int width, int height, int fieldWidth, int fieldHeight, int[] mapString) {
		this.width = width;
//		this.height = height;
//		this.fieldHeight = fieldHeight;
//		this.fieldWidth = fieldWidth;
//		
//		this.mapString = mapString;

		for (int i = 0; i < width * height; i++) {
		
			int x = (int) convertLineToDimension(i).getWidth() * fieldWidth;
			int y = (int) convertLineToDimension(i).getHeight() * fieldHeight;
			
			String imageString = null;
			
			int fieldTypeFromMap = mapString[i];
		
			//enum wert von mapstring
			FieldType type = FieldType.values()[fieldTypeFromMap];
			
			switch (type) {
			case GRASS:
				imageString = "/grassField.png";
				break;

			case COAL:
				imageString = "/coalField.png";
				break;
				
			case IRON_ORE:
				imageString = "/iron_oreField.png";
				break;
				
			default:
				break;
			}
			
			if(imageString!=null) {
				
				Tile tempTile = new Tile(x,y,fieldWidth,fieldHeight,type,ImageLoader.loadImage(imageString));
				
				tempTile.setHooveredBorderColor(Color.blue);
				tempTile.setHooveredBorderThickness(2);
				mapTiles.add(tempTile);	
			}
		}
	}	

	/**
	 * Rechnet eine linienkoordinate in 2d um Beginnt mit 0,1,2,3,4......
	 * 
	 * Map beginnt mit 0 z.B.: </br>
	 * 0,0 / 1,0 / 2,0 / 3,0 / 4,0 </br>
	 * 0,1 / 1,1 / 2,1 / 3,1 / 4,1 </br>
	 * 0,2 / 1,2 / 2,2 / 3,2 / 4,2 </br>
	 * 
	 * 
	 * @param line
	 * @return Dimension
	 */
	private Dimension convertLineToDimension(int line) {

		int x = 0;
		int y = 0;

		x = line % width;
		y = (int) (line / width);

		// System.out.println(x + " / " + y);
		return new Dimension(x, y);
	}

	/**
	 * 
	 * Rechnet eine eindimensionale Koordinate in 2D um Map beginnt bei 0.
	 * 
	 * z.B.: 0,1,2,3,.....
	 * 
	 * @param dim
	 * @return int
	 */
	private int convertDimensionToLine(Dimension dim) {

		int line = (int) ((dim.getHeight()) * width + dim.getWidth());

		return line;
	}

	public int convertDimensionToLine(int x, int y) {
		return convertDimensionToLine(new Dimension(x, y));
	}

	@Override
	public void render(Graphics g) {
		for (Tile tile : mapTiles) {
			tile.render(g);
		}
	}

	@Override
	public void update(long dT) {
		
		Tile firstTile = mapTiles.get(0);
		Tile lastTile = mapTiles.get(mapTiles.size()-1);
		
		long moveSize = scrollSpeed*dT/1000_000_000;
		
		int diffLeft = (int)(firstTile.getX() + moveSize);
		int diffUp =   (int)(firstTile.getY() + moveSize);
		int diffRight = (int)(lastTile.getX()+lastTile.getWidth() - moveSize);
		int diffDown =  (int)(lastTile.getY()+lastTile.getHeight()- moveSize);
		
		
		//moving map left
		if(moveLeft)
		{
			if(diffLeft>0) {
				moveMap((int) moveSize-diffLeft, 0);
			}
		
			if(diffLeft<=0) {
				moveMap((int)moveSize,0);
			}			
		}
		
		if(moveRight) {
			//System.out.println("point right: " + (lastTile.getX()+lastTile.getWidth()) + "    WindowWidth:  " + gw.getWidth() + "   diff:"+ diffRight);
			
			if(diffRight>gw.getWidth()) {
				moveMap(-(int) moveSize, 0);
			}
		
			if(diffRight<=gw.getWidth()) {
				moveMap(-(int)moveSize-diffRight+gw.getWidth(),0);
			}			
		}
		
		//Moving map up
		if(moveUp) {
			
			if(diffUp>0) {
				moveMap(0,(int) moveSize-diffUp );
			}
		
			if(diffUp<=0) {
				moveMap(0, (int)moveSize);
			}			
		}
		
		if(moveDown) {
			if(diffDown>gw.getHeight()) {
				moveMap(0,-(int) moveSize );
			}
		
			if(diffDown<=gw.getHeight()) {
				moveMap(0, -(int)moveSize-diffDown+gw.getHeight());
			}		
		}
		
		
		for (Tile tile : mapTiles) {
			tile.update(dT);
		}
	}
	
	public Tile getTileFromCoordinate(int x, int y) {
		
		Tile temp = null;
		
		for (Tile tile : mapTiles) {
			
			if(tile.isInside(new Point(x,y)))
				temp = tile;
		}
		
		return temp;
	}
	
	public int getIndex(Tile tile) {
		int index = mapTiles.indexOf(tile);
		
		return index;
	}
	
	public void changeField(final Tile oldField,final Tile newField) {
		int index = mapTiles.indexOf(oldField);
		mapTiles.set(index, newField);
	}
	
	private void moveMap(int x, int y) {
		for (Tile tile : mapTiles) {
			
			tile.moveTile(x, y);
		}
	}
	
	public void moveRight(boolean right, GameWindow gw) {
		moveRight = right;
		this.gw = gw;
	}
	
	public void moveLeft(boolean left, GameWindow gw) {
		moveLeft = left;
		this.gw = gw;
	}
	
	public void moveUp(boolean up, GameWindow gw) {
		moveUp = up;
		this.gw = gw;
	}
	
	public void moveDown(boolean down, GameWindow gw) {
		moveDown = down;
		this.gw = gw;
	}
	
	//TODO
	//returns the x/y cooridnate from maptile
	public Point getMapCoordinateFromTile(Tile tile) {
		
		Point coordinate = new Point(0,0);
		
		Dimension d = convertLineToDimension(getIndex(tile));
		
		coordinate.x = (int) d.getWidth();
		coordinate.y = (int) d.getHeight();
			
		return coordinate;
	}
}