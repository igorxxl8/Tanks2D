package Resources;

import javax.swing.*;
import java.awt.*;

public class ImageManager {
    private static final ImageManager image_manager;

    private final Image lvl1_bg;
    private final Image lvl1_miniMap;
    private final Image lvl1_block1;
    private final Image lvl1_block2;
    private final Image victory;
    private final Image lose;
    private final Image logo;
    private final Image A20Texture;
    private final Image A20Turret;
    private final Image a20Ammo;
    private final Image T34_85Texture;
    private final Image T34_85Turret;
    private final Image PzKpfw_IVH_Texture;
    private final Image PzKpfw_IVH_Turret;
    private final Image PzKpfw_VIH_Tiger_Texture;
    private final Image PzKpfw_VIH_Tiger_Turret;
    private final Image tigerAmmo;
    private final Image cursor;
    private final Image menu;
    private final Image killed;
    private final Image miniMapBlock1;
    private final Image miniMapBlock2;
    private final Image miniMapEnemy;
    private final Image miniMapPlayer;
    private final Image miniMapBase;
    private final Image a1;
    private final Image a2;
    private final Image a3;
    private final Image a4;
    private final Image a5;
    private final Image a6;
    private final Image base;

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
        return switch (id) {
            case -8 -> miniMapBase;
            case -7 -> base;
            case -6 -> miniMapBlock2;
            case -5 -> miniMapPlayer;
            case -4 -> miniMapEnemy;
            case -3 -> miniMapBlock1;
            case -2 -> killed;
            case -1 -> menu;
            case 0 -> cursor;
            case 1 -> logo;
            case 2 -> A20Texture;
            case 3 -> A20Turret;
            case 4 -> a20Ammo;
            case 6 -> T34_85Texture;
            case 7 -> T34_85Turret;
            case 10 -> PzKpfw_IVH_Texture;
            case 11 -> PzKpfw_IVH_Turret;
            case 12 -> PzKpfw_VIH_Tiger_Texture;
            case 13 -> PzKpfw_VIH_Tiger_Turret;
            case 14 -> tigerAmmo;
            case 20 -> lvl1_bg;
            case 21 -> lvl1_block1;
            case 22 -> lvl1_block2;
            case 23 -> lvl1_miniMap;
            case 24 -> victory;
            case 25 -> lose;
            case 100 -> a1;
            case 101 -> a2;
            case 102 -> a3;
            case 103 -> a4;
            case 104 -> a5;
            case 105 -> a6;
            default -> null;
        };
    }

    public Image getImage(ImageEnum image){
        return switch (image) {
            case MiniMapBase -> miniMapBase;
            case Base -> base;
            case MiniMapBlock1 -> miniMapBlock1;
            case MiniMapBlock2 -> miniMapBlock2;
            case MiniMapPlayer -> miniMapPlayer;
            case MiniMapEnemy -> miniMapEnemy;
            case Killed -> killed;
            case Menu -> menu;
            case Cursor -> cursor;
            case Logo -> logo;
            case A20Texture -> A20Texture;
            case A20Turret -> A20Turret;
            case A20Ammo -> a20Ammo;
            case T34_85Texture -> T34_85Texture;
            case T34_85Turret -> T34_85Turret;
            case PzKpfw_IVH_Texture -> PzKpfw_IVH_Texture;
            case PzKpfw_IVH_Turret -> PzKpfw_IVH_Turret;
            case PzKpfw_VIH_Tiger_Texture -> PzKpfw_VIH_Tiger_Texture;
            case PzKpfw_VIH_Tiger_Turret -> PzKpfw_VIH_Tiger_Turret;
            case TigerAmmo -> tigerAmmo;
            case Lvl1_bg -> lvl1_bg;
            case Lvl1_block1 -> lvl1_block1;
            case Lvl1_block2 -> lvl1_block2;
            case Lvl1_miniMap -> lvl1_miniMap;
            case Victory -> victory;
            case Lose -> lose;
            case A1 -> a1;
            case A2 -> a2;
            case A3 -> a3;
            case A4 -> a4;
            case A5 -> a5;
            case A6 -> a6;
        };
    }
}

