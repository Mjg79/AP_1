package View.Map;

import Model.Places.WareHouse;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WarehouseScene {
    private WareHouse wareHouse;
    private Stage primaryStage;
    private Group warehouseRoot = new Group();
    private final Scene wareHouseScence = new Scene(warehouseRoot,1000,750);
    private Scene backScene;
    public WarehouseScene(WareHouse wareHouse,Scene backScene) {
        this.wareHouse = wareHouse;
        this.backScene = backScene;
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

    }
    public void changeToWarehouse(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(wareHouseScence);
        Button exit = new Button("exit");
        exit.relocate(20,20);
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(backScene);

            }
        });
        warehouseRoot.getChildren().add(exit);
    }


}
