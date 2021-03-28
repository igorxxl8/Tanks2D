package Vehicles;

import Resources.ImageManager;
import Logic.Ammo;
import Logic.TankManipulator;

public class T34_85 extends Tank {
    public T34_85(int x, int y, TankManipulator owner) {
        super(x, y, ImageManager.getImage_manager().getImage(6), owner, 6000);
        speedForward = 0.49;
        speedBack = -0.05;
        bodyRotation = 1;
        turret = new TankTurret(x + image.getWidth(null)/4,y, ImageManager.getImage_manager().getImage(7), this);
        ammo = new Ammo(turret.getX(), turret.getY(), ImageManager.getImage_manager().getImage(4),5000, 200, 2.5);
    }
}
