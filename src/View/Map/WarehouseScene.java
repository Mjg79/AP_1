package View.Map;

import Model.Places.WareHouse;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WarehouseScene {
    private WareHouse wareHouse;
    private Group warehouseRoot = new Group();
    private final Scene wareHouseScence = new Scene(warehouseRoot,1000,750);
    public WarehouseScene(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }
    public void changeToWarehouse(Stage primaryStage) {
        Image backGround = null;
        try {
            backGround = new Image(new FileInputStream(
                    "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\wareHousePlace.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView mapViewBackGround = new ImageView(backGround);
        mapViewBackGround.setFitWidth(1000);
        mapViewBackGround.setFitHeight(750);
        warehouseRoot.getChildren().add(mapViewBackGround);
        primaryStage.setScene(wareHouseScence);
    }


}
