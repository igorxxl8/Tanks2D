package Logic;

import Vehicles.PzKpwIVH;
import Vehicles.PzKpwVIHTiger;

import java.io.Serializable;
import java.util.LinkedList;

public class Enemy extends TankManipulator implements Serializable {
    private final Graph graph;
    private final Player player;
    private int position;
    private final int level;
    private final Background bg;
    private LinkedList<Integer> path;
    private final EnemyStrategy strategy;

    public int getLevel() {
        return level;
    }

    public int getPosition(){return position;}

    Enemy(int x, int y, int position, int level, Graph graph, Player player, Background bg){
        this.level = level;
        switch (level) {
            case 1 -> tank = new PzKpwIVH(x, y, this);
            case 2 -> tank = new PzKpwVIHTiger(x, y, this);
        }
        this.graph = graph;
        this.player = player;
        tank.setId(position);
        path = new LinkedList<>();
        this.position = position;
        this.bg = bg;
        strategy = new EnemyStrategy();
        path = strategy.executeStrategy(graph, position);
        setTankLauncher();
    }

    Enemy(int position, int level, Graph graph, Player player, Background bg) {
        int x = 250 + (position % 12 - 1) * 600, y = 250 + (position / 12) * 600;
        this.level = level;
        switch (level) {
            case 1 -> tank = new PzKpwIVH(x, y, this);
            case 2 -> tank = new PzKpwVIHTiger(x, y, this);
        }
        this.graph = graph;
        this.player = player;
        tank.setId(position);
        path = new LinkedList<>();
        this.position = position;
        this.bg = bg;
        strategy = new EnemyStrategy();
        path = strategy.executeStrategy(graph, position);
        setTankLauncher();
    }

    private void checkAndChangeStrategy(){
        double x = bg.getCurrentBgXPosition();
        double y = bg.getCurrentBgYPosition();
        if(tank.getHealth() <= 200 && !(strategy.getStrategy() instanceof Damaged)){
            path.clear();
            strategy.setStrategy(new Damaged());
        }
        else if(x < -5000 && y < -4000 && !(strategy.getStrategy() instanceof Defense) && !(strategy.getStrategy() instanceof Damaged)) {
            if(path == null) path = new LinkedList<>();
            path.clear();
            strategy.setStrategy(new Defense());
        }

    }

    private void updateCurrentPath(){
        if (path != null && path.isEmpty()) {
            path = strategy.executeStrategy(graph, position);
        }
    }

    private double calculateAngle(double x, double y, double tX, double tY) {
        double tankAngle = getTank().getAngle();
        if (tX == x && tY == y) return tankAngle;
        if (tX == x) {
            if (tY < y)
                return 270;
            else if (tY > y) return 90;
        } else if (tY == y) {
            if (tX > x)
                return 180;
            else if (tX < x) return 0;
        } else {
            final var v = Math.toDegrees(Math.atan((tX - x) / (tY - y)));
            if (tY < y) {
                return (90 - v);
            } else return (270 - v);
        }
        return 0;
    }

    public void move(){
        checkAndChangeStrategy();
        updateCurrentPath();
        if(path != null) {
            int pos = path.getLast();
            double x = 250 + (pos % 12 - 1) * 600, y = 250 + (pos / 12) * 600;
            double tankX = getTank().getX() - bg.getCurrentBgXPosition();
            double tankY = getTank().getY() - bg.getCurrentBgYPosition();
            double angle = calculateAngle(x, y, tankX, tankY);
            double tAngle = getTank().getAngle();
            int e = 20;// погрешность
            if ((x < tankX + e && x > tankX - e) && (y < tankY + e || y > tankY - e)) {
                path.removeLast();
                position = pos;
            } else if (angle > tAngle + e || angle < tAngle - e) {
                launcher.turnRight();
            } else {
                launcher.goForward();
            }
        }
        else{
            launcher.stopTank();
        }
    }

    @Override
    public void updateTurretXY() {
        turretX = player.getTank().getX();
        turretY = player.getTank().getY();
    }
}

