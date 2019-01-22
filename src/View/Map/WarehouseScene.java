package View.Map;

import Model.Places.WareHouse;
import View.Buttons.GeneralButton;
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
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\" ;
    private WareHouse wareHouse;
    private Stage primaryStage;
    private Group warehouseRoot = new Group();
    private final Scene wareHouseScence = new Scene(warehouseRoot,1000,750);
    private Scene backScene;
    public WarehouseScene(WareHouse wareHouse,Scene backScene) throws FileNotFoundException {
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
        Button exit = new Button("exit");
        exit.relocate(565,686);
        exit.setScaleX(4.3);
        exit.setScaleY(2);
        exit.setOpacity(0);
        GeneralButton.buttonAppearanceWithCursor(exit,wareHouseScence);
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(backScene);
            }
        });
        ImageView cancel = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "cancelButton.png")));
        cancel.relocate(500,670);
        warehouseRoot.getChildren().add(cancel);
        warehouseRoot.getChildren().add(exit);

    }
    public void changeToWarehouse(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(wareHouseScence);

    }


}
