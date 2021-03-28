package Logic;

import Resources.ImageManager;

import java.io.Serializable;

public class Block extends MapObject implements Serializable {
    Block(double x, double y, int type, int id)
    {
        super(x,y,ImageManager.getImage_manager().getImage(type), type==-7?500:type*10);
        this.id = id;
        if(type == 21) setHealth(100);
        else if(type ==22) setHealth(200);
    }
}
