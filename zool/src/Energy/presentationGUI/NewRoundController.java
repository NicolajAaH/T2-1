package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
        textNewYear.setText(getDomainI().newRoundText());

        //eventhandler for knappen der skifter scene afhængigt af om man har nået max antal runder
        newYear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean status = getDomainI().newRound();
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
        exitGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
