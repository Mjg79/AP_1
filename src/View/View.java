package View;

import Controller.Controller;
import View.Map.MapView;
import View.Menu.MenuView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Group mainMenu = new Group();
        Scene menu = new Scene(mainMenu, 800, 620);

        Group nameMenu = new Group();
        Scene names = new Scene(nameMenu, 818, 557);

        Group chooseMap = new Group();
        Scene choseMap = new Scene(chooseMap, 818, 557);
        menuView.mapChooseMenu(primaryStage, chooseMap, choseMap, menu);

        Group map = new Group();
        Scene mapGame = new Scene(map, 1000, 750);

        Group hGroup = new Group();
        Scene hScene = new Scene(hGroup, 1000, 750);

        MapView mapView = new MapView(controller,primaryStage,controller.getMap().getWareHouse(), hScene);
        mapView.gameMap(map, mapGame, controller.getMap());

        menuView.mainMenu(primaryStage, menu, names, choseMap,  mainMenu, nameMenu);
        menuView.nameMenu(primaryStage, menu, nameMenu, names, mainMenu, choseMap);

        HeliCopterView heliCopterView = new HeliCopterView(primaryStage, controller, mapScene,
                hScene, hGroup, mapView, map);
        heliCopterView.helicopterShow();
        primaryStage.setScene(mapGame);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
