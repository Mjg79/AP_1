package View.MissionNeed;

import Model.MapAndCell.Map;
import View.View;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MissionNeeds {
    private static final String PRODUCTS = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private transient ArrayList<Label> needs = new ArrayList<>();
    private transient static ArrayList<Label> gathers = new ArrayList<>();

    private static void showMissionNeeds(Group mapGroup, Map map) throws FileNotFoundException {
        int number = 0;
      for (String mission: map.getMissionNeeds().keySet()) {
          Line line = new Line(800 + number * 50, 680, 835 + number * 50, 680);
          line.setOpacity(0.6);
          mapGroup.getChildren().add(line);
          ImageView imageView = new ImageView();
          Label needNumber = new Label();
          imageView.setImage(new Image(new FileInputStream(PRODUCTS + mission + ".png")));
          imageView.relocate(792 + number * 50, 624);
          imageView.setScaleX(0.8);
          imageView.setScaleY(0.8);
          needNumber.setText(Integer.toString(map.getMissionNeeds().get(mission)));
          needNumber.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: BOLD;");;
          needNumber.relocate(808 + number * 50, 679);
          mapGroup.getChildren().add(needNumber);
          mapGroup.getChildren().add(imageView);
          number++;
      }

    }

    private static void numberOfNeeds(Group group, Map map) {
        int number = 0;
        for (String mission: map.getMissionNeeds().keySet()) {
            Label gather = new Label("0");
            gather.relocate(808 + number * 50, 664);
            group.getChildren().add(gather);
            gather.setStyle("-fx-text-fill: #ffdb0b; -fx-font-weight: BOLD");
            gathers.add(gather);
            number++;
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int number = 0;
                for (String mission: map.getGatherElements().keySet()) {
                    gathers.get(number).setText(Integer.toString(map.getGatherElements().get(mission)));
                    number++;
                }

            }
        };
        timer.start();
    }

    public static void showMissionAndGathers(Group mapGroup, Map map) throws FileNotFoundException {
        showMissionNeeds(mapGroup, map);
        numberOfNeeds(mapGroup, map );
    }


}
