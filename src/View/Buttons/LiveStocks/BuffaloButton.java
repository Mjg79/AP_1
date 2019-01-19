package View.Buttons.LiveStocks;

import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BuffaloButton extends GeneralButton {

    public static Button buffaloButton(Group mapGroup, Map map, ImageView buttonView, Scene mapScene) throws FileNotFoundException {
        Button button = new Button("chick");
        button.setStyle("-fx-font-size: 16;");
        button.setOpacity(0);
        buttonView.setOpacity(0.9);
        mapGroup.getChildren().add(buttonView);
        buttonView.relocate(160, 10);
        button.relocate(163, 26);
        button.setShape(new Circle(10));
        mapGroup.getChildren().add(button);

        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
             //TODO:BUY BUFFALO
            }
        });
        return button;
    }

}