package myJava.neulii.Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.event.MouseInputListener;

public class InputListener extends MouseAdapter implements MouseInputListener, KeyListener{

	
	private Tile activeTile = null;
	private Tile newTile = null;
	private BufferedImage changeingTile = null;
	private GameWindow gw;
	private GUI gui;
	
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

//				tile.setHooveredBorderColor(Color.red);
//				tile.setHoovered(true);
				
				//when buildable on field
				if(gui.getElementMarked()!=null) {
					System.out.println(gui.getElementMarked().getFieldType() + "      "  + gw.getMap().getTileFromCoordinate(e.getX(), e.getY()).getFieldType());
					if(canBuildOnActualField) {
						
						//tile = new Tile(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), gui.getElementMarked().getFieldType(), gui.getElementMarked().getImage());
						
						changeingTile = gui.getElementMarked().getImage();
						
						tile.setImage(gui.getElementMarked().getImage());				
						tile.setFieldType(gui.getElementMarked().getFieldType());
						
						gui.clearMarkedTile();
						changeingTile = null;
						tile.setHooveredBorderColor(Color.blue);
					}
				}
			}
		}
		
		//Right Mousebutton
		
		if(e.getButton()==MouseEvent.BUTTON3) {
			if(changeingTile!=null)
				tile.setImage(changeingTile);
			gui.clearMarkedTile();
			canBuildOnActualField= false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gw.setMousePos(e.getPoint());
		
		
		//mouse is over field  and not in menu
		if(!gw.getMouseOverMenu()) {
		
			activeTile = newTile;
			newTile = gw.getMap().getTileFromCoordinate(e.getX(), e.getY());
			
			if(newTile==activeTile) {
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
					
					if(gui.getElementMarked().getBuildOn(newTile.getFieldType())) {
						canBuildOnActualField = true;
						newTile.setHooveredBorderColor(Color.green);
							
					}

					else {
						canBuildOnActualField = false;
						newTile.setHooveredBorderColor(Color.red);
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
			
			activeTile.setHoovered(false);
			newTile.setHoovered(false);
		}
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gui.switchDebugView();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}