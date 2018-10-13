package object;

import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import object.player.Player;
import screen.FScreen;

public class Move1<V> extends Task<V> {

    private FScreen presentScreen;
    private Player player;

    public Move1(FScreen presentScreen) {
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
    protected V call() throws Exception {
        initEvents();
        while (true) {
            synchronized (player) {
                try {
                    player.collisionReaction();
                    player.move();
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
