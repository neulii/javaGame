//package myJava.neulii.Game;
//
//public class CheatAction {
//	
//	public static void doCheat(GameWindow gw, String cheatCode) {
//	
//		MaterialManager mm = gw.getMaterialManager();
//		int value = 0;
//		String arguments[];
//		
//		arguments = cheatCode.split(",");
//		
//		if(arguments.length>0) {
//			try {
//				value = (Integer.parseInt(arguments[1]));
//			} 
//			catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//				System.out.println("Fehler bei der Cheatauswertung oder Falsche eingabe!!");
//			}
//			
//			switch (arguments[0]){
//			
//			case "giveCoal":
//				
//				mm.addMaterials(value, 0);
//				
//				break;
//			
//			case "giveMoney":
//				mm.addMoney(value);
//				break;
//		
//			case "giveIronOre":
//				mm.addMaterials(0, value);
//				break;
//				
//			default:
//				System.out.println("Cheat nicht erkannt!");
//				break;
//			}
//		}
//	}	
//}
