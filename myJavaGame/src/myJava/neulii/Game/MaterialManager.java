package myJava.neulii.Game;

public class MaterialManager {
	
	double coal;
	double iron_ore;
	
	public MaterialManager() {
		this.coal = 0;
		this.iron_ore = 0;
	}
	
	public void addMaterials(double coal, double iron_ore) {
		this.coal = this.coal + coal;
		this.iron_ore = this.iron_ore + iron_ore;
		
	}
	
	double getIronOre() {
		return this.iron_ore;
	}
	
	public double getcoal() {
		return this.coal;
	}

}
