package Energy.presentationGUI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;
import Energy.domain.Game;
import Energy.domain.Inventory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
    public final int WASHINGMACHINE = 1;
    public final int DRYER = 2;
    public final int HEATING = 3;
    public final int STOVE = 4;
    public final int FRIDGE = 5;
    public final int DISHWASHER = 6;
    public final int WINDOW = 7;
    public final int LIGHTS = 8;
    public final int TV = 9;
    public final int WALLFIXER = 10;
    public final int ISOLATION = 11;
    public final int SOLARCELLS = 12;
    public final int BATH = 13;

    private DomainI domainI = new DomainConnect();

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
    @FXML
    private ImageView arrowNorth;
    @FXML
    private ImageView arrowSouth;
    @FXML
    private ImageView arrowEast;
    @FXML
    private ImageView arrowRight;
    @FXML
    private Button endYear;
    @FXML
    private Button endGame;


    public Image findImage(int itemType) {
        if (itemType == WASHINGMACHINE) {
            return new Image("/Images/washing-machine_icon.png");
        }
        return null;
    }

    public void updateInventory() {
        if (domainI.getPlayerInventory().getSize() > 0 && domainI.getPlayerInventory().getItem(0) != null) {
            item0.setImage(findImage(domainI.getPlayerInventory().getItem(0).getItemType()));
        } else {
            item0.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 1 && domainI.getPlayerInventory().getItem(1) != null) {
            item1.setImage(findImage(domainI.getPlayerInventory().getItem(1).getItemType()));
        } else {
            item1.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 2 && domainI.getPlayerInventory().getItem(2) != null) {
            item2.setImage(findImage(domainI.getPlayerInventory().getItem(2).getItemType()));
        } else {
            item2.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 3 && domainI.getPlayerInventory().getItem(3) != null) {
            item3.setImage(findImage(domainI.getPlayerInventory().getItem(3).getItemType()));
        } else {
            item3.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 4 && domainI.getPlayerInventory().getItem(4) != null) {
            item4.setImage(findImage(domainI.getPlayerInventory().getItem(4).getItemType()));
        } else {
            item4.setImage(null);
        }
    }

    public DomainI getDomainI() {
        return domainI;
    }

    public void showExits(){
        if(domainI.hasEastExit()){
            arrowEast.setImage(new Image("/Images/east_arrow.png"));
        }
        if(domainI.hasNorthExit()){
            arrowEast.setImage(new Image("/Images/north_arrow.png"));
        }
        if(domainI.hasSouthExit()){
            arrowEast.setImage(new Image("/Images/south_arrow.png"));
        }
        if(domainI.hasWestExit()){
            arrowEast.setImage(new Image("/Images/west_arrow.png"));
        }
    }
}
