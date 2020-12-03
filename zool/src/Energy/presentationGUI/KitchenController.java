package Energy.presentationGUI;

import Energy.domain.Game;
import Energy.domain.Inventory;
import Energy.domain.Item;
import Energy.domain.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class KitchenController extends Controller{
    @FXML
    private Label fridge_label;
    @FXML
    private Label oven_label;
    @FXML
    private Label dishwasher_label;

    public void initialize(){
        //getDomainI().getPlayerInventory().addItem(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE, "A+"));
        //getDomainI().getPlayerInventory().addItem(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE, "A+"));
        //getDomainI().getPlayerInventory().addItem(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE, "A+"));
        //getDomainI().getPlayerInventory().addItem(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE, "A+"));
        super.initialize();
    }
}
