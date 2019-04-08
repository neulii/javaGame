package myJava.neulii.Game;

//FieldType enum
public enum FieldType {
	GRASS(0),
	COAL(1),
	IRON_ORE(2),
	
	COAL_MINE(3),
	IRON_ORE_MINE(4),
	FURNACE(5);
	
	int value;
	
	FieldType(int value) {
		this.value = value;
	}
	
	public int getInt() {
		return value;
	}
	

}