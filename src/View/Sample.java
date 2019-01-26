package View;

import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import View.Map.MapView;
import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.HashMap;

public class Sample {
    public static void main(String[] args) throws IOException {
        Gson serializer = new Gson();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Home\\Desktop\\map6.json"));
        Map map = new Map();
        HashMap<String, Integer> missions = new HashMap<>();
        HashMap<String, Integer> gathers = new HashMap<>();
        missions.put("cake", 8);
        missions.put("flour", 12);
        missions.put("horn", 5);
        missions.put("feather", 20);
        gathers.put("cake", 0);
        gathers.put("flour", 0);
        gathers.put("horn", 0);
        gathers.put("feather", 0);
        map.setBudget(25000);
        map.setMissionNeeds(missions);
        map.setGatherElements(gathers);

        serializer.toJson(map, Map.class, writer);
        writer.flush();
    }
}
