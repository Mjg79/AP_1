package View.ScoreBoard;

import Controller.Profile;
import com.google.gson.Gson;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ScoreBoardScene {
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\" ;
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private Group group = new Group();
    private Scene scene = new Scene(group,1000,750);
    private Stage primaryStage;
    private Scene backScene;
    private ImageView backGround;
    private HashMap<String,Integer> profiles = new HashMap<>();


    public ScoreBoardScene(Stage primaryStage, Scene backScene) {
        this.primaryStage = primaryStage;
        this.backScene = backScene;
        try {
            backGround = new ImageView(new Image(new FileInputStream(
                    FARMFRENZYSAVEFILES+"farmFrenzyScenesDesign\\mapBackGround.png")));
            group.getChildren().add(backGround);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Rectangle rectangle =  new Rectangle(600,650, Color.YELLOW);
        rectangle.relocate(200,50);
        group.getChildren().add(rectangle);
    }

    private void findProfiles() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiPlayer\\players.json");
        Scanner scanner = new Scanner(inputStream);
        Profile profile;
        Gson deserializer = new Gson();
        while (scanner.hasNextLine()){
            String sentence = scanner.nextLine();
            profile = deserializer.fromJson(sentence, Profile.class);
            profiles.put(profile.getUserName(),profile.getBudget());
        }
        HashMap<String, Integer> sorted = profiles
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        int temp = 0;
        for (String name:sorted.keySet()){
            Label label = new Label(name);
            label.relocate(210,60+temp*10);
            group.getChildren().add(label);
            temp++;
        }


    }

    public void setScene(){
        group.getChildren().remove(2,group.getChildren().size());
        try {
            findProfiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(scene);
    }


}
