package Vehicles;

import Logic.Ammo;
import Logic.MapObject;
import Logic.TankManipulator;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Tank extends MapObject implements IMoveable {

    TankManipulator manipulator;
    Ammo ammo;

    double angle = 0;
    double speedBack;
    double speedForward;
    double bodyRotation;

    private boolean toBack;
    private boolean canMove;
    boolean rotateLeft;
    boolean rotateRight;

    TankTurret turret;

    public TankTurret getTurret() {
        return turret;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    Tank(int x, int y, Image image, TankManipulator manipulator, double health) {
        super(x, y, image, health);
        this.manipulator = manipulator;
    }

    public void setAngle(double angle){this.angle = angle;}

    public double getAngle() {
        return angle;
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        turret.setX(x + getWidth() / 2);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        turret.setY(y);
    }

    public void setCanMove(boolean canMove, boolean toBack, boolean left, boolean right) {
        this.canMove = canMove;
        this.rotateLeft = left;
        this.rotateRight = right;
        this.toBack = toBack;
    }

    public void move(long time) {
        if (canMove && toBack) {
            setX(getX() + speedBack * time * Math.cos(Math.toRadians(angle)));
            setY(getY() + speedBack * time * Math.sin(Math.toRadians(angle)));
        } else if (canMove) {
            setX(getX() + speedForward * time * Math.cos(Math.toRadians(angle)));
            setY(getY() + speedForward * time * Math.sin(Math.toRadians(angle)));
        } else if (rotateLeft)
            angle -= 1 * bodyRotation;
        else if (rotateRight)
            angle += 1 * bodyRotation;
        manipulator.updateTurretXY();
        turret.move(time);
        if (!ammo.getFire()) {
            ammo.setAngle(turret.getAngle());
            ammo.setX(this.getX());
            ammo.setY(this.getY());
        }
        if(!ammo.isCanFire()){
            ammo.setTempReloading(ammo.getTempReloading() - 20);
            if(ammo.getTempReloading() <= 0) {
                ammo.setTempReloading(ammo.getReloadingTime());
                ammo.setCanFire(true);
            }
        }
        if (angle > 360) angle-=360;
        if (angle < 0) angle+=360;
    }

    public void fire(){
        if(ammo.isCanFire()) {
            ammo.setCanFire(false);
            ammo.setFire(true);
        }
    }

    @Override
    public void draw(Graphics2D g){
        AffineTransform affine = new AffineTransform();
        affine.rotate(Math.toRadians(angle), getX(), getY());
        g.setTransform(affine);
        super.draw(g);
        turret.draw(g);
        g.setTransform(new AffineTransform());
    }
}
