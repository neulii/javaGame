package myJava.neulii.Game;

public class MaterialManager {
	
	private int money;
	private int coal;
	private int iron_ore;
	
	public MaterialManager() {
		this.coal = 0;
		this.iron_ore = 0;
		this.money = 200;
	}
	
	public void addMaterials(int coal, int iron_ore) {
		this.coal = this.coal + coal;
		this.iron_ore = this.iron_ore + iron_ore;
		
	}
	
	public int getIronOre() {
		return this.iron_ore;
	}
	
	public int getcoal() {
		return this.coal;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void addMoney(int money) {
		this.money = this.money + money;
	}
	
	public void subMoney(int money) {
		if(this.money>= money) {
			this.money = this.money - money;
		}
		else 
			return;
	}

}
