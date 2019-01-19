package View.Buttons.WorkShop;

import Model.MapAndCell.Map;
import View.Buttons.GeneralButton;
import View.Services.WorkShops.EggPowderPlant;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

public class EggPowderPlantButton extends GeneralButton {
    public static Button workShopButton(Group mapGroup, Scene mapScene, int x, int y, Map map) {
        Button workshop = new Button(" workshop ");
        workshop.relocate(x, y);
        GeneralButton.buttonAppearanceWithCursor(workshop, mapScene);
        mapGroup.getChildren().add(workshop);
        workshop.setOpacity(0);
        workshop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Animation animation = EggPowderPlant.eggPowderPlantAnimation(true,
                             mapGroup, 1);
                    animation.setCycleCount(5);
                    AnimationTimer timer = new AnimationTimer() {
                        int criteria = 0;

                        @Override
                        public void handle(long now) {
                            criteria++;
                            workshop.setVisible(false);
                            System.out.println(criteria);
                            if (criteria == 370) {
                                workshop.setVisible(true);
                                this.stop();
                            }
                        }
                    };
                    timer.start();
                    animation.play();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        return workshop;
    }
}
