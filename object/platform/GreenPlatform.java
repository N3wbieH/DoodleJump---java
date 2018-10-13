package object.platform;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import object.FObject;

public class GreenPlatform extends FObject {
    private Image image = new Image("res/green.png");
    private static double width = 100;
    private static double height = 30;
    
    public GreenPlatform() {
        super();
    }

    public GreenPlatform(double x, double y) {
        super(width, height, x, y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY(),this.getWidth(),this.getHeight());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
