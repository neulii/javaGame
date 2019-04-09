package myJava.neulii.Game;

import java.awt.*;
import java.util.Vector;

public class GameMenu implements GameObject{

    private GameWindow gw;
    private int buttonWidth = 180;
    private int buttonHeight = 60;

    MenuButton startNewGame;





//    private Rectangle buttonEnd;
//    private Rectangle buttonNewGame;
//    private Rectangle buttonLoadGame;

    private Vector<Rectangle> buttons;

    private int buttonDistance = 30;

    private int leftMenu;
    private int topMenu = 150;

    public GameMenu(GameWindow gameWindow){

        //buttons = new Vector<>();
        
        this.gw = gameWindow;
        

        leftMenu = (gw.getWidth()-buttonWidth) /2;

        startNewGame = new MenuButton(leftMenu,topMenu,buttonWidth,buttonHeight,"Neues Spiel");





//        buttonNewGame = new Rectangle(leftMenu,topMenu,buttonWidth,buttonHeight);
//        buttonLoadGame = new Rectangle(leftMenu,(int)buttonNewGame.getY()+ buttonDistance+buttonHeight,buttonWidth,buttonHeight);
//        buttonEnd = new Rectangle(leftMenu,(int)buttonLoadGame.getY()+buttonDistance+buttonHeight,buttonWidth,buttonHeight);
//
//        buttons.add(buttonNewGame);
//        buttons.add(buttonLoadGame);
//        buttons.add(buttonEnd);
    }

    @Override
    public void update(long dT) {


    }

    @Override
    public void render(Graphics g) {

        startNewGame.render(g);
//        for (Rectangle r :
//                buttons) {
//
//        g.drawRect((int)r.getX(),(int) r.getY(),(int)r.getWidth(), (int)r.getHeight());
//
//        }
//        g.drawString("Neues Spiel", leftMenu+20, buttonNewGame.y+20);
//
//        g.drawString("Spiel Laden", leftMenu+20, buttonLoadGame.y+20);
//        g.drawString("Beenden", leftMenu+20, buttonEnd.y+20);

    }


//    public Rectangle getButtonEnd(){
//        return buttonEnd;
//    }

    public void clickedAt(Point p){

//        if(buttonNewGame.contains(p)){
//            gw.setGameState(GameState.MAINGAME);
//
//        }
//
//        if(buttonEnd.contains(p)){
//            System.exit(0);
//        }

    }

    public void setMousePosition(Point p){
//
//        for (Rectangle r : buttons) {
//            if(r.contains(p)){
//
//
//                System.out.println("inside");
//
//
//            }
//        }

    }
}