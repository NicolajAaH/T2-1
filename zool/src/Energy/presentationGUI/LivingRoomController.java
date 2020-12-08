package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LivingRoomController extends Controller {
    //fxml variable
    @FXML
    Label tvLabel;
    @FXML
    Label windowLabelLiving;
    @FXML
    Label lightLiving;

    //initialize fra superklassen, og opdaterer labels på tingene når man kommer ind i rummet.
    public void initialize() {
        super.initialize();
        lightLiving.setText(getDomainI().getRoomInventory().getItem(1).getName());
        windowLabelLiving.setText(getDomainI().getRoomInventory().getItem(0).getName());
        tvLabel.setText(getDomainI().getRoomInventory().getItem(2).getEnergyLabel());

        //eventhandlers der replacer ting ved mouseclick
        windowLabelLiving.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(0);
                if (name != null) {
                    windowLabelLiving.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        lightLiving.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(1);
                if (name != null) {
                    lightLiving.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        tvLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(2);
                if (energyLabel != null) {
                    tvLabel.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
