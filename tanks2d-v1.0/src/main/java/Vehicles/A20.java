package Vehicles;

import Engine.ImageManager;
import Logic.Ammo;
import Logic.TankManipulator;


public class A20 extends Tank {
    public A20(int x, int y, TankManipulator owner) {
        super(x, y, ImageManager.getImage_manager().getImage(2), owner, 500);
        speedForward = 0.4;
        speedBack = -0.2;
        body_rotation = 1;
        turret = new TankTurret(x + image.getWidth(null)/4,y, ImageManager.getImage_manager().getImage(3), this);
        ammo = new Ammo(turret.getX(),turret.getY(), ImageManager.getImage_manager().getImage(4), 3000, 100, 2);
    }
}
