package View.Buttons;

import Model.MapAndCell.Map;
import Model.Products.Forage.Forage;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProductButton {
    private static final String productFile = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\products\\";

    public static Button productButton(Group group, Scene scene, int x, int y, Map map, double farmTime) throws FileNotFoundException {
        Button productButton = new Button("");
        productButton.setOpacity(0);
        productButton.relocate(250 + x * 12 + 11,250 + y * 7 + 10);
        if (!map.getCells()[x][y].getProducts().isEmpty())
            group.getChildren().add(productButton);
        GeneralButton.buttonAppearanceWithCursor(productButton, scene);
        productButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!map.getCells()[x][y].getProducts().isEmpty()) {
                    map.pickUpAndPutInWareHouse(x, y);
                    map.getCells()[x][y].refreshImageViews(group);
                }
                group.getChildren().remove(productButton);

            }
        });

        return productButton;
    }
}
