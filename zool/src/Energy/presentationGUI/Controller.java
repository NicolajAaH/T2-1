package Energy.presentationGUI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Controller {

    //vores 'spil' og vores bindeled mellem domain og interface
    private static DomainI domainI = new DomainConnect();

    //getter til domainI
    public DomainI getDomainI() {
        return domainI;
    }

    //Variablene fra FXML filerne
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
    @FXML
    private Label movesLeft;

    //køres når der skiftes fxml. Eventhandlers til at afslutte spillet, som loader en anden FXML fil.
    public void initialize() {
        showExits();
        updateInventory();
        updateStatus();
        endGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent newRoot = null;
                try {
                    newRoot = runGui.getFxmlLoader().load(getClass().getResource("Exit.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runGui.getStage().setScene(new Scene(newRoot));
                runGui.getStage().show();
            }
        });

        endYear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent newRoot = null;
                try {
                    newRoot = runGui.getFxmlLoader().load(getClass().getResource("NewRound.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runGui.getStage().setScene(new Scene(newRoot));
                runGui.getStage().show();
            }
        });
    }

    //metode til at finde det rigtige icon ved items i inventory.
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
        if (itemName.equals("Oliefyr")) {
            return new Image("/Images/oliefyr.png");
        }
        return null;
    }

    //opdaterer alle pladsene i inventory. Den tjekker først om der er noget i inventory.
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

    //opdaterer penge tilbage og årlig besparelse på skærmen
    public void updateStatus() {
        wallet.setText("Penge tilbage: " + getDomainI().getWallet());
        savings.setText("Årlig besparelse: " + getDomainI().getScore());
        movesLeft.setText("Bevægelser tilbage: " + domainI.getMovesRemaing());
        AffordMore();
    }

    //giver pilene forskellige billeder, og skjuler eller viser dem i hvert rum, afhængig af om der er udveje.
    public void showExits() {
        arrowUp.setImage(new Image("/Images/north_arrow.png"));
        arrowDown.setImage(new Image("/Images/south_arrow.png"));
        arrowLeft.setImage(new Image("/Images/west_arrow.png"));
        arrowRight.setImage(new Image("/Images/east_arrow.png"));
        if (domainI.hasEastExit()) {
            arrowRight.setVisible(true);
        } else {
            arrowRight.setVisible(false);
        }
        if (domainI.hasNorthExit()) {
            arrowUp.setVisible(true);
        } else {
            arrowUp.setVisible(false);
        }
        if (domainI.hasSouthExit()) {
            arrowDown.setVisible(true);
        } else {
            arrowDown.setVisible(false);
        }
        if (domainI.hasWestExit()) {
            arrowLeft.setVisible(true);
        } else {
            arrowLeft.setVisible(false);
        }
    }

    //tilføjer et move, og hvis max antal moves er nået, skal der loades fxmlfilen "NewRound"
    public boolean moves() {
        if (!domainI.addMove()) {
            Parent newRoot = null;
            try {
                newRoot = runGui.getFxmlLoader().load(getClass().getResource("NewRound.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
            return false;
        } else {
            return true;
        }
    }

    //når man klikker på nord pilen, skal den loade den fxml fil der er i næste rum, osv. ved de andre.
    public void goNorth() throws IOException {
        domainI.goNorth();
        if (moves()) {
            String resource = domainI.getCurrentRoom() + ".fxml";
            Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
        }
    }

    public void goSouth() throws IOException {
        domainI.goSouth();
        if (moves()) {
            String resource = domainI.getCurrentRoom() + ".fxml";
            Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
        }
    }

    public void goWest() throws IOException {
        domainI.goWest();
        if (moves()) {
            String resource = domainI.getCurrentRoom() + ".fxml";
            Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
        }
    }

    public void goEast() throws IOException {
        domainI.goEast();
        if (moves()) {
            String resource = domainI.getCurrentRoom() + ".fxml";
            Parent newRoot = runGui.getFxmlLoader().load(getClass().getResource(resource));
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
        }
    }

    public void AffordMore() {
        if (!domainI.canAffordMore()) {
            Parent newRoot = null;
            try {
                newRoot = runGui.getFxmlLoader().load(getClass().getResource("NewRound.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            runGui.getStage().setScene(new Scene(newRoot));
            runGui.getStage().show();
        }
    }
}
