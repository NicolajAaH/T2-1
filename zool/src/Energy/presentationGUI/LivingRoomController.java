package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LivingRoomController extends Controller{
    @FXML
    Label tvLabel;
    @FXML
    Label windowLabelLiving;
    @FXML
    Label lightLiving;

    public void initialize(){
        super.initialize();
        tvLabel.setText("DER SKAL BRUGES EN METODE");

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
                if(energyLabel != null){
                    tvLabel.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }}
        });
    }
}
