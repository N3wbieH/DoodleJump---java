package object.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import object.FObject;
import screen.FScreen;

public class Player extends FObject {
    public static double jumpHeight = 200;
    private static Image[] images = { new Image("res/left_up.png"), new Image("res/right_up.png"),
            new Image("res/left_down.png"), new Image("res/right_down.png") };
    public double isUp = jumpHeight;
    private Image image = images[2];
    public boolean left = false;
    public boolean right = false;

    private enum Direction {
        Right, Left
    };

    private Direction direction = Direction.Left;

    public Player() {
        super();
    }

    public Player(double width, double height, double x, double y) {
        super(width, height, x, y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void jump() {
        if (isUp > 0)
            moveY(-1);
        else if (isUp<-28)
            moveY(1);
        isUp -= 1;
        if (getY() >= 600)
            setY(0);
        if (isUp > 0.85 * jumpHeight) {
            if (direction == Direction.Left) {
                if (image != images[2])
                    image = images[2];
            } else {
                if (image != images[3])
                    image = images[3];
            }
        } else {
            if (direction == Direction.Left) {
                if (image != images[0])
                    image = images[0];
            } else {
                if (image != images[1])
                    image = images[1];
            }
        }
    }

    public void moveLeft() {
        moveX(-1);
        if (getX() <= -60)
            setX(500);
        // 判断是否需要到最右边

    }

    public void moveRight() {
        moveX(1);
        if (getX() >= 480)
            setX(-60);
        // 判断是否需要到最左边
    }

    public void move() {
        if (left) {
            moveLeft();
            direction = Direction.Left;
        }
        if (right) {
            moveRight();
            direction = Direction.Right;
        }
        jump();

    }

    public void reJump() {
        if (this.isUp < 0) {
            this.isUp = jumpHeight;
        }
    }

    public boolean collisonByFoot(FObject baseObject) {
        // if (direction == Direction.Left) {
        // if (this.getY() + this.getHeight() == baseObject.getY()
        // && this.getX() + 0.7 * this.getWidth() > baseObject.getX()
        // && this.getX() + 0.3 * this.getWidth() < baseObject.getX() +
        // baseObject.getWidth())
        // return true;
        // } else {
        // if (this.getY() + this.getHeight() == baseObject.getY()
        // && this.getX() + 0.7 * this.getWidth() > baseObject.getX()
        // && this.getX() + 0.3 * this.getWidth() < baseObject.getX() +
        // baseObject.getWidth())
        // return true;
        // }
        if (this.getY() + this.getHeight() == baseObject.getY()
                && this.getX() + 0.7 * this.getWidth() > baseObject.getX()
                && this.getX() + 0.3 * this.getWidth() < baseObject.getX() + baseObject.getWidth())
            return true;
        return false;
    }

    public void collisionReaction() {
        for (int i = 0; i < FScreen.monster.size(); i++) {
            // if (player.)
            // 检测是否碰到怪物
            if (collisonByFoot(FScreen.monster.get(i)))
                reJump();
        }
        for (int i = 0; i < FScreen.platform.size(); i++) {
            if (collisonByFoot(FScreen.platform.get(i)))
                reJump();
        }
    }

}
