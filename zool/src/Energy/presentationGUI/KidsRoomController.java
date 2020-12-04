package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class KidsRoomController extends Controller{
    @FXML
    private Label bulbKids;
    @FXML
    private Label windowKids;
    @FXML
    private ImageView wallKidsroom;

    public void initialize(){
        super.initialize();
        bulbKids.setText(getDomainI().getRoomInventory().getItem(0).getName());
        windowKids.setText(getDomainI().getRoomInventory().getItem(1).getName());
        if(getDomainI().getRoomInventory().getItem(2).getName().equals("Hul i væggen")){
            wallKidsroom.setImage(new Image("/Images/hole_in_wall.png"));
        }
        else{
            wallKidsroom.setImage(null);
            wallKidsroom.setVisible(false);
        }


        bulbKids.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(0);
                if (name != null) {
                    bulbKids.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        windowKids.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(1);
                if (name != null) {
                    windowKids.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        wallKidsroom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(2);
                if (name != null) {
                    wallKidsroom.setImage(null);
                    wallKidsroom.setVisible(false);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
