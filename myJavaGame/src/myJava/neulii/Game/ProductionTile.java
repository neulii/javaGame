package myJava.neulii.Game;

public class ProductionTile extends Tile {

	private FieldType buildOn;
	
	public ProductionTile(Tile tile, FieldType fieldType) {
		super(tile);
		
		setHooveredBorderThickness(tile.getHooverBorderThickness());
		buildOn = tile.getFieldType();
		this.setFieldType(fieldType);
		
		
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
