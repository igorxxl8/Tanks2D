package Engine;

import Logic.*;
import Vehicles.Tank;

import java.io.Serializable;
import java.util.ArrayList;

public class GameSaver implements Serializable {
    private int level;
    private double health;
    private double playerX;
    private double playerY;
    private double playerAngle;
    private int currentBgXPosition;
    private int currentBgYPosition;
    private int[][] blocks;
    private int[][] enemies;
    public GameSaver(GameMap map){
        Game gameOwner = map.getOwner();
        Tank playerTank = map.getPlayer().getTank();
        this.level = gameOwner.level;
        this.playerX = playerTank.getX();
        this.playerY = playerTank.getY();
        this.health = playerTank.getHealth();
        this.playerAngle = playerTank.getAngle();
        this.currentBgXPosition = map.getBackground().getCurrentBgXPosition();
        this.currentBgYPosition = map.getBackground().getCurrentBgYPosition();
        ArrayList<MapObject> mp = map.getMpObjects();
        ArrayList<Enemy> en = map.getEnemies();
        setBlocks(mp);
        setEnemies(en);
    }

    public int getLevel(){return level;}

    public double getHealth(){return health;}

    public double getPlayerY() {
        return playerY;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerAngle() {
        return playerAngle;
    }

    public int getCurrentBgXPosition(){return currentBgXPosition;}

    public int getCurrentBgYPosition(){return currentBgYPosition;}

    private void setBlocks(ArrayList<MapObject> mp){
        int n = 0;
        for(MapObject m: mp)
            if (m instanceof Block)
                n++;
        blocks = new int[n+1][4];
        int i = 0;
        for(MapObject m: mp) {
            if (m instanceof Block) {
                blocks[i][0] = (int)m.getX();
                blocks[i][1] = (int)m.getY();
                blocks[i][2] = m.getId();
                blocks[i][3] = m.getId();
            }
            i++;
        }
    }

    public int[][] getBlocks(){return blocks;}

    private void setEnemies(ArrayList<Enemy> en){
        int n = 0;
        enemies = new int[en.size()][4];
        int i = 0;
        for(Enemy e: en) {
                enemies[i][0] = (int)e.getTank().getX();
                enemies[i][1] = (int)e.getTank().getY();
                enemies[i][2] = e.getPosition();
                enemies[i][3] = e.getLevel();
            i++;
        }
    }

    public int[][] getEnemies(){
        return enemies;
    }

}
