package myJava.neulii.Game;

public class Production_Furnace extends ProductionTile {

	public Production_Furnace(Tile tile, FieldType fieldType, MaterialManager mm) {
		super(tile, fieldType, mm);
		setMiningPerSecond(0.5);
	}
	
	@Override
	public void update(long dT) {
		
		//System.out.println("furnace processing");
		
		if(mm.getIronOre()>0 && mm.getcoal()>0) {
			double minedThisUpdate = dT * miningPerSecond /1_000_000_000;
			
			minedAmount = minedAmount + minedThisUpdate;
			if(minedAmount>=1) {
				
				minedAmount = 0;
				mm.subIronOre(1);
				mm.subCoal(1);
				
				mm.addRawIron(1);
			}
		}
	}
}
