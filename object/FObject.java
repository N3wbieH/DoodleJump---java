package object;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

public abstract class FObject {
    protected DoubleProperty widthProperty = new SimpleDoubleProperty(0);
    protected DoubleProperty heightProperty = new SimpleDoubleProperty(0);
    protected DoubleProperty xProperty = new SimpleDoubleProperty(0);
    protected DoubleProperty yProperty = new SimpleDoubleProperty(0);
    protected BooleanProperty visibleProperty = new SimpleBooleanProperty(true);
    protected BooleanProperty updateProperty = new SimpleBooleanProperty(true);

    public FObject(double width, double height, double x,
            double y) {
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.widthProperty = new SimpleDoubleProperty(width);
        this.heightProperty = new SimpleDoubleProperty(height);
    }

    public FObject() {
        this.xProperty = new SimpleDoubleProperty(0);
        this.yProperty = new SimpleDoubleProperty(0);
        this.widthProperty = new SimpleDoubleProperty(0);
        this.heightProperty = new SimpleDoubleProperty(0);
    }

    public abstract void draw(GraphicsContext gc);

    public abstract void update();

    public DoubleProperty widthProperty() {
        return widthProperty;
    }

    public double getWidth() {
        return widthProperty.get();
    }

    public void setWidth(double width) {
        widthProperty.set(width);
    }

    public DoubleProperty heightProperty() {
        return heightProperty;
    }

    public double getHeight() {
        return heightProperty.get();
    }

    public void setHeight(double height) {
        heightProperty.set(height);
    }

    public DoubleProperty xProperty() {
        return xProperty;
    }

    public double getX() {
        return xProperty.get();
    }

    public void setX(double x) {
        xProperty.set(x);
    }

    public DoubleProperty yProperty() {
        return yProperty;
    }

    public double getY() {
        return yProperty.get();
    }

    public void setY(double y) {
        yProperty.set(y);
    }

    public BooleanProperty visibleProperty() {
        return visibleProperty;
    }

    public void setVisible(boolean isVisible) {
        this.visibleProperty.set(isVisible);
    }

    public boolean isVisible() {
        return visibleProperty.get();
    }

    public BooleanProperty updateProperty() {
        return updateProperty;
    }

    public void setUpdate(boolean isUpdate) {
        this.updateProperty.set(isUpdate);
    }

    public boolean isUpdate() {
        return updateProperty.get();
    }

    public void moveX(double x) {
        setX(getX() + x);
    }

    public void moveY(double y) {
        setY(getY() + y);
    }

    public boolean isCollisionWith(FObject baseObject) {
        if (baseObject == null)
            return false;
        if (getX() + 0.7*getWidth() > baseObject.getX() && getX() < baseObject.getX() + baseObject.getWidth()
                && getY() + getHeight() > baseObject.getY() && getY() <= baseObject.getY() + baseObject.getHeight()) {
            return true;
        }
        return false;
    }

}
