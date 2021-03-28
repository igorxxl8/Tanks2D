package Logic;
import Vehicles.*;

import java.awt.*;
import java.io.Serializable;


public class Player extends TankManipulator implements Serializable {
    Player(int x, int y, int lvl) {
        switch (lvl) {
            case 2 -> tank = new A20(x, y, this);
            case 1 -> tank = new T34_85(x, y, this);
        }
        tank.setIsPlayer(true);
        tank.setId(200);
        setTankLauncher();
    }

    void fire(){
        tank.fire();
    }

    @Override
    void move() {
    }

    @Override
    public void updateTurretXY(){
        var point = MouseInfo.getPointerInfo().getLocation();
        turretX = point.x;
        turretY = point.y;
    }
}



