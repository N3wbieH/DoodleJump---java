package object;

import javafx.application.Platform;
import javafx.concurrent.Task;
import screen.FScreen;

public class Repaint<V> extends Task<V> {
    FScreen presentScreen;
    FScreen nextScreen;

    public Repaint(FScreen presentScreen, FScreen nextScreen) {
        this.presentScreen = presentScreen;
        this.nextScreen = nextScreen;
    }

    public FScreen exchangeScreen(FScreen... screens) {
        screens[0].setVisible(true);
        screens[1].setVisible(false);
        return screens[0];
    }

    @Override
    protected V call() throws Exception {
        nextScreen.initScreen();

        while (true) {
            Platform.runLater(() -> {
                nextScreen.draw();
                presentScreen = exchangeScreen(nextScreen, nextScreen = presentScreen);
            });
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("ERROR!");
            }
        }
    }

}
