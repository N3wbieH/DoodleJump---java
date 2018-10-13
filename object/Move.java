package object;

import javafx.scene.input.KeyCode;
import object.player.Player;
import screen.FScreen;

public class Move implements Runnable {
    private FScreen presentScreen;
    private Player player;

    public Move(FScreen presentScreen) {
        super();
        this.presentScreen = presentScreen;
        player = FScreen.player;
    }

    public void initEvents() {
        presentScreen.getParent().getScene().setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.LEFT)
                player.left = true;
            if (event.getCode() == KeyCode.RIGHT)
                player.right = true;
        });

        presentScreen.getParent().getScene().setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT)
                player.left = false;
            if (event.getCode() == KeyCode.RIGHT)
                player.right = false;
        });
    }

    @Override
    public void run() {
        initEvents();
        while (true) {
            synchronized (player) {
                try {
                    player.collisionReaction();
                    player.move();
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
