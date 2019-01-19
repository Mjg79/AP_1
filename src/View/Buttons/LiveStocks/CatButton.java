package View.Buttons.LiveStocks;

import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CatButton extends GeneralButton {

    public static Button catButton(Group mapGroup, Map map) throws FileNotFoundException {
        Button button = new Button("chick");
        button.setStyle("-fx-font-size: 16;");
        button.setOpacity(0);
        ImageView buttonView = new ImageView();
        buttonView.setOpacity(0.9);
        mapGroup.getChildren().add(buttonView);
        buttonView.relocate(310, 10);
        button.relocate(313, 26);
        button.setShape(new Circle(10));
        mapGroup.getChildren().add(button);

        buyAnimalAppereance(button, buttonView);

        if (map.getBudget() >= 2500)
            buttonView.setImage(new Image(new FileInputStream(animalBuy + "catAfter.png")));
        else
            buttonView.setImage(new Image(new FileInputStream(animalBuy + "catBefore.png")));
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            //TODO: CAT BUYGIN
            }
        });
        return button;
    }

}
