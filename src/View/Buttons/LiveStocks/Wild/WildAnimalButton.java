package View.Buttons.Animals.Wild;

import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class WildAnimalButton {
    public static Button wildButton(Button wildButton, Scene scene, WildAnimal wildAnimal, Map map) {
        GeneralButton.buttonAppearanceWithCursor(wildButton, scene);
        wildButton.setOpacity(0);
        wildButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.cageWildAnimal(wildAnimal.getX(), wildAnimal.getY());
            }
        });
        return wildButton;
    }

    public static Button wildProductButton(Button productButton, ImageView view, ImageView imageView, Group group,
                                           Scene scene, WildAnimal wildAnimal, Map map, double farmTime){
        if (wildAnimal.isCaged())
            productButton.setText("product");
        int x = wildAnimal.getX();
        int y = wildAnimal.getY();
        productButton.setOpacity(0);
        GeneralButton.buttonAppearanceWithCursor(productButton, scene);
        productButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.pickUpAndPutInWareHouse(x, y);
                group.getChildren().remove(view);
                group.getChildren().remove(imageView);
                productButton.setVisible(false);
            }
        });

        return productButton;
    }

}
