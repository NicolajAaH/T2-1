package Energy.presentationGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UtilityController extends Controller{
    @FXML
    ImageView heatingSystem;
    @FXML
    Label washingMachineLabel;
    @FXML
    Label tumbleDryerLabel;
    public void initialize(){
        super.initialize();
        heatingSystem.setImage(new Image("/Images/oliefyr.png"));
    }


}
