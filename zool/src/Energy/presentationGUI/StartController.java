package Energy.presentationGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartController extends Controller {
    //fxml variable
    @FXML
    private Button okButton;
    @FXML
    private TextField input;
    @FXML
    private Label welcomeText;
    @FXML
    private Label errorLabel;

    //initialize fra superklassen, og opdaterer labels på tingene når man kommer ind i rummet.
    public void initialize() {

        welcomeText.setText(getDomainI().welcomeText());

        //eventhandler for buttonclick, der sætter fxml til outside, da man derefter starter den nye runde
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (true) {
                    String error = getDomainI().setStartAmount(input.getText());
                    errorLabel.setText(error);
                    if (error == null) {
                        Parent newRoot = null;
                        try {
                            newRoot = runGui.getFxmlLoader().load(getClass().getResource("Outside.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runGui.getStage().setScene(new Scene(newRoot));
                        runGui.getStage().show();
                        break;
                    }
                    break;
                }
            }
        });
    }
}
