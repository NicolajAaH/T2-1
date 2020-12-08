package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OutsideController extends Controller {
    //fxml variable
    @FXML
    private Label insulation;
    @FXML
    private ImageView sunCatcher;

    //initialize fra superklassen, og tjekker om solcellen skal være der.
    public void initialize() {
        super.initialize();

        if (getDomainI().getRoomInventory().getItem(0).getName().equals("Tag uden solceller")) {
            sunCatcher.setImage(null);
        } else {
            sunCatcher.setImage(new Image("/Images/solar_panels_addToBackground.png"));
            sunCatcher.setVisible(true);
        }
        insulation.setText(getDomainI().getRoomInventory().getItem(1).getName());

        //eventhandler for solcellen
        sunCatcher.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(0);
                if (name != null) {
                    sunCatcher.setImage(new Image("/Images/solar_panels_addToBackground.png"));
                    sunCatcher.setVisible(true);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        //eventhandler for isolering
        insulation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(1);
                if (name != null) {
                    insulation.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
