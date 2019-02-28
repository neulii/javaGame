package myJava.neulii.Game;

public class ProductionTile extends Tile {

	private FieldType buildOn;
	private double minedAmount = 0;
	private double miningPerSecond = 1;	
	
	private int costOfTile = 100;
	
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
	
	public int getCostOfTile() {
		return costOfTile;
	}
}