package Energy.presentationGUI;

import Energy.domain.Item;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StoreController extends Controller{
    public void initialize(){
        super.initialize();
        ObservableList<Item> items = FXCollections.observableArrayList(getDomainI().getStoreInventory().getItems());
        storeList.setItems(items);
    }
}
