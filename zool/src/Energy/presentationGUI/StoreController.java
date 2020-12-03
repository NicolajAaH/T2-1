package Energy.presentationGUI;

import Energy.domain.Item;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class StoreController extends Controller {
    @FXML
    ListView<Item> storeList;
    @FXML
    Button buyButton;
    @FXML
    Label textStore;

    public void initialize() {
        super.initialize();
        ObservableList<Item> items = FXCollections.observableArrayList(getDomainI().getStoreInventory().getItems());
        storeList.setItems(items);
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int selectedItem = storeList.getSelectionModel().getSelectedIndex()+1;
                textStore.setText(getDomainI().buyItem(selectedItem));
                updateInventory();
                updateStatus();
            }
        });
    }
}
