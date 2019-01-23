package View.Map;
import Controller.Controller;
import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Cell;
import Model.MapAndCell.Map;
import Model.Places.WareHouse;
import View.Buttons.GeneralButton;
import View.Buttons.GrassButton;
import View.Buttons.LiveStocks.*;
import View.Buttons.MenuButton;
import View.Buttons.WellButton;
import View.Buttons.WorkShop.CakeBakeryButton;
import View.Buttons.WorkShop.CookieBakeryButton;
import View.Buttons.WorkShop.EggPowderPlantButton;
import View.MissionNeeds;
import View.Services.WorkShops.CakeBakery;
import View.Services.WorkShops.CookieBakery;
import View.Services.WorkShops.EggPowderPlant;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MapView {
    private static Controller controller;
    private Stage primaryStage;
    private Scene helicopterScene;
    private WarehouseScene warehouseScene;
    private Pane backGroundPane = new Pane();
    private Pane grassButtonPane = new Pane();
    private static boolean isPaused = false;
    private static boolean isResumed = false;
    private static boolean isPlaying = true;
    private static Button hButton = new Button();//helicopterButton
    private static Button truckButton = new Button();
    private static Scene mapScene;

    public MapView(Controller controller, Stage primaryStage,WareHouse wareHouse,Scene mapScene, Scene helicopterScene) {
        MapView.controller = controller;
        MapView.mapScene = mapScene;
        this.primaryStage = primaryStage;
        try {
            warehouseScene = new WarehouseScene(wareHouse,mapScene);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.helicopterScene = helicopterScene;
    }

    private static final String backGround =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png";
    private static final String accounts = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\accounts";

    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static final String farmFrenzyScenesDesign =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\";
    private static final String animalBuy =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\buttons\\buyAnimal\\";
    private static final String upgrade = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\upgrade\\";

    private ImageView chickenView = new ImageView();
    private ImageView ostrichView = new ImageView();
    private ImageView buffaloView = new ImageView();
    private ImageView helicopterView = new ImageView();
    private ImageView truckView = new ImageView();
    private ImageView dogView = new ImageView();
    private ImageView catView = new ImageView();



    private void setBuyChickenView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 100 && isPlaying) {
                    try {
                        chickenView.setImage(new Image(new FileInputStream(animalBuy + "chickenAfter.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        chickenView.setImage(new Image(new FileInputStream(animalBuy + "chickenBefore.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        Button chickenButton = ChickenButton.chickenButton(mapGroup, controller.getMap(), chickenView, mapScene);

    }

    private void setBuyOstrichView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 1000 && isPlaying) {
                    try {
                        ostrichView.setImage(new Image(new FileInputStream(animalBuy + "ostrichAfter.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        ostrichView.setImage(new Image(new FileInputStream(animalBuy + "ostrichBefore.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        Button ostrichButton = OstrichButton.ostrichButton(mapGroup, controller.getMap(), ostrichView, mapScene);

    }

    private void setBuyBuffaloView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 10000 && isPlaying) {
                    try {
                        buffaloView.setImage(new Image(new FileInputStream(animalBuy + "buffaloAfter.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        buffaloView.setImage(new Image(new FileInputStream(animalBuy + "buffaloBefore.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        Button buffaloButton = BuffaloButton.buffaloButton(mapGroup, controller.getMap(), buffaloView, mapScene);

    }

    private void setBuyCatView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 2500 && isPlaying) {
                    try {
                        catView.setImage(new Image(new FileInputStream(animalBuy + "catAfter.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        catView.setImage(new Image(new FileInputStream(animalBuy + "catBefore.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        Button catButton = CatButton.catButton(mapGroup, controller.getMap(), catView, mapScene);

    }

    private void setBuyDogView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 2600 && isPlaying) {
                    try {
                        dogView.setImage(new Image(new FileInputStream(animalBuy + "dogAfter.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        dogView.setImage(new Image(new FileInputStream(animalBuy + "dogBefore.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        Button dogButton = DogButton.dogButton(mapGroup, controller.getMap(), dogView, mapScene);

    }


    public void initializeGameMap(Group mapGroup, Scene mapScene, Map maps) throws FileNotFoundException {
        mapGroup.getChildren().add(backGroundPane);
        mapGroup.getChildren().add(grassButtonPane);
        Image backGround1 = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\mapBackGround.png"));
        ImageView mapViewBackGround = new ImageView(backGround1);
        mapViewBackGround.setFitWidth(mapScene.getWidth());
        mapViewBackGround.setFitHeight(mapScene.getHeight());
        backGroundPane.getChildren().add(mapViewBackGround);

        maps.getWell().wellAnimation(false, 1, mapGroup).play();
        EggPowderPlant.eggPowderPlantAnimation(false, mapGroup, 1).play();
        CakeBakery.cakeBakeryAnimation(false, mapGroup, 1).play();
        CookieBakery.cookieBakeryAnimation(false, mapGroup, 1).play();


        Image wareHouse = new Image(new FileInputStream(serviceFiles + "Depot\\01.png"));
        ImageView wareHouseView = new ImageView(wareHouse);
        wareHouseView.relocate(390, 579);
        mapGroup.getChildren().add(wareHouseView);
        mapGroup.getChildren().add(truckView);
        truckButton = new Button();
        truckButton.relocate(278,650);
        truckButton.setScaleX(4.5);
        truckButton.setScaleY(4.5);
        truckButton.setOpacity(0);
        GeneralButton.buttonAppearanceWithCursor(truckButton,mapScene);
        mapGroup.getChildren().add(truckButton);

        hButton.relocate(606, 559);
        hButton.setGraphic(helicopterView);
        hButton.setBorder(Border.EMPTY);
        hButton.setBackground(Background.EMPTY);
        hButton.setPadding(Insets.EMPTY);
        GeneralButton.buttonAppearanceWithCursor(hButton, mapScene);
        mapGroup.getChildren().add(hButton);

        Image underBar = new Image(new FileInputStream(farmFrenzyScenesDesign + "underbar.png"));
        ImageView underBarView = new ImageView(underBar);
        underBarView.relocate(0, 580);
        mapGroup.getChildren().add(underBarView);

        Image moneyAndTransportation = new Image(new FileInputStream(farmFrenzyScenesDesign + "upperbar.png"));
        ImageView moneyAndTransportationView = new ImageView(moneyAndTransportation);
        moneyAndTransportationView.relocate(620, 0);
        mapGroup.getChildren().add(moneyAndTransportationView);
    }


    private void workShopButtons(Group mapGroup, Scene mapScene) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("chicken", 200);
        Button well = WellButton.wellButton(mapGroup, mapScene, controller.getMap());
        Button eggPowderPlant = EggPowderPlantButton.workShopButton(mapGroup, mapScene, 146, 236,
                controller.getMap());
        Button cakeBakeryPlant = CakeBakeryButton.workShopButton(mapGroup , mapScene, 146, 520,
                controller.getMap());
        Button cookieBakery = CookieBakeryButton.workShopButton(mapGroup, mapScene, 711, 221,
                controller.getMap());
        //TODO: our criteria for putting button for gathering product and grass: (250-250) to (730-530)
        refreshGrassButtons(mapScene);
    }

    public void refreshGrassButtons(Scene mapScene) {
        for (int i = 0; i < 36; i++)
            for (int j = 0; j < 36; j++) {
                Button grassButtons = GrassButton.grassButton(grassButtonPane, backGroundPane
                        , mapScene, controller.getMap(),
                        250 + i * 12, 250 + j * 7, this);
            }
    }

    public void buttons(Group mapGroup, Scene mapScene) throws FileNotFoundException {
        MissionNeeds.showMissionAndGathers(mapGroup, controller.getMap());
        this.workShopButtons(mapGroup, mapScene);
        this.setBuyChickenView(mapGroup, mapScene);
        this.setBuyOstrichView(mapGroup, mapScene);
        this.setBuyBuffaloView(mapGroup, mapScene);
        this.setBuyCatView(mapGroup, mapScene);
        this.setBuyDogView(mapGroup, mapScene);
        MenuButton.inGameMenuButton(mapGroup, mapScene);
    }


    public void gameMap(Group map, Scene mapScene , Map maps) throws FileNotFoundException {
        this.initializeGameMap(map, mapScene, maps);
        buttons(map, mapScene);
        timeShow(controller.getMap(), map, mapScene);
        mapBudget(map);
        upgradeWell(map, maps);
    }

    private void mapBudget(Group mapGroup) {
        Text label = new Text();
        mapGroup.getChildren().add(label);
        label.relocate(670, 26);
        label.setStyle("-fx-font-size: 25; -fx-fill: #fffa3d; -fx-opacity: 1; -fx-font-family: 'A Spirit Of Doha Black';" +
                "-fx-border-width: 2px; -fx-border-color: #000000; -fx-font-weight: bold");
        label.setText("Budget\n" + Double.toString(controller.getMap().getBudget()));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                label.setText("Budget\n" + Integer.toString(controller.getMap().getBudget()));
            }
        };
        animationTimer.start();
    }

    public void timeShow(Map map, Group mapGroup, Scene mapScene) {
        Text timerLabel = new Text();
        mapGroup.getChildren().add(timerLabel);
        timerLabel.relocate(840, 710);
        timerLabel.setStyle("-fx-fill: white; -fx-font-size: 30");
        timerLabel.setText("0");
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            private long pausedTime = 0;
            private double time = 0;
            private long second = 1000000000;
            @Override
            public void handle(long now) {
                if (lastTime == 0)
                    lastTime = now;
                if (now > lastTime + (second/10) && isPlaying) {
                    lastTime = now;
                    time += 1;
                    System.out.println(time);
                    if (time == 10)
                        map.addWildAnimal();
                    timerLabel.setText(Integer.toString((int) time / 600) + ":" + ((int)time / 10 % 60 < 10 ? "0"
                            : "") + Integer.toString(((int)time/10)% 60));
                    try {
                        showCell(mapGroup, mapScene, controller.getMap());
                        showLiveStocks(mapGroup, mapScene, controller.getMap().getFarmTime());
                        showWildAnimals(controller.getMap(), mapGroup, mapScene, controller.getMap().getFarmTime());
                        showHelicopter(controller.getMap(), mapGroup);
                        setHButton();
                        showTruck(map,mapScene,mapGroup);
                        showCats(mapGroup, mapScene);
                        showDogs(mapGroup, mapScene);
                        truckButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if(isPlaying) {
                                    MapView.pause();
                                    warehouseScene.changeToWarehouse(primaryStage);
                                }
                            }
                        });
                        showWildAnimals(controller.getMap(), mapGroup, mapScene, controller.getMap().getFarmTime());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if((int)time - time == 0) {
                        try {
                            map.turnMap(0.1, mapGroup);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println("term nam");
                    }
                }
                else if(isPaused){
                    isPaused = false;
                    pausedTime = now - lastTime;
                }
                else if(!isPlaying){
                    lastTime = now - pausedTime;
                }
            }
        };
        timer.start();
    }

    private void showLiveStocks(Group mapGroup, Scene scene, double farmTime) throws FileNotFoundException {
        for (LiveStock liveStock: controller.getMap().getLiveStocks()) {
            if (liveStock.getHungerLevel() <= 0.101)
                liveStock.graphicDeath = true;
            if (liveStock.durationDeath == 10)
                liveStock.setIsKilled(true);
            liveStock.chickenMoving(scene, mapGroup, false,controller.getMap().getFarmTime());
        }
    }

    private void showCell(Group group, Scene scene, Map map) throws FileNotFoundException {
        for (Cell[] cells: controller.getMap().getCells())
            for (Cell cells1: cells)
                cells1.showCell(group, scene, map);
    }

    private void showWildAnimals(Map map, Group mapGroup, Scene scene, double farmTime) throws FileNotFoundException {
        for (WildAnimal wildAnimal: controller.getMap().getWildAnimals()) {
            wildAnimal.wildAnimalMoving(map, scene, mapGroup, false, farmTime);
        }
    }

    private void showCats(Group mapGroup, Scene scene) throws FileNotFoundException {
        for (Cat cat: controller.getMap().getCats()) {
            cat.catMoving(scene, mapGroup, false);
        }
    }

    private void showDogs(Group mapGroup, Scene scene) throws FileNotFoundException {
        for (Dog dog: controller.getMap().getDogs()) {
            dog.dogMoving(scene, mapGroup ,false);
            System.out.println("catX: " + dog.getX() + " ,catY: " + dog.getY());

        }
    }

    private void showTruck(Map map,Scene mapScene,Group group) throws FileNotFoundException {
        truckView.setImage(new Image(new FileInputStream(serviceFiles + "Truck\\0"+
                controller.getMap().getTruck().getLevel()+ ".png")));
        truckView.relocate(220, 589);
    }

    private void showHelicopter(Map map, Group group) throws FileNotFoundException {
        helicopterView.setImage(new Image(new FileInputStream(serviceFiles + "Helicopter\\0" +
                controller.getMap().getHelicopter().getLevel()+  ".png")));
        if (!map.getHelicopter().isInWareHouse()) {
            hButton.setVisible(false);
            helicopterView.setVisible(false);
        } else {
            hButton.setVisible(true);
            helicopterView.setVisible(true);
        }
    }


    private void setHButton() {
        hButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isPlaying){
                    MapView.pause();
                    primaryStage.setScene(helicopterScene);
                }
            }
        });
    }

    public static void pause(){
        isPaused = true;
        isPlaying = false;
        WellButton.pause();
        EggPowderPlantButton.pause();
        CakeBakeryButton.pause();
        CookieBakeryButton.pause();
        ChickenButton.pause();
        GeneralButton.buttonAppearanceDefault(hButton, mapScene);
        GeneralButton.buttonAppearanceDefault(truckButton, mapScene);
        //OstrichButton.pause();
        //BuffaloButton.pause();
    }

    public static void resume(){
        isResumed = true;
        isPlaying = true;
        WellButton.resume();
        EggPowderPlantButton.resume();
        CakeBakeryButton.resume();
        CookieBakeryButton.resume();
        ChickenButton.resume();
        GeneralButton.buttonAppearanceWithCursor(hButton, mapScene);
        GeneralButton.buttonAppearanceWithCursor(truckButton, mapScene);
        //OstrichButton.resume();
        //BuffaloButton.resume();
    }

    private void upgradeWell(Group mapGroup, Map map) {
        ImageView upgradeView = new ImageView();
        Label text = new Label("0");
        upgradeView.relocate(400, 400);
        text.relocate(436, 404);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20");
        mapGroup.getChildren().add(upgradeView);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                text.setText(Integer.toString((int)map.getWell().getMoneyForUpgrading()));
                try {
                    if (map.getWell().getLevel() < 4 && map.getWell().getMoneyForUpgrading() < map.getBudget()) {
                        upgradeView.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        upgradeView.setOpacity(1);
                    }else {
                        upgradeView.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        upgradeView.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        upgradeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (upgradeView.getOpacity() == 1)
                    map.upgrade("well");
            }
        });

    }

}
