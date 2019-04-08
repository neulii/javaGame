package myJava.neulii.Game;

import java.awt.*;

public class GameMenu implements GameObject{

    private Canvas gw;
    private int buttonWidth = 180;
    private int buttonHeight = 60;


    private Rectangle buttonEnd;

    private int leftMenu;
    private int topMenu = 150;

    public GameMenu(Canvas gameWindow){

        this.gw = gameWindow;

        leftMenu = (gw.getWidth()-buttonWidth) /2;

        buttonEnd = new Rectangle(leftMenu,topMenu,buttonWidth,buttonHeight);




    }

    @Override
    public void update(long dT) {


    }

    @Override
    public void render(Graphics g) {


        g.drawRect((int)buttonEnd.getX(),(int) buttonEnd.getY(),(int)buttonEnd.getWidth(), (int)buttonEnd.getHeight());
        g.drawString("Beenden", leftMenu+20, buttonEnd.y+20);





    }


    public Rectangle getButtonEnd(){
        return buttonEnd;
    }
}
