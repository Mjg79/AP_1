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

public class CatButton extends GeneralButton {
    private static boolean isPaused = false;
    private static boolean isResumed = false;
    private static boolean isPlaying = true;
    private transient static ImageView buttonView;
    private transient static Scene mapScene;
    private transient static Button button = new Button("chick");
    public static Button catButton(Group mapGroup, Map map, ImageView buttonView, Scene mapScene)
            throws FileNotFoundException {
        button.setStyle("-fx-font-size: 16;");
        button.setOpacity(0);
        buttonView.setOpacity(0.9);
        mapGroup.getChildren().add(buttonView);
        buttonView.relocate(235, 10);
        button.relocate(238, 26);
        button.setShape(new Circle(10));
        mapGroup.getChildren().add(button);
        CatButton.buttonView = buttonView;
        CatButton.mapScene = mapScene;
        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if(isPlaying) {
                        if (map.getBudget() >= 2500) {
                            map.buyAnimal("cat");
                            map.getCats().get(map.getCats().size() - 1).catMoving(mapScene,
                                    mapGroup, false);
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
