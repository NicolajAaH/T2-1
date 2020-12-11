package Energy.presentationGUI;

import Energy.domain.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class StoreController extends Controller {
    //fxml variable
    @FXML
    private ListView<Item> storeList;
    @FXML
    private Button buyButton;
    @FXML
    private Label textStore;

    //initialize fra superklassen
    public void initialize() {
        super.initialize();
        //opretter en observableList af Items som bruges i listen ved store
        ObservableList<Item> items = FXCollections.observableArrayList(getDomainI().getStoreInventory().getItems());
        storeList.setItems(items);

        //eventhandler for køb knappen
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                int selectedItem = storeList.getSelectionModel().getSelectedIndex();
                if (selectedItem >= 0) {
                    textStore.setText(getDomainI().buyItem(selectedItem));
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
