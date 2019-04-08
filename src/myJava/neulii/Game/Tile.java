package myJava.neulii.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tile implements GameObject {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private double resources = 1000;

	private boolean isHoovered = false;

	private Color borderColor;
	private Color hooveredBorderColor;
	private int hooveredBorderThickness = 1;
	private int borderThickness = 1;
	
	private long lastTimeRender = 0;
	private boolean borderBlink = false;
	private long blinkTime;
	private Color blinkColor;
	private boolean blinkOn = false;
	//private Color tempBlink;
	
	private long timeCounter = 0;
	
	private BufferedImage image;
	private FieldType fieldType;
	
	public Tile(int x, int y, int width, int height, FieldType fieldType, BufferedImage image) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.image = image;
		this.fieldType = fieldType;
	}
	
	public Tile(Tile tile) {
		
		this.height = tile.getHeight();
		this.width = tile.getWidth();
		this.x = tile.getX();
		this.y = tile.getY();
		this.image = tile.getImage();
		this.fieldType = tile.getFieldType();
	}
	
	public Tile(int x, int y, int width, int height, BufferedImage image) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.image = image;
		this.fieldType = null;
	}
	
	
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public FieldType getFieldType() {
		return fieldType;
	}
	
	public boolean isInside(Point p) {
		
		boolean tempInside = false;
		
		if( 
				
				(p.getX() >= x) 		&& 
				(p.getX() <= (x+width))	&&
				(p.getY() >= y) 		&&
				(p.getY()<= y+height)		
		) {
			tempInside = true;
		}
		
		return tempInside;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
		
		Color tempColor = g.getColor();
		
		
		
		//when color is set then paint border
		if(borderColor!=null) {
			g.setColor(borderColor);
			
			//System.out.println(borderColor);
			for(int i = 0; i< borderThickness; i++) {
				g.drawRect(x+i, y+i, width-1-2*i, height-1-2*i);
			}
		}
		
		//when tile is hoovered and hoovered color is ist then paint border
		
		if(isHoovered && (hooveredBorderColor!=null)) {
			//tempColor = g.getColor();
			g.setColor(hooveredBorderColor);
			
			for(int i = 0; i< hooveredBorderThickness; i++) {
				
				g.drawRect(x+i, y+i, width-1-2*i, height-1-2*i);
			}
		}
		
		//blinking rendering

		if(borderBlink) {
			long deltaT = System.currentTimeMillis()-lastTimeRender;
			lastTimeRender = System.currentTimeMillis();
			
			timeCounter = timeCounter + deltaT;
						
			if(timeCounter>blinkTime) {
				timeCounter=0;
				
				if(blinkOn) {
					//tempBlink = borderColor;
					
					//System.out.println("blink  " + tempBlink);
					
					setBorderColor(blinkColor);
					setBorderThickness(3);
					
					blinkOn = false;
				}
				else {
					blinkOn = true;
					//borderColor = tempBlink;
					borderThickness =0;
				}
			}
		}
		
		g.setColor(tempColor);
	}
	
	public void setHoovered(boolean hoovered) {
		isHoovered = hoovered;
	}
	
	public void setBorderColor(Color color) {
		borderColor = color;
	}
	
	public void setHooveredBorderColor(Color color) {
		hooveredBorderColor = color;
	}

	@Override
	public void update(long dT) {
		
	}
	
	public void setHooveredBorderThickness(int thickness) {
		this.hooveredBorderThickness = thickness;
	}
	
	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
		
	}
	
	public double getResources() {
		return resources;
	}
	
	public void subtractResource(double resource) {
		
		if(this.resources > 0) {
			this.resources = this.resources-resource;			
		}
	}
	
	public int getHooverBorderThickness() {
		return hooveredBorderThickness;
	}
	
	public void moveTile(int x, int y) {
		this.x = this.x+x;
		this.y = this.y+y;
	}
	
	public void setBorderBlink(boolean blinkOn, long time, Color color) {
		borderBlink = blinkOn;
		blinkTime = time;
		borderColor = color;
		
		
	}
}