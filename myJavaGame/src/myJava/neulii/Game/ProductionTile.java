package myJava.neulii.Game;

public class ProductionTile extends Tile {

	private FieldType buildOn;
	
	public ProductionTile(Tile tile, FieldType buildOn) {
		super(tile);
		
		this.buildOn = buildOn;
		
		
	}
	
	
	public boolean getBuildOn(FieldType fieldType) {
		boolean buildAble = false;
		
		if(this.buildOn==fieldType) {
			buildAble = true;
		}
		
		return buildAble;
	}
	
	@Override
	public void update() {
		
		System.out.println("test");
		
		
		
	}

}
