package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class KitchenController extends Controller{
    //fxml variable
    @FXML
    private Label fridge_label;
    @FXML
    private Label oven_label;
    @FXML
    private Label dishwasher_label;

    //initialize fra superklassen, og opdaterer labels på tingene når man kommer ind i rummet.
    public void initialize(){
        super.initialize();
        fridge_label.setText(getDomainI().getRoomInventory().getItem(0).getEnergyLabel());
        oven_label.setText(getDomainI().getRoomInventory().getItem(1).getEnergyLabel());
        dishwasher_label.setText(getDomainI().getRoomInventory().getItem(2).getEnergyLabel());

        //eventhandlers som replacer
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
