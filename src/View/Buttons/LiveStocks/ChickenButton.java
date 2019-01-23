package View.Buttons.LiveStocks;

import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;

public class ChickenButton extends GeneralButton {
    private static boolean isPaused = false;
    private static boolean isResumed = false;
    private static boolean isPlaying = true;
    private static ImageView buttonView;
    private static Scene mapScene;
    private static Button button = new Button("chick");
    public static Button chickenButton(Group mapGroup, Map map, ImageView buttonView, Scene mapScene)
            throws FileNotFoundException {
        button.setStyle("-fx-font-size: 16;");
        button.setOpacity(0);
        buttonView.setOpacity(0.9);
        mapGroup.getChildren().add(buttonView);
        buttonView.relocate(10, 10);
        button.relocate(13, 26);
        button.setShape(new Circle(10));
        mapGroup.getChildren().add(button);
        ChickenButton.buttonView = buttonView;
        ChickenButton.mapScene = mapScene;
        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if(isPlaying) {
                        if (map.getBudget() >= 100) {
                            map.buyAnimal("chicken");
                            map.getLiveStocks().get(map.getLiveStocks().size() - 1).chickenMoving(mapScene,
                                    mapGroup, true, map.getFarmTime());
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        return button;
    }

    public static void pause(){
        isPaused = true;
        isPlaying = false;
        buyAnimalAppereanceDefault(button, buttonView);
        buttonAppearanceDefault(button, mapScene);
    }

    public static void resume(){
        isResumed = true;
        isPlaying = true;
        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);
    }
}
