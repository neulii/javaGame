package myJava.neulii.Game;

public class ProductionTile extends Tile {

	private FieldType buildOn;
	private double minedAmount = 0;
	private double miningPerSecond = 3;	
	
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
		//subtractResource(1);
		//System.out.println(getFieldType()+ "   update   " + getResources());
		
		
		
		double minedThisUpdate = dT * miningPerSecond /1_000_000_000;
		System.out.println(minedThisUpdate);
		
		minedAmount = minedAmount + minedThisUpdate;
		
		subtractResource(minedThisUpdate);
		
		
		
		
		
		
		
		
		
		
	
		//System.out.println(dT);
		
	}
	
	public double getMinedAmount() {
		return minedAmount;
	}
}