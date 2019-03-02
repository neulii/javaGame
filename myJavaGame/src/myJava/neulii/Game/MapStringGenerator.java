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
		
		//Map mit Grass fuellen
		for(int i=0; i<stringLength;i++) {
			mapString[i] = 0;
			
		}
		
	}
	
	public void setCoal(int coal) {
		
	}
	
	public void setIronOre(int ironOre) {
		
		
	}
	
	public void setMapStructure(int coal, int ironOre) {
		setCoal(coal);
		setIronOre(ironOre);
	}
	
	public void printMapStringToConsole() {
		
		for(int i = 0; i<stringLength; i++) {
		
			if(i>0 && (i % (width) == 0)) {
				System.out.print("\n");
			}
			System.out.print(mapString[i]);
		}
	}
}
