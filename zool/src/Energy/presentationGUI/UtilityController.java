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
        heatingSystem.setImage(new Image("/Images/oliefyr.png"));
        washingMachineLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                washingMachineLabel.setText(getDomainI().replaceStaticGUI(0));
                updateInventory();
                updateStatus();
            }
        });
        tumbleDryerLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tumbleDryerLabel.setText(getDomainI().replaceStaticGUI(1));
                updateInventory();
                updateStatus();
            }
        });
        heatingSystem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getDomainI().replaceStaticGUI(3);
                //heatingSystem.setImage(findImage(""))
            }
        });
    }


}
