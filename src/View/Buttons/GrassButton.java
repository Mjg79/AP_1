package View.Buttons;

import Model.MapAndCell.Map;
import Model.Products.Forage.Forage;
import View.Map.MapView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;


public class GrassButton extends GeneralButton {
    public static Button grassButton(Pane grassButtonPane,Pane backGroundPane,
                                     Scene mapScene, Map map, int x, int y, MapView mapView) {
        Button grass = new Button();
        grass.setText("");
        grass.relocate(x, y);
        grass.setOpacity(0);
        grassButtonPane.getChildren().add(grass);
        grass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (map.getWell().getCurrent() > 0) {
                    map.plantForage((x - 250) / 12, (y - 250) / 7, 300);
                    try {
                        for (int i = -1; i <= 1; i++)
                            for (int j = -1; j <= 1; j++)
                                new Forage(map.getFarmTime()).grassAnimation(
                                        backGroundPane, x + i * 12, y + j * 12).play();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return grass;
    }
}
