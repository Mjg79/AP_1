package View.Map;
import Controller.Controller;
import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Cell;
import Model.MapAndCell.Map;
import Model.Places.WareHouse;
import Model.Places.WorkShop;
import View.Brightness.Brightness;
import View.Buttons.GeneralButton;
import View.Buttons.GrassButton;
import View.Buttons.LiveStocks.*;
import View.Buttons.MenuButton;
//import View.Buttons.WellButton;
//import View.Buttons.WorkShop.EggPowderPlantButton;
import View.MissionNeed;
import View.MissionNeeds;
import View.Services.WorkShops.CakeBakery;
import View.Services.WorkShops.CookieBakery;
import View.Services.WorkShops.EggPowderPlant;
import com.google.gson.Gson;
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

import java.io.*;

public class MapView {
    private  Controller controller;
    private transient Stage primaryStage;
    private transient Scene helicopterScene;
    private transient WarehouseScene warehouseScene;
    private transient Pane backGroundPane = new Pane();
    private transient Pane grassButtonPane = new Pane();
    private static boolean isPaused = false;
    private static boolean isResumed = false;
    private static boolean isPlaying = true;
    private transient static Button hButton = new Button();//helicopterButton
    private transient static Button truckButton = new Button();
    private transient static Scene mapScene;
    private transient String fileName;
    private transient Scene chooseMap;
    private transient Scene menu;

    public MapView(Controller controller, Stage primaryStage,WareHouse wareHouse,Scene mapScene, Scene helicopterScene
    ,Map map, Scene chooseMap, Scene menu) {
        this.controller = controller;
        MapView.mapScene = mapScene;
        this.primaryStage = primaryStage;
        this.chooseMap = chooseMap;
        this.menu = menu;
        try {
            warehouseScene = new WarehouseScene(wareHouse,mapScene,map);
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
    private ImageView wareHouseView = new ImageView();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private void setBuyChickenView(Group mapGroup , Scene mapScene) throws FileNotFoundException {
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
        if (!mapGroup.getChildren().contains(backGroundPane) && !mapGroup.getChildren().contains(grassButtonPane)) {
            mapGroup.getChildren().add(backGroundPane);
            mapGroup.getChildren().add(grassButtonPane);
        }
        Image backGround1 = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\mapBackGround.png"));
        ImageView mapViewBackGround = new ImageView(backGround1);
        mapViewBackGround.setFitWidth(mapScene.getWidth());
        mapViewBackGround.setFitHeight(mapScene.getHeight());
        backGroundPane.getChildren().add(mapViewBackGround);

        if (!mapGroup.getChildren().contains(truckView))
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
        if (!mapGroup.getChildren().contains(hButton))
         mapGroup.getChildren().add(hButton);

        Image underBar = new Image(new FileInputStream(farmFrenzyScenesDesign + "underbar.png"));
        ImageView underBarView = new ImageView(underBar);
        underBarView.relocate(0, 580);
        if (!mapGroup.getChildren().contains(underBarView))
            mapGroup.getChildren().add(underBarView);

        Image moneyAndTransportation = new Image(new FileInputStream(farmFrenzyScenesDesign + "upperbar.png"));
        ImageView moneyAndTransportationView = new ImageView(moneyAndTransportation);
        moneyAndTransportationView.relocate(620, 0);
        if (!mapGroup.getChildren().contains(moneyAndTransportationView))
            mapGroup.getChildren().add(moneyAndTransportationView);
    }


    private void grassButtons(Group mapGroup, Scene mapScene) {
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
        this.grassButtons(mapGroup, mapScene);
        this.setBuyChickenView(mapGroup, mapScene);
        this.setBuyOstrichView(mapGroup, mapScene);
        this.setBuyBuffaloView(mapGroup, mapScene);
        this.setBuyCatView(mapGroup, mapScene);
        this.setBuyDogView(mapGroup, mapScene);
        MenuButton.inGameMenuButton(mapGroup, mapScene, this,  primaryStage, chooseMap, menu);
    }

    public  Controller getController() {
        return controller;
    }

    public void missionCompleted() throws FileNotFoundException {
        MissionNeed.backToMenu(primaryStage, menu);
    }

    public void gameMap(Group map, Scene mapScene , Map maps) throws FileNotFoundException {
        this.initializeGameMap(map, mapScene, maps);
        buttons(map, mapScene);
        timeShow(controller.getMap(), map, mapScene);
        mapBudget(map);
        upgradeWell(map, maps);
        upgradeHelicopter(map, maps);
        upgradeEggPlant(map, maps);
        upgradeCookieBakery(map, maps);
        upgradeCakeBakery(map, maps);

        showAndUpgradeWareHouse(map, maps);

        maps.getWell().setWellView(map);
        maps.getWell().showWellInWorking(maps);
        maps.getWell().wellInfo(map);

        EggPowderPlant.setEggPlantView(map, maps);
        EggPowderPlant.showEggPlantInWorking(maps);
        EggPowderPlant.eggPlantInfo(map, maps);
        showHelicopterInfo(maps, map);

        CookieBakery.setCookieBakeryView(map, maps);
        CookieBakery.showCookieBakeryInWorking(maps);
        CookieBakery.cookieBakeryInfo(map, maps);

        CakeBakery.setCakeBakeryView(map, maps);
        CakeBakery.showCookieBakeryInWorking(maps);
        CakeBakery.cakeBakeryInfo(map, maps);
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
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if((int)time - time == 0) {
                        try {
                            map.turnMap(0.1, mapGroup);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
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
            liveStock.liveStockMoving(scene, mapGroup, false,controller.getMap().getFarmTime());
            System.out.println("thisX: " + liveStock.getX() + ", thisY: " + liveStock.getY());
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

    private void showHelicopterInfo(Map map, Group group) throws FileNotFoundException {
            ImageView info = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop" +
                    "\\farmFrenzySaveFiles\\helpBox\\helpBox2.png")));
            WorkShop cakeBakery = map.getWorkshops().get(2);
            info.setScaleY(0.7);
            info.setScaleX(0.7);
            info.relocate(680, 470);
            group.getChildren().add(info);
            Label timeDuration = new Label();
            timeDuration.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
            Label level = new Label();
            level.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
            Label numOfBoxes = new Label();
            numOfBoxes.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
            timeDuration.relocate(740, 494);
            numOfBoxes.relocate(828, 494);
            level.relocate(740, 524);
            level.setOpacity(0);
            timeDuration.setOpacity(0);
            numOfBoxes.setOpacity(0);
            info.setOpacity(0);
            group.getChildren().addAll(level, numOfBoxes, timeDuration);

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    timeDuration.setText("time duration: " + map.getHelicopter().getTimeDurationForWorking());
                    numOfBoxes.setText("numOfBoxes: " + map.getHelicopter().getNumOfBoxes());
                    level.setText("level: " + map.getHelicopter().getLevel());
                }
            };
            timer.start();
            Brightness.changeHelicopterBrightness(timeDuration,  info, level, numOfBoxes, hButton);


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
        ChickenButton.pause();
        GeneralButton.buttonAppearanceDefault(hButton, mapScene);
        GeneralButton.buttonAppearanceDefault(truckButton, mapScene);
    }

    public static void resume(){
        isResumed = true;
        isPlaying = true;
        ChickenButton.resume();
        GeneralButton.buttonAppearanceWithCursor(hButton, mapScene);
        GeneralButton.buttonAppearanceWithCursor(truckButton, mapScene);
    }

    private void upgradeWell(Group mapGroup, Map map) {
        ImageView upgradeView = new ImageView();
        Label text = new Label("0");
        upgradeView.relocate(340, 130);
        text.relocate(376, 134);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(upgradeView);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    map.getWell().setWellView(mapGroup);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.setText(Integer.toString((int)map.getWell().getMoneyForUpgrading()));
                try {
                    if (map.getWell().getLevel() == 3) {
                        upgradeView.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getWell().getLevel() < 3 && map.getWell().getMoneyForUpgrading() < map.getBudget()) {
                        upgradeView.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        upgradeView.setOpacity(1);
                    }else if (map.getWell().getLevel() < 3 && map.getWell().getMoneyForUpgrading() >= map.getBudget()){
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
                if (upgradeView.getOpacity() == 1) {
                    map.upgrade("well");
                }
            }
        });

    }

    private void upgradeEggPlant(Group mapGroup, Map map) {
        ImageView eggPlant = new ImageView();
        Label text = new Label("0");
        eggPlant.relocate(100, 330);
        text.relocate(136, 334);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(eggPlant);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    EggPowderPlant.setEggPlantView(mapGroup, map);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.setText(Integer.toString((int)map.getWorkshops().get(0).getMoneyForUpgrading()));
                try {
                    if (map.getWorkshops().get(0).getLevel() == 4) {
                        eggPlant.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getWorkshops().get(0).getLevel() < 4
                            && map.getWorkshops().get(0).getMoneyForUpgrading() <= map.getBudget()) {
                        eggPlant.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        eggPlant.setOpacity(1);
                    }else if (map.getWorkshops().get(0).getLevel() < 4
                            && map.getWorkshops().get(0).getMoneyForUpgrading() > map.getBudget()){
                        eggPlant.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        eggPlant.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        eggPlant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (eggPlant.getOpacity() == 1) {
                    map.upgrade("EggPowderedPlant");
                }
            }
        });

    }

    private void upgradeCookieBakery(Group mapGroup, Map map) {
        ImageView cookieBakery = new ImageView();
        Label text = new Label("0");
        cookieBakery.relocate(850, 230);
        text.relocate(886, 234);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(cookieBakery);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    CookieBakery.setCookieBakeryView(mapGroup, map);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.setText(Integer.toString((int)map.getWorkshops().get(1).getMoneyForUpgrading()));
                try {
                    if (map.getWorkshops().get(1).getLevel() == 4) {
                        cookieBakery.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getWorkshops().get(1).getLevel() < 4
                            && map.getWorkshops().get(1).getMoneyForUpgrading() <= map.getBudget()) {
                        cookieBakery.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        cookieBakery.setOpacity(1);
                    }else if (map.getWorkshops().get(1).getLevel() < 4
                            && map.getWorkshops().get(1).getMoneyForUpgrading() > map.getBudget()){
                        cookieBakery.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        cookieBakery.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        cookieBakery.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cookieBakery.getOpacity() == 1) {
                    map.upgrade("CookieBakery");
                }
            }
        });

    }

    private void upgradeCakeBakery(Group mapGroup, Map map) {
        ImageView cakeBakery = new ImageView();
        Label text = new Label("0");
        cakeBakery.relocate(130, 385);
        text.relocate(166, 389);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(cakeBakery);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    CakeBakery.setCakeBakeryView(mapGroup, map);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.setText(Integer.toString((int)map.getWorkshops().get(2).getMoneyForUpgrading()));
                try {
                    if (map.getWorkshops().get(2).getLevel() == 4) {
                        cakeBakery.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getWorkshops().get(2).getLevel() < 4
                            && map.getWorkshops().get(2).getMoneyForUpgrading() <= map.getBudget()) {
                        cakeBakery.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        cakeBakery.setOpacity(1);
                    }else if (map.getWorkshops().get(2).getLevel() < 4
                            && map.getWorkshops().get(2).getMoneyForUpgrading() > map.getBudget()){
                        cakeBakery.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        cakeBakery.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        cakeBakery.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cakeBakery.getOpacity() == 1) {
                    map.upgrade("CakeBakery");
                }
            }
        });

    }


    private void upgradeHelicopter(Group mapGroup, Map map) {
        ImageView upgradeHelicopter = new ImageView();
        Label text = new Label();
        upgradeHelicopter.relocate(556, 689);
        text.relocate(586, 692);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(upgradeHelicopter);
        mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().isMissionCompleted()) {
                    Gson serializer = new Gson();
                    try {
                        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(getFileName()));
                        serializer.toJson(controller.getMap(), Map.class, writer);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        missionCompleted();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    this.stop();
                }
                text.setText(Integer.toString((int)map.getHelicopter().getMoneyForUpgrading()));
                try {
                    if (map.getHelicopter().getLevel() == 4) {
                        upgradeHelicopter.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getHelicopter().getLevel() < 4 && map.getHelicopter().getMoneyForUpgrading() <
                            map.getBudget()) {
                        upgradeHelicopter.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        upgradeHelicopter.setOpacity(1);
                    }else if (map.getHelicopter().getLevel() < 4 && map.getHelicopter().getMoneyForUpgrading() >=
                            map.getBudget()){
                        upgradeHelicopter.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        upgradeHelicopter.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        upgradeHelicopter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (upgradeHelicopter.getOpacity() == 1) {
                    map.upgrade("helicopter");
                }
            }
        });

    }

    private void showAndUpgradeWareHouse(Group mapGroup, Map map) throws FileNotFoundException {
        wareHouseView.relocate(390, 579);
        if (!mapGroup.getChildren().contains(wareHouseView))
            mapGroup.getChildren().add(wareHouseView);
        wareHouseView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        ImageView upgradeWareHouse = new ImageView();
        Label text = new Label();
        upgradeWareHouse.relocate(316, 695);
        text.relocate(346, 698);
        wareHouseInfo(mapGroup, map);
        text.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 20; -fx-font-weight: BOLD");
        mapGroup.getChildren().add(upgradeWareHouse);
        if (!mapGroup.getChildren().contains(text))
            mapGroup.getChildren().add(text);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    wareHouseView.setImage(new Image(new FileInputStream(serviceFiles + "Depot\\0"
                            + map.getWareHouse().getLevel() + ".png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                text.setText(Integer.toString((int)map.getWareHouse().getMoneyForUpgrading()));
                try {
                    if (map.getWareHouse().getLevel() == 4) {
                        upgradeWareHouse.setVisible(false);
                        text.setVisible(false);
                    }
                    if (map.getWareHouse().getLevel() < 4 && map.getWareHouse().getMoneyForUpgrading() <=
                            map.getBudget()) {
                        upgradeWareHouse.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png")));
                        upgradeWareHouse.setOpacity(1);
                    }else if (map.getWareHouse().getLevel() < 4 && map.getWareHouse().getMoneyForUpgrading() >
                            map.getBudget()){
                        upgradeWareHouse.setImage(new Image(new FileInputStream(upgrade + "purchaseButtonGray.png")));
                        upgradeWareHouse.setOpacity(0.9);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();

        upgradeWareHouse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (upgradeWareHouse.getOpacity() == 1) {
                    map.upgrade("wareHouse");

                }
            }
        });


    }

    private void wareHouseInfo(Group group, Map map) throws FileNotFoundException {
        ImageView info = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop" +
                "\\farmFrenzySaveFiles\\helpBox\\helpBox2.png")));
        info.setScaleY(0.5);
        info.setScaleX(0.5);
        info.setOpacity(0);
        info.relocate(495, 540);
        if (!group.getChildren().contains(info))
            group.getChildren().add(info);
        Label current = new Label();
        current.setStyle("-fx-opacity: 0;-fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        Label level = new Label();
        level.setStyle("-fx-opacity: 0; -fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        Label volume = new Label();
        volume.setStyle("-fx-opacity: 0;-fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        current.relocate(578, 582);
        volume.relocate(635, 582);
        level.relocate(616, 602);
        if (!group.getChildren().contains(level))
            group.getChildren().addAll(level, volume, current);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                current.setText("current: " + map.getWareHouse().getCurrent());
                volume.setText("volume: " + map.getWareHouse().getVolume());
                level.setText("level: " + map.getWareHouse().getLevel());
            }
        };
        timer.start();
        Brightness.changeBrightNess4(current, volume, info, level,  wareHouseView);
    }

}


