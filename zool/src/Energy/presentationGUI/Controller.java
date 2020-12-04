package Energy.presentationGUI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;
import Energy.domain.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;

import java.io.IOException;

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


    public void initialize(){
        showExits();
        updateInventory();
        updateStatus();
        endGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    public Image findImage(String itemName) {
        if (itemName.equals("Opvaskemaskine A")) {
            return new Image("/Images/dishwasher_A_icon.png");
        }
        if (itemName.equals("Opvaskemaskine A++")) {
            return new Image("/Images/dishwasher_A++_icon.png");
        }
        if (itemName.equals("Vaskemaskine A+")) {
            return new Image("/Images/washing-machine_A+_icon.png");
        }
        if (itemName.equals("Vaskemaskine A++")) {
            return new Image("/Images/washing-machine_A++_icon.png");
        }
        if (itemName.equals("Vaskemaskine A+++")) {
            return new Image("/Images/washing-machine_A+++_icon.png");
        }
        if (itemName.equals("Tørretumbler A+")) {
            return new Image("/Images/tumble_dryer_A+_icon.png");
        }
        if (itemName.equals("Tørretumbler A++")) {
            return new Image("/Images/tumble_dryer_A++_icon.png");
        }
        if (itemName.equals("Tørretumbler A+++")) {
            return new Image("/Images/tumble_dryer_A+++_icon.png");
        }
        if (itemName.equals("Køleskab A+")) {
            return new Image("/Images/fridge_A+_icon.png");
        }
        if (itemName.equals("Køleskab A++")) {
            return new Image("/Images/fridge_A++_icon.png");
        }
        if (itemName.equals("Køleskab A+++")) {
            return new Image("/Images/fridge_A+++_icon.png");
        }
        if (itemName.equals("Komfur A+")) {
            return new Image("/Images/cooker_A+_icon.png");
        }
        if (itemName.equals("TV A+")) {
            return new Image("/Images/TV_A+_icon.png");
        }
        if (itemName.equals("Termorude (2 lag)")) {
            return new Image("/Images/window_icon.png");
        }
        if (itemName.equals("Sparepære")) {
            return new Image("/Images/Spare_bulb_icon.png");
        }
        if (itemName.equals("LED-pære")) {
            return new Image("/Images/LED_bulb_icon.png");
        }
        if (itemName.equals("Pillefyr (varmeanlæg)")) {
            return new Image("/Images/pellet_heater_icon.png");
        }
        if (itemName.equals("Gas (varmeanlæg)")) {
            return new Image("/Images/gasboiler_icon.png");
        }
        if (itemName.equals("Hul-fikser-kit")) {
            return new Image("/Images/wallfixer-kit_icon.png");
        }
        if (itemName.equals("Isolering")) {
            return new Image("/Images/insulation_icon.png");
        }
        if (itemName.equals("Solceller")) {
            return new Image("/Images/solar_panels_icon.png");
        }
        if (itemName.equals("Bruser A+")) {
            return new Image("/Images/showerhead_icon.png");
        }
        return null;
    }

    public void updateInventory() {
        if (domainI.getPlayerInventory().getSize() > 0 && domainI.getPlayerInventory().getItem(0) != null) {
            item0.setImage(findImage(domainI.getPlayerInventory().getItem(0).getName()));
        } else {
            item0.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 1 && domainI.getPlayerInventory().getItem(1) != null) {
            item1.setImage(findImage(domainI.getPlayerInventory().getItem(1).getName()));
        } else {
            item1.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 2 && domainI.getPlayerInventory().getItem(2) != null) {
            item2.setImage(findImage(domainI.getPlayerInventory().getItem(2).getName()));
        } else {
            item2.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 3 && domainI.getPlayerInventory().getItem(3) != null) {
            item3.setImage(findImage(domainI.getPlayerInventory().getItem(3).getName()));
        } else {
            item3.setImage(null);
        }

        if (domainI.getPlayerInventory().getSize() > 4 && domainI.getPlayerInventory().getItem(4) != null) {
            item4.setImage(findImage(domainI.getPlayerInventory().getItem(4).getName()));
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
    public String replace(int index){
        return getDomainI().replaceStaticGUI(index);
    }
}
