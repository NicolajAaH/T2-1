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

public class Start extends Controller{
    @FXML
    private Button okButton;
    @FXML
    private TextField input;
    @FXML
    private Label welcomeText;
    @FXML
    private Label errorLabel;

    public void initialize(){

        welcomeText.setText(getDomainI().welcomeText());

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while(true) {
                    String error = getDomainI().setStartAmountGUI(input.getText());
                    errorLabel.setText(error);
                    if(error == null){
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
