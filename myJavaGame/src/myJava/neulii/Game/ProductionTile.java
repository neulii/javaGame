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
			
			//just add one when one is ready mined
				
			if(minedAmount>=1) {
				
				switch (getFieldType()) {
				case COAL_MINE:
					
					mm.addMaterials(1, 0);
					break;
					
				case IRON_ORE_MINE:
					mm.addMaterials(0, 1);
					break;
					
				default:
					break;
				}
				minedAmount = 0;
				subtractResource(1);
			}
			
		}		
	}
	
	public double getMinedAmount() {
		return minedAmount;
	}
}