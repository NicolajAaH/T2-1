package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class BathroomController extends Controller {
    //forbinder variable med FXML
    @FXML
    private Label shower;

    //initialize køres i starten.
    public void initialize() {
        super.initialize(); //kalder controllers initialize
        //sætter teksten til energimærket på tingen.
        shower.setText(getDomainI().getRoomInventory().getItem(0).getEnergyLabel());

        //eventhandler, som skifter tingen ud, og opdaterer de forskellige ting.
        shower.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String energyLabel = getDomainI().replaceStaticGUI(0);
                if (energyLabel != null) {
                    shower.setText(energyLabel);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
