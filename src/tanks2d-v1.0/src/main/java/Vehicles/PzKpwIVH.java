package Vehicles;

import Resources.ImageManager;
import Logic.Ammo;
import Logic.TankManipulator;

public class PzKpwIVH extends Tank {
    public PzKpwIVH(int x, int y, TankManipulator owner) {
        super(x, y, ImageManager.getImage_manager().getImage(10), owner, 400);
        speedForward = 0.3;
        speedBack = -0.05;
        bodyRotation = 0.7;
        turret = new TankTurret(x + image.getWidth(null)/4,y, ImageManager.getImage_manager().getImage(11), this);
        ammo = new Ammo(turret.getX(),turret.getY(), ImageManager.getImage_manager().getImage(4), 5500, 150, 1.5);
    }
}
