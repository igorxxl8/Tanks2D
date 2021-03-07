package Vehicles;

import Engine.ImageManager;
import Logic.Ammo;
import Logic.MapObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TankTurret extends MapObject implements IMoveable {

    private Tank owner;
    private double angle;

    TankTurret(double x, double y, Image image, Tank owner) {
        super(x, y, image, 0);
        this.owner = owner;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void draw(Graphics2D g){
        AffineTransform affine = new AffineTransform();
        affine.rotate((angle), getX() - image.getWidth(null)/4, getY());
        g.setTransform(affine);
        super.draw(g);
    }

    public double getGunX(){
        return getX() + Math.cos(angle)*image.getWidth(null)/2;
    }

    public double getGunY(){
        return getY() + Math.sin(angle)* image.getWidth(null)/2;
    }

    public void move(long time) {
        double mX = owner.manipulator.getTurretX();
        double mY = owner.manipulator.getTurretY();
        double ptX = getX();
        double ptY = getY();
        if(mX > ptX)
            angle = Math.atan((ptY - mY) / (ptX - mX));
        else{
            angle =-Math.atan((ptY - mY) / (mX - ptX)) - Math.toRadians(180);
        }
        if(owner.rotateLeft)
            owner.angle -= 1 * owner.body_rotation;
        else if(owner.rotateRight)
            owner.angle += 1 * owner.body_rotation;
    }
}
