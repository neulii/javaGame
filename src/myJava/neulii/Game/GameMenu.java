package myJava.neulii.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameMenu implements GameObject{

    private GameWindow gw;

    private int buttonWidth = 180;
    private int buttonHeight = 60;
    private int buttonBetween = 40;

    private ArrayList<MenuButton> menuButtons;

    private Vector<Rectangle> buttons;

    private int buttonDistance = 30;

    private int leftMenu;
    private int topMenu = 150;

    public GameMenu(GameWindow gameWindow){

        this.gw = gameWindow;
        leftMenu = (gw.getWidth()-buttonWidth) /2;
        menuButtons = new ArrayList<>();
    }

    @Override
    public void update(long dT) {


    }

    @Override
    public void render(Graphics g) {

        for (MenuButton button : menuButtons) {
            button.render(g);

        }
    }

    public void addMenuButton(String buttonText){
        MenuButton tempButton = new MenuButton(leftMenu,topMenu+(menuButtons.size()*(buttonHeight + buttonDistance)),buttonWidth,buttonHeight,buttonText);

        menuButtons.add(tempButton);
    }

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