package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class RoomController extends Controller {
    //fxml variable
    @FXML
    Label roomBulb;
    @FXML
    Label roomWindow;

    //initialize fra superklassen, og opdaterer labels på tingene når man kommer ind i rummet.
    public void initialize() {
        super.initialize();
        roomBulb.setText(getDomainI().getRoomInventory().getItem(0).getName());
        roomWindow.setText(getDomainI().getRoomInventory().getItem(1).getName());

        //eventhandlers der skifter tingen ud
        roomBulb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(0);
                if (name != null) {
                    roomBulb.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        roomWindow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(1);
                if (name != null) {
                    roomWindow.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
