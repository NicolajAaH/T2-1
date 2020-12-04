package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UtilityController extends Controller{
    @FXML
    ImageView heatingSystem;
    @FXML
    Label washingMachineLabel;
    @FXML
    Label tumbleDryerLabel;
    public void initialize(){
        super.initialize();
        heatingSystem.setImage(findImage(getDomainI().getRoomInventory().getItem(2).getName()));
        tumbleDryerLabel.setText(getDomainI().getRoomInventory().getItem(1).getEnergyLabel());
        washingMachineLabel.setText(getDomainI().getRoomInventory().getItem(0).getEnergyLabel());
        System.out.println(getDomainI().getRoomInventory().printInventory());
        washingMachineLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(0);
                if(energyLabel != null){
                washingMachineLabel.setText(energyLabel);
                updateInventory();
                updateStatus();
            }}
        });
        tumbleDryerLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(1);
                if(energyLabel != null){
                tumbleDryerLabel.setText(energyLabel);
                updateInventory();
                updateStatus();}

            }
        });
        heatingSystem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(2);
                if(name != null){
                heatingSystem.setImage(findImage(name));
                updateInventory();
                updateStatus();
            }}
        });
    }


}
