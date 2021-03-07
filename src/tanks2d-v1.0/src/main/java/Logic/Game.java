package Logic;


import Engine.GameSaver;
import Engine.ImageManager;
import Engine.ScreenManager;
import Engine.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Game implements Runnable, KeyListener, MouseListener {
    private boolean running = true;
    private boolean playing = false;
    private boolean exiting = false;
    ScreenManager screenManager;
    public Player player;
    private GameMap gameMap;
    private String start_resume = "Start";
    private String save_last = "Last save";
    public int level = 1;
    private ArrayList<Integer> preessedKey;

    public void setPlayer(Player player){this.player = player;}

    private static final DisplayMode modes[] = {
            new DisplayMode(1920,1080,32,0),
            new DisplayMode(1920,1080,24,0),
            new DisplayMode(1920,1080,16,0),
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,24,0),
            new DisplayMode(800,600,16,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,24,0),
            new DisplayMode(640,480,16,0),
    };

    Player getPlayer(){
        return player;
    }

    private Game(){

    }

    public static void main(String[] args) {
        Thread t = new Thread(new Game());
        t.start();
    }

    private void init(){
        preessedKey = new ArrayList<>();
        screenManager = new ScreenManager();
        DisplayMode dm = screenManager.findFirstCompatibleMode(modes);
        screenManager.setFullScreen(dm);
        Window w = screenManager.getFullScreenWindow();
        w.addMouseListener(this);
        w.addKeyListener(this);
        player = new Player(300,300, level);
        gameMap = new GameMap(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = ImageManager.getImage_manager().getImage(0);
        Cursor cursor = toolkit.createCustomCursor(cursorImage, new Point(), "Aim");
        w.setBackground(Color.BLACK);
        w.setCursor(cursor);
    }

    private void menu() {
        Graphics2D g = screenManager.getGraphics();
        Image image = ImageManager.getImage_manager().getImage(-1);
        g.drawImage(image, 0, 0, null);
        Image logo = ImageManager.getImage_manager().getImage(1);
        g.drawImage(logo, 200, 150, null);
        g.setColor(new Color(100,100,255));
        g.setFont(new Font("Tahoma", Font.BOLD|Font.ITALIC, 60));
        g.drawString(start_resume, 200, 400);
        g.drawString(save_last, 200, 500);
        g.drawString("Exit", 200, 600);
        g.setFont( new Font("Tahoma", Font.BOLD|Font.ITALIC, 40));
        g.setColor(new Color(255, 255, 255));
        g.drawString("ver 1.0", 0, 1020);
        g.dispose();
    }

    private void victory(){
        Graphics2D g = screenManager.getGraphics();
        Image image = ImageManager.getImage_manager().getImage(24);
        g.drawImage(image, 0, 0, null);
        g.setColor(new Color(15, 255, 0));
        g.setFont(new Font("Tahoma", Font.BOLD|Font.ITALIC, 60));
        g.drawString("Victory", 200, 200);
        g.drawString("Exit", 200, 500);
        g.setFont( new Font("Tahoma", Font.BOLD|Font.ITALIC, 40));
        g.setColor(new Color(255, 255, 255));
        g.drawString("Turcevich Igor 2018", 0, 1020);
        g.dispose();
    }

    private void lose(){
        Graphics2D g = screenManager.getGraphics();
        Image image = ImageManager.getImage_manager().getImage(25);
        g.drawImage(image, 0, 0, null);
        g.setColor(new Color(255, 250, 18));
        g.setFont(new Font("Tahoma", Font.BOLD|Font.ITALIC, 60));
        g.drawString("Lose", 200, 200);
        g.drawString("Exit", 200, 500);
        g.setFont( new Font("Tahoma", Font.BOLD|Font.ITALIC, 40));
        g.setColor(new Color(255, 255, 255));
        g.drawString("Turcevich Igor 2018", 0, 1020);
        g.dispose();
    }

    public void run(){
        try{
            init();
            gameLoop();
        }finally{
            screenManager.restoreScreen();
        }
    }

    private void stop(){
        running = false;
    }

    private void pause(){
        start_resume = "Resume";
        save_last = "Save game";
        playing = false;
    }

    private void loadGame() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("resources/res/Temp/saveGame.sav");
        ObjectInputStream oin = new ObjectInputStream(fis);
        GameSaver gs = (GameSaver) oin.readObject();
        gameMap = new GameMap(this, gs);
        playing = true;
        preessedKey = new ArrayList<>();
    }

    private void saveGame() throws IOException {
        gameMap.save();
    }

    private void gameLoop(){
        long cumTime = System.currentTimeMillis();
        Sound sound = new Sound("resources/res/sounds/main.wav");
        sound.loop();

        while(running){
            if(!playing) menu();
            else if(gameMap.isLose){
                sound.stop();
                lose();
                exiting = true;
            }
            else if(!gameMap.isVictory){
                long timePassed = System.currentTimeMillis() - cumTime;
                cumTime += timePassed;
                update(timePassed);

                Graphics2D g = screenManager.getGraphics();
                draw(g);
                player.updateInfo();
                g.dispose();
            }
            else if(gameMap.isVictory){
                sound.stop();
                victory();
                exiting = true;
            }
            screenManager.update();
            try{
                Thread.sleep(20);
            }catch (Exception ignored){}
        }
    }

    private void update(long timePassed){
        gameMap.moveAll(timePassed);
    }

    private void draw(Graphics2D g){
        gameMap.drawAll(g);
    }

    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    public void keyPressed(KeyEvent e) {
        if(playing) {
            int key = e.getKeyCode();
            if(!preessedKey.contains(key)) preessedKey.add(key);
            if(key == KeyEvent.VK_ESCAPE) {
                pause();
            }
            else if(preessedKey.contains(KeyEvent.VK_P)) {
                try {
                    saveGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(preessedKey.contains(KeyEvent.VK_W)){
                if(preessedKey.contains(KeyEvent.VK_D)) player.launcher.goRight();
                else if(preessedKey.contains(KeyEvent.VK_A)) player.launcher.goLeft();
                else player.launcher.goForward();
            }
            else if(preessedKey.contains(KeyEvent.VK_S)){
                if(preessedKey.contains(KeyEvent.VK_D)) player.launcher.backLeft();
                else if(preessedKey.contains(KeyEvent.VK_A)) player.launcher.backRight();
                else player.launcher.goBack();
            }
            else if(preessedKey.contains(KeyEvent.VK_D)){
                player.launcher.turnRight();
            }
            else if(preessedKey.contains(KeyEvent.VK_A)){
                player.launcher.turnLeft();
            }
            else{
                e.consume();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(playing) {
            int keyCode = e.getKeyCode();
            int i;
            if (preessedKey.contains(keyCode)) {
                i = preessedKey.indexOf(keyCode);
                preessedKey.remove(i);
            }
            if(preessedKey.contains(KeyEvent.VK_W)){
                if(preessedKey.contains(KeyEvent.VK_D)) player.launcher.goRight();
                else if(preessedKey.contains(KeyEvent.VK_A)) player.launcher.goLeft();
                else player.launcher.goForward();
            }
            else if(preessedKey.contains(KeyEvent.VK_S)){
                if(preessedKey.contains(KeyEvent.VK_D)) player.launcher.backLeft();
                else if(preessedKey.contains(KeyEvent.VK_A)) player.launcher.backRight();
                else player.launcher.goBack();
            }
            else if(preessedKey.contains(KeyEvent.VK_D)){
                player.launcher.turnRight();
            }
            else if(preessedKey.contains(KeyEvent.VK_A)){
                player.launcher.turnLeft();
            }
            else{
                player.launcher.stopTank();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(!playing) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            int x = point.x;
            int y = point.y;
            int a;
            if(start_resume.equals("Start")) a = 440;
            else a = 530;
            if (x > 200 && x < a && y > 350 && y < 400) {
                playing = true;
            }
            if (x > 200 && x < 580 && y > 450 && y < 500) {
                if (save_last.equals("Last save")) {
                    try {
                        loadGame();//playing = true;
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    try {
                        saveGame();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            if (x > 200 && x < 400 && y > 550 && y < 600)  stop();
        }
        else if(exiting){
            Point point = MouseInfo.getPointerInfo().getLocation();
            int x = point.x;
            int y = point.y;
            if (x > 200 && x < 400 && y > 450 && y < 500)  stop();
        }
        else{
            player.fire();
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
