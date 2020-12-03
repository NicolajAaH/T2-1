package Energy.presentationGUI;

import Energy.domain.Game;
import Energy.domain.Inventory;
import Energy.domain.Item;
import Energy.domain.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class KitchenController extends Controller{
    @FXML
    private Label fridge_label;
    @FXML
    private Label oven_label;
    @FXML
    private Label dishwasher_label;

    public void initialize(){
        super.initialize();

        fridge_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(0);
                if(energyLabel != null){
                    fridge_label.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }}
        });

        oven_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(1);
                if(energyLabel != null){
                    oven_label.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }}
        });

        dishwasher_label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(2);
                if(energyLabel != null){
                    dishwasher_label.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }}
        });
    }
}
