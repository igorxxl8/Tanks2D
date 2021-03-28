package Logic;

import Resources.ImageManager;

import java.awt.*;
import java.io.Serializable;

public class Background extends MapObject implements Serializable {
    private int currentBgXPosition = 0;
    private int currentBgYPosition = 0;
    Background(float x, float y, int lvl) {
        super(x, y, ImageManager.getImage_manager().getImage(lvl*20), 0);
    }

    public int getCurrentBgXPosition(){
        return currentBgXPosition;
    }

    public int getCurrentBgYPosition(){
        return currentBgYPosition;
    }

    public void setCurrentBgXPosition(int currentBgXPosition){
        this.currentBgXPosition = currentBgXPosition;
    }

    public void setCurrentBgYPosition(int currentBgYPosition) {
        this.currentBgYPosition = currentBgYPosition;
    }

    @Override
    public void draw(Graphics2D g){
        g.drawImage(image, currentBgXPosition, currentBgYPosition,null);
    }
}