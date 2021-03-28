package Vehicles;

import Resources.ImageManager;
import Logic.Ammo;
import Logic.TankManipulator;

public class PzKpwVIHTiger extends  Tank {
    public PzKpwVIHTiger(int x, int y, TankManipulator owner) {
        super(x, y, ImageManager.getImage_manager().getImage(12), owner, 800);
        speedForward = 0.103;
        speedBack = -0.05;
        bodyRotation = 0.6;
        turret = new TankTurret(x + image.getWidth(null)/2.5,y, ImageManager.getImage_manager().getImage(13), this);
        ammo = new Ammo(turret.getX(),turret.getY(), ImageManager.getImage_manager().getImage(14), 6400, 250, 1.8);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        turret.setX(x + getWidth()/1.4);
    }
}
