package myJava.neulii.Game;

public class MapStringGenerator {
	
	private int width;
	private int height;
	
	
	//in from mapTiles percentage
	private int ironOre = 0;
	private int coal = 0;
	
	private int stringLength;
	
	private int[] mapString;
	
	//leere map erstellen
	public MapStringGenerator(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		this.stringLength = width * height;
		
		mapString = new int[stringLength];
		
		
		
		
		
	}
	
	public void setCoal(int coal) {
		
	}
	
	public void setIronOre(int ironOre) {
		
		
	}
	
	public void setMapStructure(int coal, int ironOre) {
		setCoal(coal);
		setIronOre(ironOre);
	}
	
	
	
	

}
