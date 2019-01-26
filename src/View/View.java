package View;

import Controller.Controller;
import Model.MapAndCell.Map;
import View.Helicopter.HeliCopterView;
import View.Map.MapView;
import View.Menu.MenuView;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;

public class View extends Application {
    private static final String backGround =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png";
    private static final String accounts = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\accounts";

    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static final String farmFrenzyScenesDesign =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\";

    @Override
    public void start(Stage primaryStage) throws Exception{
        View view = new View();
        MenuView menuView = new MenuView();
        Controller controller = new Controller();
        Gson deserializer = new Gson();
        Group mainMenu = new Group();
        Scene menu = new Scene(mainMenu, 800, 620);

        Group nameMenu = new Group();
        Scene names = new Scene(nameMenu, 818, 557);

        Group chooseMap = new Group();
        Scene choseMap = new Scene(chooseMap, 818, 557);


        Group map = new Group();
        Scene mapScene = new Scene(map, 1000, 750);

//        Group hGroup = new Group();
//        Scene hScene = new Scene(hGroup, 1000, 750);

//        MapView mapView = new MapView(controller,primaryStage,controller.getMap().getWareHouse(),mapScene,  hScene,
//                controller.getMap(),map);
//        mapView.gameMap(map, mapScene, controller.getMap());

        menuView.mainMenu(primaryStage, menu, names, choseMap,  mainMenu, nameMenu);
        menuView.nameMenu(primaryStage, menu, nameMenu, names, mainMenu, choseMap);

//        HeliCopterView heliCopterView = new HeliCopterView(primaryStage, mapScene,
//                hScene, hGroup, map);
//        heliCopterView.helicopterShow(controller);
        menuView.mapChooseMenu(primaryStage, chooseMap, choseMap, menu, controller);

        primaryStage.setScene(menu);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
