package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class BathroomController extends Controller{
    @FXML
    private Label shower;

    public void initialize(){
        super.initialize();
        shower.setText(getDomainI().getRoomInventory().getItem(0).getEnergyLabel());

        shower.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(0);
                if(energyLabel != null){
                    shower.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }}
        });
    }
}
