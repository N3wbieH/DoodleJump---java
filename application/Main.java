package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import object.Move;
import object.Repaint;
import screen.FScreen;

public class Main extends Application {
    private Pane parent = new Pane();
    private Scene scene;

    @Override
    public void start(Stage arg0) throws Exception {
        Image image = new Image("res/background.png");
        ImageView imageView = new ImageView(image);
        parent.getChildren().add(imageView);
        // 添加背景图片

        FScreen presentScreen = new FScreen(500, 600);
        FScreen nextScreen = new FScreen(500, 600);
        presentScreen.setVisible(true);

        parent.getChildren().addAll(presentScreen, nextScreen);
        scene = new Scene(parent, 500, 600);

        Thread move = new Thread(new Move(presentScreen));
        move.setDaemon(true);
        move.start();

        Thread repaint = new Thread(new Repaint<>(presentScreen, nextScreen));
        repaint.setDaemon(true);
        repaint.start();

        arg0.setScene(scene);
        arg0.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
