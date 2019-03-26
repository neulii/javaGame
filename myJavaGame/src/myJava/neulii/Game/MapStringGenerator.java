package myJava.neulii.Game;

import javax.swing.JFrame;

import myJava.neulii.Lib.Utils;

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
	
	public int[] getMapString() {
		return mapString;
	}
	
	public void generateTilesOnMap(int coal, FieldType fieldType) {
		
		int numberOfCoalFields = (int)(stringLength*coal/100);
		
		//System.out.println(numberOfCoalFields);
		int [] coalFields = Utils.getArrayWithRandomInts(mapString.length, numberOfCoalFields);
				
		for(int i = 0; i<coalFields.length;i++) {
			mapString[coalFields[i]] = fieldType.value;	
		}
	}
	
	public void setMapStructure(int coal, int ironOre) {
		generateTilesOnMap(coal, FieldType.COAL);
		generateTilesOnMap(ironOre, FieldType.IRON_ORE);
		
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
