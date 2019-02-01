package View.Buttons.LiveStocks;

import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import View.View;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;

public class OstrichButton extends GeneralButton {
    private static boolean isPlaying = true;
    private transient static ImageView buttonView;
    private transient static Scene mapScene;
    private static transient Button button = new Button("chick");
    private static transient Media ostrichMedia = new Media(View.class.getResource("farmFrenzySaveFiles/soundTracks/ostrichSound.WAV").toExternalForm());
    private static transient MediaPlayer ostrichMediaPlayer = new MediaPlayer(ostrichMedia);

    public static Button ostrichButton(Group mapGroup, Map map ,ImageView buttonView,  Scene mapScene)
            throws FileNotFoundException {
        button.setStyle("-fx-font-size: 16;");
        button.setOpacity(0);
        buttonView.setOpacity(0.9);
        if (!mapGroup.getChildren().contains(buttonView))
            mapGroup.getChildren().add(buttonView);
        buttonView.relocate(85, 10);
        button.relocate(98, 26);
        button.setShape(new Circle(10));
        if(!mapGroup.getChildren().contains(button))
            mapGroup.getChildren().add(button);
        OstrichButton.buttonView = buttonView;
        OstrichButton.mapScene = mapScene;
        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                        if (isPlaying && map.getBudget() >= 1000) {
                            map.buyAnimal("ostrich");
                            try {
                                map.getLiveStocks().get(map.getLiveStocks().size() - 1).liveStockMoving(mapScene,
                                        mapGroup, true, map.getFarmTime());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            ostrichMediaPlayer.stop();
                            ostrichMediaPlayer.play();
                        }
                }
        });
        return button;
    }
    public static void pause(){
        isPlaying = false;
        buyAnimalAppereanceDefault(button, buttonView);
        buttonAppearanceDefault(button, mapScene);
    }

    public static void resume(){
        isPlaying = true;
        buyAnimalAppereance(button, buttonView);
        buttonAppearanceWithCursor(button, mapScene);
    }
}
