package Energy.presentationGUI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;
import Energy.domain.Item;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    private static DomainI domainI = new DomainConnect();

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
    private Label wallet;
    @FXML
    private Label savings;
    @FXML
    private ImageView arrowUp;
    @FXML
    private ImageView arrowDown;
    @FXML
    private ImageView arrowRight;
    @FXML
    private ImageView arrowLeft;
    @FXML
    private Button endYear;
    @FXML
    private Button endGame;
    @FXML
    ImageView heatingSystem;

    @FXML
    ListView<Item> storeList;
    @FXML
    Button buyButton;


    public void initialize(){
        showExits();
        updateInventory();
        updateStatus();
    }

    public Image findImage(int itemType) {
        if (itemType == WASHINGMACHINE) {
            return new Image("/Images/washing-machine_icon.png");
        }
        if (itemType == DRYER) {
            return new Image("/Images/tumble_dryer_icon.png");
        }
        if (itemType == HEATING) {
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

    public void updateStatus(){
        wallet.setText("Penge tilbage: " + getDomainI().getWallet());
        savings.setText("Årlig besparelse: " + getDomainI().getScore());
    }

    public DomainI getDomainI() {
        return domainI;
    }

    public void showExits(){
        arrowUp.setImage(new Image("/Images/north_arrow.png"));
        arrowDown.setImage(new Image("/Images/south_arrow.png"));
        arrowLeft.setImage(new Image("/Images/west_arrow.png"));
        arrowRight.setImage(new Image("/Images/east_arrow.png"));
        if(domainI.hasEastExit()){
            arrowRight.setVisible(true);
        }
        else{
            arrowRight.setVisible(false);
        }
        if(domainI.hasNorthExit()){
            arrowUp.setVisible(true);
        }
        else{
            arrowUp.setVisible(false);
        }
        if(domainI.hasSouthExit()){
            arrowDown.setVisible(true);
        }
        else{
            arrowDown.setVisible(false);
        }
        if(domainI.hasWestExit()){
            arrowLeft.setVisible(true);
        }
        else{
            arrowLeft.setVisible(false);
        }
    }

    public void goNorth() throws IOException {
        domainI.goNorth();
        String resource = getDomainI().getCurrentRoom() + ".fxml";
        runGui.getFxmlLoader().load(getClass().getResource(resource));
        Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
        runGui.getStage().setScene(new Scene(newRoot));
        runGui.getStage().show();
    }

    public void goSouth() throws IOException {
        domainI.goSouth();
        String resource = getDomainI().getCurrentRoom() + ".fxml";
        runGui.getFxmlLoader().load(getClass().getResource(resource));
        Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
        runGui.getStage().setScene(new Scene(newRoot));
        runGui.getStage().show();
    }
    public void goWest() throws IOException {
        domainI.goWest();
        String resource = getDomainI().getCurrentRoom() + ".fxml";
        runGui.getFxmlLoader().load(getClass().getResource(resource));
        Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
        runGui.getStage().setScene(new Scene(newRoot));
        runGui.getStage().show();
    }
    public void goEast() throws IOException {
        domainI.goEast();
        String resource = getDomainI().getCurrentRoom() + ".fxml";
        runGui.getFxmlLoader().load(getClass().getResource(resource));
        Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
        runGui.getStage().setScene(new Scene(newRoot));
        runGui.getStage().show();
    }

}
