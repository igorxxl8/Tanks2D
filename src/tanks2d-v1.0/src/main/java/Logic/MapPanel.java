package Logic;

import Resources.ImageManager;
import Vehicles.Tank;
import Core.Pair;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

class MapPanel implements Serializable {
    ArrayList<Pair<Image,Double[]>> objs;
    Image miniMap;
    int bcX;
    int bcY;

    MapPanel(Image image, int bcX, int bcY) {
        objs = new ArrayList<>();
        miniMap = image;
        this.bcX = bcX;
        this.bcY = bcY;
    }

    void getMapObjects(ArrayList<MapObject> mpObjs)
    {
        objs.clear();
        Image base = ImageManager.getImage_manager().getImage(-8);
        Image imBl2 = ImageManager.getImage_manager().getImage(-6);
        Image imBl1 = ImageManager.getImage_manager().getImage(-3);
        Image imEn = ImageManager.getImage_manager().getImage(-4);
        Image imPl = ImageManager.getImage_manager().getImage(-5);
        double firstX = mpObjs.get(0).getX()/25 - bcX;
        double firstY = mpObjs.get(0).getY()/25 - bcY;
        for(MapObject mpObj: mpObjs) {
            if (!(mpObj instanceof Background)) {
                Double[] i = new Double[2];
                i[0] = mpObj.getX() / 25 - firstX;
                i[1] = mpObj.getY() / 25 - firstY;
                if(mpObj.id == -7){
                    i[0] = mpObj.getX() / 25 - firstX -30;
                    i[1] = mpObj.getY() / 25 - firstY -30;
                }
                if (mpObj instanceof Tank) {
                    if (mpObj.getIsPlayer()) objs.add(new Pair(imPl, i));
                    else objs.add(new Pair(imEn, i));
                } else if (mpObj.id == 21){
                    objs.add(new Pair(imBl1, i));
                }
                else if (mpObj.id == 22)  objs.add(new Pair(imBl2, i));
                else if (mpObj.id == -7) objs.add(new Pair(base, i));
            }
        }
    }
}
