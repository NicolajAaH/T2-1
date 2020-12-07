package Energy.presentationGUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class BedroomController extends Controller{
    @FXML
    private Label bulbBedroom;
    @FXML
    private Label windowBedroom;
    @FXML
    private ImageView holeWallBedroom;

    //initialize køres i starten. Der kaldes controllers initialize. Hullet skal enten skjules eller vises.
    public void initialize(){
        super.initialize();
        bulbBedroom.setText(getDomainI().getRoomInventory().getItem(0).getName());
        windowBedroom.setText(getDomainI().getRoomInventory().getItem(1).getName());

        if(getDomainI().getRoomInventory().getItem(2).getName().equals("Hul i væggen")){
            holeWallBedroom.setImage(new Image("/Images/hole_in_wall.png"));
        }
        else{
            holeWallBedroom.setImage(null);
            holeWallBedroom.setVisible(false);
        }

        //eventhandlers, som skifter tingen ud, og opdaterer de forskellige ting.
        bulbBedroom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(0);
                if (name != null) {
                    bulbBedroom.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        windowBedroom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(1);
                if (name != null) {
                    windowBedroom.setText(name);
                    updateInventory();
                    updateStatus();
                }
            }
        });

        holeWallBedroom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = getDomainI().replaceDynamicGUI(2);
                if (name != null) {
                    holeWallBedroom.setImage(null);
                    holeWallBedroom.setVisible(false);
                    updateInventory();
                    updateStatus();
                }
            }
        });
    }
}
