package Energy.presentationGUI;

import Energy.domain.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
    private Game game = new Game();
    @FXML
    private ImageView item0;
    @FXML
    private ImageView item1;
    @FXML
    private ImageView item2;
    @FXML
    private ImageView item3;
    @FXML
    private ImageView item4;
    @FXML
    private Label fridge_label;
    @FXML
    private Label oven_label;
    @FXML
    private Label dishwasher_label;
    @FXML
    private Label printStatus;


    public Image findImage(int itemType) {
        if (itemType == game.WASHINGMACHINE) {
            return new Image("/Images/washing-machine_icon.png");
        }
        return null;
    }

    public void updateInventory() {
        /*if (game.getPlayer().getInventory().isEmpty() == false) {
            for (int i = 0; i < 5; i++) {
                if (game.getPlayer().getInventory().getItem(i) != null && game.getPlayer().getInventory().getSize() > i) {
                    items[i].setImage(findImage(game.getPlayer().getInventory().getItem(i).getItemType()));
                } else {
                    items[i].setImage(null);
                }
            }
        }*/
        if (game.getPlayer().getInventory().getSize() > 0 && game.getPlayer().getInventory().getItem(0) != null) {
            item0.setImage(findImage(game.getPlayer().getInventory().getItem(0).getItemType()));
        } else {
            item0.setImage(null);
        }

        if (game.getPlayer().getInventory().getSize() > 1 && game.getPlayer().getInventory().getItem(1) != null) {
            item1.setImage(findImage(game.getPlayer().getInventory().getItem(1).getItemType()));
        } else {
            item1.setImage(null);
        }

        if (game.getPlayer().getInventory().getSize() > 2 && game.getPlayer().getInventory().getItem(2) != null) {
            item2.setImage(findImage(game.getPlayer().getInventory().getItem(2).getItemType()));
        } else {
            item2.setImage(null);
        }

        if (game.getPlayer().getInventory().getSize() > 3 && game.getPlayer().getInventory().getItem(3) != null) {
            item3.setImage(findImage(game.getPlayer().getInventory().getItem(3).getItemType()));
        } else {
            item3.setImage(null);
        }

        if (game.getPlayer().getInventory().getSize() > 4 && game.getPlayer().getInventory().getItem(4) != null) {
            item4.setImage(findImage(game.getPlayer().getInventory().getItem(4).getItemType()));
        } else {
            item4.setImage(null);
        }
    }

    public Game getGame() {
        return game;
    }
}
