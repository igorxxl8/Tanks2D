package Engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.TreeSet;

public class AnimationPlayer implements Runnable {
    Animation a;
    ScreenManager s;
    double x;
    double y;
    double angle;
    double time;

    public AnimationPlayer(Animation a, ScreenManager s, double x, double y, double angle, double time){
        this.a = a;
        this.s = s;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.time = time;
    }

    public synchronized void playAnimation() {
        long startingTime = System.currentTimeMillis();
        long cumTime = startingTime;

        while (cumTime - startingTime < time) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            a.update(timePassed);

            //draw and update screen
            Graphics2D g = s.getGraphics();
            Image cur = a.getImage();
            AffineTransform affine = new AffineTransform();
            affine.rotate((angle), x - cur.getWidth(null)/2, y);
            g.setTransform(affine);
            g.drawImage(cur, (int)x - cur.getWidth(null)/2, (int)y - cur.getHeight(null)/2, null);
            g.dispose();
            s.update();

            try {
                Thread.sleep(20);
            } catch (Exception ignored) {}
        }
        try{
            if(time == 361)
                Audio.playSound("resources/res/sounds/explod03.wav").join();
            else Audio.playSound("resources/res/sounds/explod02.wav").join();
        }catch (Exception e){}


    }

    @Override
    public void run() {
        try{
            playAnimation();
        }catch (Exception ignored){}
    }
}
