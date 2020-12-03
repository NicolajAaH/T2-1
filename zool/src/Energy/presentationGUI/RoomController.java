package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class RoomController extends Controller {
    @FXML
    Label roomBulb;
    @FXML
    Label roomWindow;

    public void initialize() {
        super.initialize();

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
