package Energy.presentationGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BedroomController extends Controller{
    @FXML
    private Label bulbBedroom;
    @FXML
    private Label windowBedroom;
    //mangler hul i væggen

    public void initialize(){
        super.initialize();
        bulbBedroom.setText(getDomainI().getRoomInventory().getItem(0).getName());
        windowBedroom.setText(getDomainI().getRoomInventory().getItem(1).getName());
    }
}
