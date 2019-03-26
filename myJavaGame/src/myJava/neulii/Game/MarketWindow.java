package myJava.neulii.Game;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class MarketWindow {
	
	private JDialog window;
	private MaterialManager mm;
	private JFrame parentWindow;
	
	public MarketWindow(MaterialManager mm, JFrame parentWindow) {
		
		this.mm = mm;
		this.parentWindow = parentWindow;
		
		
		window = new JDialog(parentWindow, "Market", true);
		
		
		window.setSize(300,300);
		window.setLocationRelativeTo(parentWindow);
		window.setResizable(false);
		
		
		
		
		
	}

	public void show() {
		window.setVisible(true);
		
		
	}
	
	

}
