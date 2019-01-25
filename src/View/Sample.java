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

public class Sample {
    public static void main(String[] args) throws IOException {
        Gson serializer = new Gson();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Home\\Desktop\\aliaqa.json"));
        Map map = new Map();
        serializer.toJson(map, Map.class, writer);
        writer.flush();
    }
}
