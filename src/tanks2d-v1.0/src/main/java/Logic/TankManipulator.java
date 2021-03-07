package Logic;

import Vehicles.Tank;

import java.io.Serializable;

public abstract class TankManipulator implements Serializable {
    Tank tank;
    TankLauncher launcher;
    double turretX;
    double turretY;

    void setTankRotator(){
        launcher = new TankLauncher(new TurnRight(tank), new GoForward(tank),
                new TurnLeft(tank), new GoBack(tank), new StopTank(tank), new GoLeft(tank),
                new GoRight(tank), new BackLeft(tank), new BackRight(tank));
    }

    abstract void move();



    public Tank getTank(){
        return tank;
    }

    public double getTurretX(){
        return turretX;
    }

    public double getTurretY(){
        return turretY;
    }

    abstract public void updateTurretXY();
}
