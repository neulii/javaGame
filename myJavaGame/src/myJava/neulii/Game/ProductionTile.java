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
	public void update(long dT) {
		//TODO funktion fuer production tile udpate
		subtractResource(1);
		System.out.println(getFieldType()+ "   update   " + getResources());
		
	}
}