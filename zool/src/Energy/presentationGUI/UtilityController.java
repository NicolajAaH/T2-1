package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UtilityController extends Controller{
    //FXML variable
    @FXML
    ImageView heatingSystem;
    @FXML
    Label washingMachineLabel;
    @FXML
    Label tumbleDryerLabel;

    //initialize fra super klassen og sætter labels
    public void initialize(){
        super.initialize();
        heatingSystem.setImage(findImage(getDomainI().getRoomInventory().getItem(2).getName()));
        tumbleDryerLabel.setText(getDomainI().getRoomInventory().getItem(1).getEnergyLabel());
        washingMachineLabel.setText(getDomainI().getRoomInventory().getItem(0).getEnergyLabel());

        //eventhandlers for mouseclick der skifter tingen ud
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
