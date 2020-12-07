package Energy.presentationGUI;

import Energy.domain.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StoreController extends Controller {
    //fxml variable
    @FXML
    ListView<Item> storeList;
    @FXML
    Button buyButton;
    @FXML
    Label textStore;

    //initialize fra superklassen, og opdaterer labels på tingene når man kommer ind i rummet.
    public void initialize() {
        super.initialize();
        //opretter en observableList af Items som bruges i listen ved store
        ObservableList<Item> items = FXCollections.observableArrayList(getDomainI().getStoreInventory().getItems());
        storeList.setItems(items);

        //eventhandler for køb knappen
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
