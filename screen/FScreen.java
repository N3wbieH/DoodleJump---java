package screen;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import object.FObject;
import object.enemy.Enemy;
import object.platform.BluePlatform;
import object.platform.GreenPlatform;
import object.platform.RedPlatform;
import object.player.Player;
import object.prop.JetPack;
import object.prop.Propeller;
import object.prop.Spring;
import sun.security.provider.VerificationProvider;

public class FScreen extends Canvas {

    public volatile static Player player = new Player(93, 90, 250, 300);
    public volatile static List<FObject> platform = new ArrayList<>();
    private volatile static List<FObject> prop = new ArrayList<>();
    public volatile static List<FObject> monster = new ArrayList<>();
    public volatile static List<FObject> bluePlatforms = new ArrayList<>();

    public FScreen(double width, double height) {
        super(width, height);
        this.setVisible(false);
    }

    public void initScreen() {
        player.setX(150);
        player.setY(490);
        platform.add(new GreenPlatform(100, 550));
        double presentY;
        double presentX;
        FObject lastPlatform;
        do {
            lastPlatform = platform.get(platform.size() - 1);
            presentY = lastPlatform.getY();
            presentX = lastPlatform.getX();
            double x = Math.random() * 400 + presentX + 130;
            if (x < 380)
                platform.add(new GreenPlatform(x, presentY));
            else
                platform.add(new GreenPlatform(x - presentX - 130, presentY - 80));
        } while (presentY >= 10);

    }

    public void moveElement() {
        double height = Player.jumpHeight - player.isUp;
        for (int i = 0; i < platform.size(); i++) {
            FObject object = platform.get(i);
            object.setY(object.getY() + height);
            if (object.getY() > 600)
                platform.remove(object);
            i--;
        }
        for (int i = 0; i < prop.size(); i++) {
            FObject object = prop.get(i);
            object.setY(object.getY() + height);
            if (object.getY() > 600)
                prop.remove(object);
            i--;
        }
    }

    public void generateElement() {
        double height = platform.get(platform.size() - 1).getY();
        platform.add(new GreenPlatform(Math.random() * 400, height - 30));
        FObject lastPlatform;
        double presentX;
        double presentY;
        double x;
        for (int times = 0; times < 2;) {
            lastPlatform = platform.get(platform.size() - 1);
            presentY = lastPlatform.getY();
            presentX = lastPlatform.getX();
            double kind = Math.random();
            if (kind < 0.2) {
                x = Math.random() * 420;
                monster.add(new Enemy(x, presentY - 30));
                times++;
            } else {
                if (kind < 0.4) {
                    x = Math.random() * 40 + presentX;
                    if (kind < 0.3)
                        prop.add(new Spring(x, presentY));
                    else if (kind < 0.37)
                        prop.add(new Propeller(x, presentY));
                    else
                        prop.add(new JetPack(x, presentY));
                }
                x = Math.random() * 400 + presentX + 120;
                if (x > 380) {
                    presentY -= 30;
                    x -= presentX + 120;
                }
                if (kind < 0.7)
                    platform.add(new GreenPlatform(x, presentY));
                else if (kind < 0.8) {
                    platform.add(new BluePlatform(x, presentY));
                    bluePlatforms.add(new BluePlatform(x, presentY));
                } else
                    platform.add(new RedPlatform(x, presentY));
                times++;
            }

        }
        moveElement();
    }

    public void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < platform.size(); i++)
            platform.get(i).draw(gc);
        for (int i = 0; i < prop.size(); i++)
            prop.get(i).draw(gc);
        player.draw(gc);
    }
}
