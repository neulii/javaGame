package myJava.neulii.Game;

public class MaterialManager {
	
	private int money;
	private int coal;
	private int iron_ore;
	private int raw_Iron;
	
	private int coalWorth = 1;
	private int iron_oreWorth = 2;
	private int raw_ironWorth = 5;
	
	public MaterialManager() {
		this.coal = 0;
		this.iron_ore = 0;
		this.raw_Iron = 0;
		this.money = 1000;
	}
	
	public void addMaterials(int coal, int iron_ore) {
		this.coal = this.coal + coal;
		this.iron_ore = this.iron_ore + iron_ore;	
	}
	
	public void addRawIron(int raw_Iron) {
		this.raw_Iron = this.raw_Iron + raw_Iron;
	}
	
	public void subRawIron(int raw_Iron) {
		this.raw_Iron = this.raw_Iron- raw_Iron;
	}
	
	public void subCoal(int coal) {
		this.coal = this.coal-coal;
	}
	
	public void subIronOre(int ironOre) {
		this.iron_ore = this.iron_ore - ironOre;
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
	
	public int getRawIron() {
		return this.raw_Iron;
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

	
	public int getRawIronWorth() {
		return raw_ironWorth;
	
	}
	
	public int getCoalWorth() {
		return coalWorth;
	}
	
	public int getironOreWorth() {
		return iron_oreWorth;
	}
	
	
	
}
