package Logic;

import java.awt.*;
import java.io.Serializable;

public abstract class MapObject implements Serializable {
    private double x;
    private double y;
    protected Image image;
    private double health;
    private boolean isPlayer = false;
    int id = 0;

    public Image getImage(){return image;}

    public void setId(int id){
        this.id = id;
    }

    public int getId(){return id;}

    public boolean getIsPlayer(){return isPlayer;}
    public void setIsPlayer(boolean b){isPlayer = b;}

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getWidth(){return image.getWidth(null)/2;}

    double getHeigth() {
        return image.getHeight(null)/2;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public MapObject(double x, double y, Image image, double health){
        this.image = image;
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public void draw(Graphics2D g){
        g.drawImage(image,(int)(x-getWidth()),(int)(y-getHeigth()),null);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
