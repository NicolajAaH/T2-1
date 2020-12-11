package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ExitController extends Controller {
    //fxml variable
    @FXML
    private Button endProgram;
    @FXML
    private Label exitLabel;

    //initialize hvor der sættes teksten til labels og en eventhandler der afslutter spillet
    public void initialize() {
        exitLabel.setText(getDomainI().endGameText());

        endProgram.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
