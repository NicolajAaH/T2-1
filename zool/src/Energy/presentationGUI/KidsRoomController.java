package Energy.presentationGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KidsRoomController extends Controller{
    @FXML
    private Label bulbKids;
    @FXML
    private Label windowKids;
    //mangler hul i væggen

    public void initialize(){
        super.initialize();
        bulbKids.setText(getDomainI().getRoomInventory().getItem(0).getName());
        windowKids.setText(getDomainI().getRoomInventory().getItem(1).getName());

        
    }
}
