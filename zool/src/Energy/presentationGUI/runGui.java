package Energy.presentationGUI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;
import Energy.domain.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class runGui extends Application{
        private static FXMLLoader fxmlLoader = new FXMLLoader();
        private static Stage stage;

        @Override
        public void start(Stage stage) throws Exception {
            this.stage = stage;
            Parent root = fxmlLoader.load(getClass().getResource("Start.fxml"));
            stage.setTitle("Energi Optimering");
            stage.setScene(new Scene(root));
            stage.show();
        }

        public static void main(String[] args) {

            launch(args);
        }

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public static Stage getStage() {
        return stage;
    }
}

