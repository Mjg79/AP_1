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
     private static boolean isPaused = false;
     private static boolean isResumed = false;
     private static boolean isPlaying = true;
     private static Scene  mapScene;
     private static Button well = new Button("    well  ");
    public static Button wellButton(Group mapGroup, Scene mapScene, Map map) {
        WellButton.mapScene = mapScene;
        well.setOpacity(0);
        buttonAppearanceWithCursor(well, mapScene);
        well.relocate(462, 152);
        mapGroup.getChildren().add(well);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println(map.getWell().getCurrent());
                if (map.getWell().getCurrent() == 0)
                    well.setVisible(true);
                else
                    well.setVisible(false);
            }
        };
        timer.start();

        well.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    map.chargeWell();
                    try {
                        if(isPlaying) {
                            Animation animation = map.getWell().wellAnimation(true,
                                    map.getWell().getLevel(), mapGroup);
                            map.getWell().charge();
                            animation.setCycleCount(5);
                            AnimationTimer animationTimer = new AnimationTimer() {
                                int criteria = 0;

                                @Override
                                public void handle(long now) {
                                    if (isPaused) {
                                        animation.pause();
                                        isPaused = false;
                                    }
                                    if (isResumed) {
                                        animation.play();
                                        isResumed = false;
                                    }
                                    if (isPlaying)
                                        criteria++;
                                    well.setVisible(false);
                                    System.out.println(criteria);
                                    if (criteria == 350)
                                        this.stop();
                                }
                            };
                            animationTimer.start();
                            animation.play();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ;
            }
        });

        return well;
    }

    private Button refreshWellButton(Group mapGroup, Scene mapScene, Map map) {
        return wellButton(mapGroup, mapScene, map);
    }

     public static void pause(){
         isPaused = true;
         isPlaying = false;
         GeneralButton.buttonAppearanceDefault(well, mapScene);
     }

     public static void resume(){
         isResumed = true;
         isPlaying = true;
         GeneralButton.buttonAppearanceWithCursor(well, mapScene);
     }
}
