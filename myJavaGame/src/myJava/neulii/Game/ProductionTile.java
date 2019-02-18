package myJava.neulii.Game;

public class ProductionTile extends Tile {

	private FieldType buildOn;
	private double minedAmount = 0;
	private double miningPerSecond = 0.2;	
	private MaterialManager mm;
	
	public ProductionTile(Tile tile, FieldType fieldType, MaterialManager mm) {
		super(tile);
		
		this.mm = mm;
		
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
		
		
		if(getResources()>=0) {
			double minedThisUpdate = dT * miningPerSecond /1_000_000_000;
			
			minedAmount = minedAmount + minedThisUpdate;
				
			switch (getFieldType()) {
				case COAL_MINE:
					
					mm.addMaterials(minedThisUpdate, 0);
					break;
		
				case IRON_ORE_MINE:
					mm.addMaterials(0, minedThisUpdate);
					break;
					
				default:
					break;
				}
			
			subtractResource(minedThisUpdate);
		}
		
		//System.out.println(dT);
		
	}
	
	public double getMinedAmount() {
		return minedAmount;
	}
}