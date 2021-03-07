package Logic;

import Vehicles.IMoveable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Ammo extends MapObject implements IMoveable, Serializable {
    private boolean isFire = false;
    private boolean canFire = true;
    private double angle;
    private double speedForward;
    private double reloadingTime;
    private double tempReloading = reloadingTime;
    private double damage;

    public void setCanFire(boolean b){canFire = b;}

    public boolean isCanFire() {
        return canFire;
    }

    public double getReloadingTime() {
        return reloadingTime;
    }

    public Double getTempReloading() {
        return tempReloading;
    }

    public void setTempReloading(double value){
        tempReloading = value;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Ammo(double x, double y, Image image, double reloading, double damage, double speedForward) {
        super(x, y, image, 0);
        this.reloadingTime = reloading;
        this.damage = damage;
        this.speedForward = speedForward;
    }

    public void setFire(boolean b) {
        isFire = b;
    }

    public boolean getFire() {
        return isFire;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform affine = new AffineTransform();
        affine.rotate((angle), getX() - image.getWidth(null) / 2, getY());
        g.setTransform(affine);
        super.draw(g);
        g.setTransform(new AffineTransform());
    }

    public void move(long time) {
        if (isFire) {
            setX(getX() + speedForward * time * Math.cos(angle));
            setY(getY() + speedForward * time * Math.sin(angle));
        }

    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
