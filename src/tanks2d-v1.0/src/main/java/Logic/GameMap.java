package Logic;

import Engine.Animation;
import Engine.AnimationPlayer;
import Engine.GameSaver;
import Resources.ImageManager;
import Vehicles.IMoveable;
import Vehicles.Tank;
import Vehicles.TankTurret;
import Core.Pair;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap implements Serializable {
    private final ArrayList<MapObject> mpObjects = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<TankManipulator> manipulators = new ArrayList<>();
    private final Graph graph = new Graph();
    private final Background background;
    private final Player player;
    private final MapPanel mapPanel;
    private Animation fire;
    private Animation explosion;
    private final Game owner;
    boolean isVictory = false;
    boolean isLose = false;

    public Game getOwner(){return owner;}

    public Background getBackground(){return background;}

    GameMap(Game owner) {
        this.owner = owner;
        player = owner.getPlayer();
        background = new Background(960, 540, owner.level);
        mpObjects.add(background);
        setBlocks(owner.level);
        setGraph(owner.level);
        addEnemies(owner.level);
        mpObjects.add(player.getTank().getAmmo());
        mpObjects.add(player.getTank());
        manipulators.add(player);
        mapPanel = new MapPanel(ImageManager.getImage_manager().getImage(23), 40, 27);
        loadAnimation();
    }

    public ArrayList<MapObject> getMpObjects() {
        return mpObjects;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public Player getPlayer(){return player;}

    GameMap(Game owner, GameSaver savedMap) {
        this.owner = owner;
        background = new Background(960, 540, savedMap.getLevel());
        background.setCurrentBgXPosition(savedMap.getCurrentBgXPosition());
        background.setCurrentBgYPosition(savedMap.getCurrentBgYPosition());
        player = new Player((int) savedMap.getPlayerX(), (int) savedMap.getPlayerY(), savedMap.getLevel());
        player.getTank().setHealth(savedMap.getHealth());
        player.getTank().setAngle(savedMap.getPlayerAngle());
        owner.setPlayer(player);
        mpObjects.add(background);
        loadBlocks(savedMap.getBlocks());
        setGraph(savedMap.getLevel());
        loadEnemies(savedMap.getEnemies());
        mpObjects.add(player.getTank().getAmmo());
        mpObjects.add(player.getTank());
        manipulators.add(player);
        mapPanel = new MapPanel(ImageManager.getImage_manager().getImage(23), 40-savedMap.getCurrentBgXPosition()/25,27-savedMap.getCurrentBgYPosition()/25);
        loadAnimation();
    }

    private void loadBlocks(int[][] temp) {
        for (int[] aTemp : temp) mpObjects.add(new Block(aTemp[0], aTemp[1], aTemp[2], aTemp[2]));
    }

    private void loadEnemies(int[][] a){
        for (int[] anA : a) {
            Enemy enemy = new Enemy(anA[0], anA[1], anA[2], anA[3], graph, player, background);
            mpObjects.add(enemy.getTank().getAmmo());
            mpObjects.add(enemy.getTank());
            manipulators.add(enemy);
            enemies.add(enemy);
        }
    }

    void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("resources/res/Temp/saveGame.sav");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new GameSaver(this));
        oos.flush();
        oos.close();
    }

    private void loadAnimation(){
        fire = new Animation();
        explosion = new Animation();
        fire.addScene(ImageManager.getImage_manager().getImage(100),40);
        fire.addScene(ImageManager.getImage_manager().getImage(101),40);
        fire.addScene(ImageManager.getImage_manager().getImage(102),40);
        explosion.addScene(ImageManager.getImage_manager().getImage(105), 120);
        explosion.addScene(ImageManager.getImage_manager().getImage(104), 120);
        explosion.addScene(ImageManager.getImage_manager().getImage(103), 120);
    }

    private void addEnemies(int level) {
        if (level == 1) {
            Enemy enemy1 = new Enemy(28, level, graph, player, background);
            Enemy enemy2 = new Enemy(89, level, graph, player, background);
            Enemy enemy3 = new Enemy(74, level, graph, player, background);
            Enemy enemy4 = new Enemy(134, level, graph, player, background);
            Enemy enemy5 = new Enemy(30, level, graph, player, background);
            Enemy enemy6 = new Enemy(126, level, graph, player, background);
            Enemy enemy7 = new Enemy(33, level, graph, player,background);
            Enemy enemy8 = new Enemy(93, level, graph, player, background);
            Enemy enemy9 = new Enemy(143, level+1, graph, player, background);
            mpObjects.add(enemy1.getTank().getAmmo());
            mpObjects.add(enemy2.getTank().getAmmo());
            mpObjects.add(enemy3.getTank().getAmmo());
            mpObjects.add(enemy4.getTank().getAmmo());
            mpObjects.add(enemy5.getTank().getAmmo());
            mpObjects.add(enemy6.getTank().getAmmo());
            mpObjects.add(enemy7.getTank().getAmmo());
            mpObjects.add(enemy8.getTank().getAmmo());
            mpObjects.add(enemy9.getTank().getAmmo());
            mpObjects.add(enemy1.getTank());
            mpObjects.add(enemy2.getTank());
            mpObjects.add(enemy3.getTank());
            mpObjects.add(enemy4.getTank());
            mpObjects.add(enemy5.getTank());
            mpObjects.add(enemy6.getTank());
            mpObjects.add(enemy7.getTank());
            mpObjects.add(enemy8.getTank());
            mpObjects.add(enemy9.getTank());
            manipulators.add(enemy1);
            manipulators.add(enemy2);
            manipulators.add(enemy3);
            manipulators.add(enemy4);
            manipulators.add(enemy5);
            manipulators.add(enemy6);
            manipulators.add(enemy7);
            manipulators.add(enemy8);
            manipulators.add(enemy9);
            enemies.add(enemy1);
            enemies.add(enemy2);
            enemies.add(enemy3);
            enemies.add(enemy4);
            enemies.add(enemy5);
            enemies.add(enemy6);
            enemies.add(enemy7);
            enemies.add(enemy8);
            enemies.add(enemy9);
        }
    }

    private void setBlocks(int level) {
        if (level == 1) {
            List<String> list = new ArrayList<>();
            Scanner in = null;
            try {
                String path = "resources/blocks.txt";
                in = new Scanner(new File(path));
                GameMap.class.getResourceAsStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert in != null;
            while (in.hasNextLine()) list.add(in.nextLine());
            String[] ar = list.toArray(new String[0]);
            for (String s : ar) {
                try {
                    String[] temp = s.split("\t");
                    mpObjects.add(new Block(Double.parseDouble(temp[0]),
                                            Double.parseDouble(temp[1]),
                                            Integer.parseInt(temp[2]),
                                            Integer.parseInt(temp[2])));
                } catch (Exception ignored) {
                }
            }
        }
        mpObjects.add(new Block(7100,6900,-7,  -7));
    }

    private void setGraph(int level) {
        if (level == 1) {
            List<String> list = new ArrayList<>();
            Scanner in = null;
            try {
                String path = "resources/graphEdges.txt";
                in = new Scanner(new File(path));
                GameMap.class.getResourceAsStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert in != null;
            while (in.hasNextLine()) list.add(in.nextLine());
            String[] ar = list.toArray(new String[0]);
            for (String s : ar) {
                try {
                    String[] temp = s.split("\t");
                    graph.addEdge(Integer.parseInt(temp[0]),
                                  Integer.parseInt(temp[1]));
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void checkObjectCollision() {
        for (TankManipulator tankManipulator : manipulators) {
            Tank tank = tankManipulator.getTank();
            double tankX = tank.getX();
            double tankY = tank.getY();
            for (MapObject mp : mpObjects) {
                if (tank != mp && !(mp instanceof Background) && !(mp instanceof Ammo)) {
                    {
                        if (tankX + tank.getWidth() >= mp.getX() - mp.getWidth()
                                && tankX < mp.getX()
                                && tankY <= mp.getY() + tank.image.getHeight(null)
                                && tankY >= mp.getY() - tank.image.getHeight(null)) {
                            tank.setX(tankX - 18);
                        }

                        if (tankX - tank.getWidth() <= mp.getX() + mp.getWidth()
                                && tankX > mp.getX()
                                && tankY <= mp.getY() + tank.image.getHeight(null)
                                && tankY >= mp.getY() - tank.image.getHeight(null)) {
                            tank.setX(tankX + 18);
                        }

                        if (tankY + tank.getWidth() >= mp.getY() - mp.getHeight()
                                && tankY < mp.getY()
                                && tankX <= mp.getX() + tank.image.getHeight(null)
                                && tankX >= mp.getX() - tank.image.getHeight(null)
                                ) {
                            tank.setY(tankY - 18);
                        }

                        if (tankY - tank.getWidth() <= mp.getY() + mp.getHeight()
                                && tankY > mp.getY()
                                && tankX <= mp.getX() + tank.image.getHeight(null)
                                && tankX >= mp.getX() - tank.image.getHeight(null)
                                ) {
                            tank.setY(tankY + 18);
                        }
                    }
                }
            }
        }
    }

    private void destroyBlocks() {
        for (TankManipulator manipulator: manipulators ) {
            Ammo ammo = manipulator.getTank().getAmmo();
            double ammoX = ammo.getX();
            double ammoY = ammo.getY();
            int destroyedObjectIndex = -1;
            int destroyedAmmoIndex = -1;
            if (ammo.getFire()) {
                for (MapObject mp : mpObjects) {
                    if (ammo != mp && !(mp instanceof Background) && manipulator.getTank() != mp) {
                        if (ammoX >= mp.getX() - mp.getWidth()
                                && ammoX < mp.getX()
                                && ammoY <= mp.getY() + mp.image.getHeight(null)
                                && ammoY >= mp.getY() - mp.image.getHeight(null)) {
                            destroyedAmmoIndex = mpObjects.indexOf(ammo);
                            destroyedObjectIndex = mpObjects.indexOf(mp);
                            mp.setHealth(mp.getHealth() - ammo.getDamage());

                        } else if (ammoX <= mp.getX() + mp.getWidth()
                                && ammoX > mp.getX()
                                && ammoY <= mp.getY() + mp.image.getHeight(null)
                                && ammoY >= mp.getY() - mp.image.getHeight(null)) {
                            destroyedAmmoIndex = mpObjects.indexOf(ammo);
                            destroyedObjectIndex = mpObjects.indexOf(mp);
                            mp.setHealth(mp.getHealth() - ammo.getDamage());
                        } else if (ammoY >= mp.getY() - mp.getHeight()
                                && ammoY < mp.getY()
                                && ammoX <= mp.getX() + mp.image.getHeight(null)
                                && ammoX >= mp.getX() - mp.image.getHeight(null)
                                ) {
                            destroyedAmmoIndex = mpObjects.indexOf(ammo);
                            destroyedObjectIndex = mpObjects.indexOf(mp);
                            mp.setHealth(mp.getHealth() - ammo.getDamage());
                        } else if (ammoY <= mp.getY() + mp.getHeight()
                                && ammoY > mp.getY()
                                && ammoX <= mp.getX() + mp.image.getHeight(null)
                                && ammoX >= mp.getX() - mp.image.getHeight(null)
                                ) {
                            destroyedAmmoIndex = mpObjects.indexOf(ammo);
                            destroyedObjectIndex = mpObjects.indexOf(mp);
                            mp.setHealth(mp.getHealth() - ammo.getDamage());
                        }
                        if (destroyedAmmoIndex != -1) {
                            mpObjects.remove(destroyedAmmoIndex);
                            ammo.setFire(false);
                            mpObjects.add(destroyedAmmoIndex, ammo);
                        }
                        if (destroyedObjectIndex != -1) {
                            if(mpObjects.get(destroyedObjectIndex).getHealth() <= 0) {
                                drawAll(owner.screenManager.getGraphics());
                                Thread t = new Thread(new AnimationPlayer(explosion, owner.screenManager, mpObjects.get(destroyedObjectIndex).getX(), mpObjects.get(destroyedObjectIndex).getY(), 0, 361));
                                t.start();
                                int id = mpObjects.get(destroyedObjectIndex).id;
                                if(id == -7)
                                    isVictory = true;
                                if(id == 200)
                                    isLose = true;
                                MapObject ob = mpObjects.get(destroyedObjectIndex);
                                if (ob instanceof Tank){
                                    if(!ob.getIsPlayer()) {
                                        int index = 0;
                                        for(Enemy e: enemies){
                                            if (e.getTank() == ob)
                                            {
                                                index = enemies.indexOf(e);
                                                break;
                                            }
                                        }
                                        enemies.remove(index);
                                    }
                                }
                                mpObjects.remove(destroyedObjectIndex);
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    private void checkOutOfBounds() {
        Tank playerTank = player.getTank();
        TankTurret tankTurret = playerTank.getTurret();
        double playerTankX = playerTank.getX();
        double playerTankY = playerTank.getY();
        int currentBgXPosition = background.getCurrentBgXPosition();
        int currentBgYPosition = background.getCurrentBgYPosition();
        final int X = 1850;
        final int Y = 1020;
        if (playerTankX > X && currentBgXPosition - X > -X * 4) {
            background.setCurrentBgXPosition(currentBgXPosition - X);
            playerTank.setX(0);
            for (MapObject mp : mpObjects) {
                if (!mp.getIsPlayer()) {
                    double x = mp.getX() - X;
                    mp.setX(x);
                }
            }
        } else if (playerTankX > X) {
            playerTank.setX(X);
        } else if (playerTankX < 0 && currentBgXPosition + X <= 0) {
            background.setCurrentBgXPosition(currentBgXPosition + X);
            playerTank.setX(X);
            for (MapObject mp : mpObjects) {
                if (!mp.getIsPlayer()) {
                    double x = mp.getX() + X;
                    mp.setX(x);
                }
            }
        } else if (playerTankX < 0) {
            playerTank.setX(0);
        }
        if (playerTankY > Y && currentBgYPosition - Y > -Y * 7) {
            background.setCurrentBgYPosition(currentBgYPosition - Y);
            playerTank.setY(0);
            tankTurret.setY(0);
            for (MapObject mp : mpObjects) {
                if (!mp.getIsPlayer()) {
                    double y = mp.getY() - Y;
                    mp.setY(y);
                }
            }
        } else if (playerTankY > Y) {
            playerTank.setY(Y);
            tankTurret.setY(Y);
        } else if (playerTankY < 0 && currentBgYPosition + Y <= 0) {
            background.setCurrentBgYPosition(currentBgYPosition + Y);
            playerTank.setY(Y);
            tankTurret.setY(Y);
            for (MapObject mp : mpObjects) {
                if (!mp.getIsPlayer()) {
                    double y = mp.getY() + Y;
                    mp.setY(y);
                }
            }
        } else if (playerTankY < 0) {
            playerTank.setY(0);
            tankTurret.setY(0);
        }
        for(TankManipulator tm : manipulators){
            Ammo am = tm.getTank().getAmmo();
            int destroyedAmmo = mpObjects.indexOf(am);
            if(am.getX() - currentBgXPosition < 0 || am.getY() - currentBgYPosition < 0 || am.getX() + currentBgXPosition > X*4 || am.getY() > Y*7){
                mpObjects.remove(destroyedAmmo);
                am.setFire(false);
                mpObjects.add(destroyedAmmo, am);
            }
        }
    }

    private void drawMiniMap(Graphics2D g) {
        g.drawImage(mapPanel.miniMap, 0, 0, null);
        mapPanel.getMapObjects(mpObjects);
        for (Pair<Image, Double[]> mpJ : mapPanel.objs) {
            double x = mpJ.getValue()[0];
            double y = mpJ.getValue()[1];
            g.drawImage(mpJ.getKey(), (int) x, (int) y, null);
        }
    }

    void drawAll(Graphics2D g) {
        for (MapObject mpObj : mpObjects) {
            mpObj.draw(g);
        }
        drawMiniMap(g);
        g.setFont(new Font("Tahoma", Font.PLAIN, 20));
        g.setColor(new Color(255, 255,255));
        double reloading = (double)((int)(player.getTank().getAmmo().getTempReloading() / 7.07))/100;
        g.drawString("Ammo:", 310,30);
        if(!player.getTank().getAmmo().isCanFire()) g.drawString(Double.toString(reloading),400,30);
        else g.drawString("Ready", 400,30);
        g.drawString("HP:", 310,50);
        g.drawString(Double.toString(player.getTank().getHealth()), 400, 50);
        for(TankManipulator tm: manipulators){
            double tr = tm.getTank().getAmmo().getTempReloading();
            double rel = tm.getTank().getAmmo().getReloadingTime();
            if(rel - tr < 30 && rel - tr > 10)
            {
                TankTurret tur = tm.getTank().getTurret();
                Thread t = new Thread(new AnimationPlayer(fire, owner.screenManager, tur.getGunX(), tur.getGunY(), tur.getAngle(), 121));
                t.start();
            }
        }
    }

    void moveAll(long time) {
        for(TankManipulator tm: manipulators){
            Tank t = tm.getTank();
            tm.move();
            if(t != player.getTank())
            {
                double x = t.getX() - player.getTank().getX();
                double y = t.getY() - player.getTank().getY();
                if(Math.sqrt(x*x+y*y) < 1800) {
                    tm.getTank().fire();
                }
            }
        }
        checkOutOfBounds();
        checkObjectCollision();
        destroyBlocks();
        for (MapObject mpObj : mpObjects) {
            if (mpObj instanceof IMoveable) ((IMoveable) mpObj).move(time);
        }
    }
}

