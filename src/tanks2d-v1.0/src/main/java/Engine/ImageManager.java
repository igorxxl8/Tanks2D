package Engine;

import javax.swing.*;
import java.awt.*;

public class ImageManager {
    private static ImageManager image_manager;

    private Image lvl1_bg;
    private Image lvl1_miniMap;
    private Image lvl1_block1;
    private Image lvl1_block2;
    private Image victory;
    private Image lose;
    private Image logo;
    private Image A20Texture;
    private Image A20Turret;
    private Image a20Ammo;
    private Image T34_85Texture;
    private Image T34_85Turret;
    private Image PzKpfw_IVH_Texture;
    private Image PzKpfw_IVH_Turret;
    private Image PzKpfw_VIH_Tiger_Texture;
    private Image PzKpfw_VIH_Tiger_Turret;
    private Image tigerAmmo;
    private Image cursor;
    private Image menu;
    private Image killed;
    private Image miniMapBlock1;
    private Image miniMapBlock2;
    private Image miniMapEnemy;
    private Image miniMapPlayer;
    private Image miniMapBase;
    private Image a1;
    private Image a2;
    private Image a3;
    private Image a4;
    private Image a5;
    private Image a6;
    private Image base;

    static {
        image_manager = new ImageManager();
    }


    private ImageManager(){
        //можно будет еще инкапсулировать текстурки в уровень,
        // для разых уровней разные текстурки танков и др
        lose = new ImageIcon("resources/res/lose.jpg").getImage();
        victory = new ImageIcon("resources/res/victory.jpg").getImage();
        base = new ImageIcon("resources/res/level1/base.png").getImage();
        a20Ammo = new ImageIcon("resources/res/tanks/a20ammo-1.png").getImage();
        tigerAmmo = new ImageIcon("resources/res/tanks/tigerAmmo.png").getImage();
        killed = new ImageIcon("resources/res/kill.jpg").getImage();
        menu = new ImageIcon("resources/res/menu.jpg").getImage();
        logo = new ImageIcon("resources/res/logo.png").getImage();
        cursor = new ImageIcon("resources/res/cursor.png").getImage();
        lvl1_bg = new ImageIcon("resources/res/level1/background.jpg").getImage();
        lvl1_miniMap = new ImageIcon("resources/res/level1/minimap.png").getImage();
        miniMapBlock1 = new ImageIcon("resources/res/level1/minimapBlock1.png").getImage();
        miniMapBlock2 = new ImageIcon("resources/res/level1/minimapBlock2.png").getImage();
        miniMapEnemy = new ImageIcon("resources/res/level1/minimapEnemy.png").getImage();
        miniMapPlayer = new ImageIcon("resources/res/level1/minimapPlayer.png").getImage();
        miniMapBase = new ImageIcon("resources/res/level1/minimapBase.png").getImage();
        lvl1_block1 = new ImageIcon("resources/res/level1/block1.png").getImage();
        lvl1_block2 = new ImageIcon("resources/res/level1/block2.png").getImage();
        A20Texture = new ImageIcon("resources/res/tanks/a20.png").getImage();
        A20Turret = new ImageIcon("resources/res/tanks/a20_turret.png").getImage();
        T34_85Texture = new ImageIcon("resources/res/tanks/t34_85.png").getImage();
        T34_85Turret = new ImageIcon("resources/res/tanks/t34_85_turret.png").getImage();
        PzKpfw_IVH_Texture = new ImageIcon("resources/res/tanks/pzkpfw_IVH.png").getImage();
        PzKpfw_IVH_Turret = new ImageIcon("resources/res/tanks/pzkpfw_IVH_turret.png").getImage();
        PzKpfw_VIH_Tiger_Texture = new ImageIcon("resources/res/tanks/pzkpfw_VIH_TigerI.png").getImage();
        PzKpfw_VIH_Tiger_Turret = new ImageIcon("resources/res/tanks/pzkpfw_VIH_TigerI_turret.png").getImage();
        a1 = new ImageIcon("resources/res/tanks/1.png").getImage();
        a2 = new ImageIcon("resources/res/tanks/2.png").getImage();
        a3 = new ImageIcon("resources/res/tanks/3.png").getImage();
        a4 = new ImageIcon("resources/res/tanks/v1.png").getImage();
        a5 = new ImageIcon("resources/res/tanks/v1_1.png").getImage();
        a6 = new ImageIcon("resources/res/tanks/v1_2.png").getImage();
    }

    public static ImageManager getImage_manager(){
        ImageManager localInstance = image_manager;
        if(localInstance == null){
            synchronized (ImageManager.class){
                localInstance = image_manager;
            }
        }
        return localInstance;
    }

    public Image getImage(int id){
        switch (id){
            case -8:return miniMapBase;
            case -7:return base;
            case -6:return miniMapBlock2;
            case -5:return miniMapPlayer;
            case -4:return miniMapEnemy;
            case -3:return miniMapBlock1;
            case -2:return killed;
            case -1:return menu;
            case 0:return cursor;
            case 1:return logo;
            case 2:return A20Texture;
            case 3:return A20Turret;
            case 4:return a20Ammo;
            case 6:return T34_85Texture;
            case 7:return T34_85Turret;
            case 10:return PzKpfw_IVH_Texture;
            case 11:return PzKpfw_IVH_Turret;
            case 12:return PzKpfw_VIH_Tiger_Texture;
            case 13:return PzKpfw_VIH_Tiger_Turret;
            case 14:return tigerAmmo;
            case 20:return lvl1_bg;
            case 21:return lvl1_block1;
            case 22:return lvl1_block2;
            case 23:return lvl1_miniMap;
            case 24:return victory;
            case 25:return lose;
            case 100:return a1;
            case 101:return a2;
            case 102:return a3;
            case 103:return a4;
            case 104:return a5;
            case 105:return a6;
            default:return null;
        }
    }
}

