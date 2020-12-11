package Energy.presentationGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class NewRoundController extends Controller {

    //fxml variable
    @FXML
    private Label textNewYear;
    @FXML
    private Button exitGame;
    @FXML
    private Button newYear;

    //køres når rummet loades
    public void initialize() {
        textNewYear.setText(getDomainI().nextRoundText());

        //eventhandler for knappen der skifter scene afhængigt af om man har nået max antal runder
        newYear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean status = getDomainI().nextRound();
                Parent newRoot = null;
                if (status == true) {
                    try {
                        newRoot = runGui.getFxmlLoader().load(getClass().getResource("Outside.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        newRoot = runGui.getFxmlLoader().load(getClass().getResource("Exit.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                runGui.getStage().setScene(new Scene(newRoot));
                runGui.getStage().show();
            }
        });
        //eventhandler for knappen til at slutte spille, som loader exit skærmen
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent newRoot = null;
                try {
                    newRoot = runGui.getFxmlLoader().load(getClass().getResource("Exit.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runGui.getStage().setScene(new Scene(newRoot));
                runGui.getStage().show();
            }
        });
    }
}
