package View.Buttons;

import Model.MapAndCell.Map;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

 public class WellButton extends GeneralButton {
    public static Button wellButton(Group mapGroup, Scene mapScene, Map map) {
        Button well = new Button("    well  ");
        well.setOpacity(0);
        buttonAppearanceWithCursor(well, mapScene);
        well.relocate(462, 152);
        mapGroup.getChildren().add(well);
        well.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (map.getWell().getCurrent() == 0) {
                    well.setVisible(true);
                    map.chargeWell();
                    try {
                        Animation animation = map.getWell().wellAnimation(true,
                                map.getWell().getLevel(), mapGroup);
                        map.getWell().charge();
                        animation.setCycleCount(5);
                        AnimationTimer animationTimer = new AnimationTimer() {
                            int criteria = 0;
                            @Override
                            public void handle(long now) {
                                criteria++;
                                well.setVisible(false);
                                System.out.println(criteria);
                                if (criteria == 350)
                                    this.stop();
                            }
                        };
                        animationTimer.start();
                        animation.play();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ;
                } else
                    well.setVisible(false);
            }
        });

        return well;
    }

    private Button refreshWellButton(Group mapGroup, Scene mapScene, Map map) {
        return wellButton(mapGroup, mapScene, map);
    }
}
