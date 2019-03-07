package myJava.neulii.Game;

public class Production_Furnace extends ProductionTile {

	public Production_Furnace(Tile tile, FieldType fieldType, MaterialManager mm) {
		super(tile, fieldType, mm);
	}
	
	@Override
	public void update(long dT) {
		
		System.out.println("furnace processing");
	}

}
