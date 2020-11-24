package Energy.presentationGUI;

import Energy.domain.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class runGui extends Application{
        private Game game;
        static KitchenController myControllerHandle;

        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("Kitchen.fxml"));
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kitchen.fxml"));
            //myControllerHandle = (KitchenController) fxmlLoader.getController();

            stage.setTitle("FXML Welcome");
            stage.setScene(new Scene(root));
            stage.show();
        }

        public static void main(String[] args) {

            launch(args);
        }

}

