package myJava.neulii.Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

public class InputListener extends MouseAdapter implements MouseInputListener, KeyListener{

	private Tile activeTile = null;
	private Tile newTile = null;
	private BufferedImage changeingTile = null;
	private GameWindow gw;
	private GUI gui;
	private MouseEvent me;
	
	private boolean canBuildOnActualField = false; 
		
	public InputListener(GameWindow gw, GUI gui) {
		this.gw = gw;
		this.gui = gui;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Tile tile = gw.getMap().getTileFromCoordinate(e.getX(), e.getY());		
	
		//left Mousebutton
		if(e.getButton()==MouseEvent.BUTTON1) {
			
			//when mouse is over menu
			if(gw.getMouseOverMenu()) {
				if(gui.getElementMarked()==gui.getHooveredElement())
					gui.clearMarkedTile();
				else 
					gui.setHooveredTileMarked();
			}
			
			//when mouse is not in menu
			if(!gw.getMouseOverMenu()) {

				//when buildable on field
				if(gui.getElementMarked()!=null) {
					
					//when tile can build on actual field and money is enough
					if(canBuildOnActualField && (gui.getElementMarked().getCostOfTile()<=gw.getMaterialManager().getMoney())) {
						
						//tile = new Tile(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), gui.getElementMarked().getFieldType(), gui.getElementMarked().getImage());
						
						changeingTile = gui.getElementMarked().getImage();
						
						Tile newTile;
						
						//new Type of Tile is furnace
						if( gui.getElementMarked() instanceof Production_Furnace) {
							newTile = new Production_Furnace(tile,gui.getElementMarked().getFieldType(),gw.getMaterialManager());
						}
						else
							newTile = new ProductionTile(tile,gui.getElementMarked().getFieldType(),gw.getMaterialManager());
						
						//newTile.setHooveredBorderThickness(2);
						
						newTile.setHooveredBorderColor(Color.blue);
						newTile.setHoovered(true);
						
						gw.getMap().changeField(tile, newTile);
						
//						tile.setImage(gui.getElementMarked().getImage());				
//						tile.setFieldType(gui.getElementMarked().getFieldType());
//						
						gw.getMaterialManager().subMoney(((ProductionTile)newTile).getCostOfTile());
						
						gui.clearMarkedTile();
						changeingTile = null;
						tile.setHooveredBorderColor(Color.blue);
					}
				}
			}
		}
		
		//Right Mousebutton
		
		if(e.getButton()==MouseEvent.BUTTON3) {
			if(changeingTile!=null) {
				gui.clearMarkedTile();
				tile.setHooveredBorderColor(Color.blue);				
				tile.setImage(changeingTile);
				gui.showMoneyWarning(false);
				
			}
			canBuildOnActualField= false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		me = e;
		gw.setMousePos(e.getPoint());
		
		
		//mouse is over field  and not in menu
		if(!gw.getMouseOverMenu()) {
		
			activeTile = newTile;
			newTile = gw.getMap().getTileFromCoordinate(e.getX(), e.getY());
			
			if(newTile==activeTile) {
				if(newTile!=null)
					newTile.setHoovered(true);
			}
			
			//when new field becomes active
			if(newTile!=activeTile) {
				
				//when a icon in menu is marked
				if(gui.getElementMarked()!=null) {
					//System.out.println(gui.getElementMarked().getFieldType() + "     "+ newTile.getFieldType());
					
					//reset oldpicture
					if(changeingTile!=null) {
					
						activeTile.setImage(changeingTile);
						
					}
					
					//save old picture
					changeingTile= newTile.getImage();
					
					//setpicture from selected 
					newTile.setImage(gui.getElementMarked().getImage());
					
					
					//when tile can build on actual field and money is enough
					if(gui.getElementMarked().getBuildOn(newTile.getFieldType()) ) {
						
						//if enough money
						if(gui.getElementMarked().getCostOfTile()<=gw.getMaterialManager().getMoney()) {
							
							canBuildOnActualField = true;
							newTile.setHooveredBorderColor(Color.green);
						}
						
						else {
							
							newTile.setHooveredBorderColor(Color.ORANGE);	
							gui.showMoneyWarning(true);
						}
					}

					else {
						canBuildOnActualField = false;
						newTile.setHooveredBorderColor(Color.red);
						gui.showMoneyWarning(false);
					}

					newTile.setHoovered(true);
				}
				
				if(activeTile!=null) {
					activeTile.setHoovered(false);
					activeTile.setHooveredBorderColor(Color.blue);
					
				}
			}
		}
		//mouse is leaving field
		else {
			if(activeTile!=null)
				activeTile.setHoovered(false);
			newTile.setHoovered(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		mouseMoved(me);

		//when pressed F12 activate debugview
		
		if(e.getKeyCode()==KeyEvent.VK_F12)
			gui.switchDebugView();

		String cheatInput = "";
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			cheatInput = JOptionPane.showInputDialog(gw, "Cheat-Code eingeben:", "Cheat Konsole", JOptionPane.INFORMATION_MESSAGE);
			CheatAction.doCheat(gw, cheatInput);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {
			gw.getMap().moveRight(true, gw);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			gw.getMap().moveLeft(true,gw);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
			gw.getMap().moveUp(true,gw);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			gw.getMap().moveDown(true,gw);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D) {			
			gw.getMap().moveRight(false,gw);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A) {
			gw.getMap().moveLeft(false,gw);
		}
	
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W) {
			gw.getMap().moveUp(false,gw);
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S) {
			gw.getMap().moveDown(false,gw);
		}
	}
}