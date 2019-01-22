package View.Map;
import Controller.Controller;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Cell;
import Model.MapAndCell.Map;
import Model.Places.WareHouse;
import View.Buttons.GeneralButton;
import View.Buttons.GrassButton;
import View.Buttons.LiveStocks.BuffaloButton;
import View.Buttons.LiveStocks.ChickenButton;
import View.Buttons.LiveStocks.OstrichButton;
import View.Buttons.MenuButton;
import View.Buttons.WellButton;
import View.Buttons.WorkShop.CakeBakeryButton;
import View.Buttons.WorkShop.CookieBakeryButton;
import View.Buttons.WorkShop.EggPowderPlantButton;
import View.Services.WorkShops.CakeBakery;
import View.Services.WorkShops.CookieBakery;
import View.Services.WorkShops.EggPowderPlant;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MapView {
    private Controller controller;
    private Stage primaryStage;
    private Scene helicopterScene;
    private WarehouseScene warehouseScene;

    public MapView(Controller controller, Stage primaryStage,WareHouse wareHouse, Scene helicopterScene) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        warehouseScene = new WarehouseScene(wareHouse,primaryStage.getScene());
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

    private ImageView chickenView = new ImageView();
    private ImageView ostrichView = new ImageView();
    private ImageView buffaloView = new ImageView();
    private ImageView helicopterView = new ImageView();
    private ImageView dogView = new ImageView();
    private Button hButton = new Button();//helicopterButton
    private ImageView catView = new ImageView();



    private void setBuyChickenView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getBudget() >= 100) {
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
                if (controller.getMap().getBudget() >= 1000) {
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
                if (controller.getMap().getBudget() >= 10000) {
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



    public void initializeGameMap(Group map, Scene gameMap, Map maps) throws FileNotFoundException {
        Image backGround1 = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\mapBackGround.png"));
        ImageView mapViewBackGround = new ImageView(backGround1);
        mapViewBackGround.setFitWidth(gameMap.getWidth());
        mapViewBackGround.setFitHeight(gameMap.getHeight());
        map.getChildren().add(mapViewBackGround);

        maps.getWell().wellAnimation(false, 1, map).play();
        EggPowderPlant.eggPowderPlantAnimation(false, map, 1).play();
        CakeBakery.cakeBakeryAnimation(false, map, 1).play();
        CookieBakery.cookieBakeryAnimation(false, map, 1).play();


        Image wareHouse = new Image(new FileInputStream(serviceFiles + "Depot\\01.png"));
        ImageView wareHouseView = new ImageView(wareHouse);
        wareHouseView.relocate(390, 579);
        map.getChildren().add(wareHouseView);
        Image Truck = new Image(new FileInputStream(serviceFiles + "Truck\\01.png"));
        ImageView truckView = new ImageView(Truck);
        truckView.relocate(220, 589);
        Button truckButton = new Button();
        truckButton.relocate(278,650);
        truckButton.setScaleX(4.5);
        truckButton.setScaleY(4.5);
        truckButton.setOpacity(0);



        truckButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                warehouseScene.changeToWarehouse(primaryStage);
            }
        });
        map.getChildren().add(truckView);
        map.getChildren().add(truckButton);

        helicopterView.relocate(606,559);
        map.getChildren().add(helicopterView);
        hButton.relocate(656, 639);
        hButton.setText("helicopter\nhelicopter");
        GeneralButton.buttonAppearanceWithCursor(hButton, gameMap);
        hButton.setOpacity(0);
        map.getChildren().add(hButton);

        Image underBar = new Image(new FileInputStream(farmFrenzyScenesDesign + "underbar.png"));
        ImageView underBarView = new ImageView(underBar);
        underBarView.relocate(0, 580);
        map.getChildren().add(underBarView);

        Image moneyAndTransportation = new Image(new FileInputStream(farmFrenzyScenesDesign + "upperbar.png"));
        ImageView moneyAndTransportationView = new ImageView(moneyAndTransportation);
        moneyAndTransportationView.relocate(620, 0);
        map.getChildren().add(moneyAndTransportationView);
    }


    private void workShopButtons(Group mapGroup, Scene mapScene) throws FileNotFoundException {
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
        refreshGrassButtons(mapGroup, mapScene);
    }

    public void refreshGrassButtons(Group mapGroup, Scene mapScene) {
        for (int i = 0; i < 36; i++)
            for (int j = 0; j < 36; j++) {
                Button grassButtons = GrassButton.grassButton(mapGroup, mapScene, controller.getMap(),
                        250 + i * 12, 250 + j * 7, this);
            }
    }

    public void buttons(Group mapGroup, Scene mapScene) throws FileNotFoundException {
        this.workShopButtons(mapGroup, mapScene);
        this.setBuyChickenView(mapGroup, mapScene);
        this.setBuyOstrichView(mapGroup, mapScene);
        this.setBuyBuffaloView(mapGroup, mapScene);
        MenuButton.inGameMenuButton(mapGroup, mapScene);
    }


    public void gameMap(Group map, Scene mapScene , Map maps) throws FileNotFoundException {
        this.initializeGameMap(map, mapScene, maps);
        buttons(map, mapScene);
        timeShow(controller.getMap(), map, mapScene);
        mapBudget(map);
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
                label.setText("Budget\n" + Integer.toString((int)controller.getMap().getBudget()));
            }
        };
        animationTimer.start();
    }

    public void timeShow(Map map, Group mapGroup, Scene scene) {
        Text timerLabel = new Text();
        mapGroup.getChildren().add(timerLabel);
        timerLabel.relocate(840, 710);
        timerLabel.setStyle("-fx-fill: white; -fx-font-size: 30");
        timerLabel.setText("0");
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            private double time = 0;
            private long second = 1000000000;
            @Override
            public void handle(long now) {
                if (lastTime == 0)
                    lastTime = now;
                if (now > lastTime + (second/10)) {
                    lastTime = now;
                    time += 1;
                    System.out.println(time);
//                    if (time == 2)
//                        map.addWildAnimal();
                    timerLabel.setText(Integer.toString((int) time / 600) + ":" + ((int)time / 10 % 60 < 10 ? "0"
                            : "") + Integer.toString(((int)time/10)% 60));
                    try {
                        showCell(mapGroup, scene, controller.getMap());
                        showLiveStocks(mapGroup, scene, controller.getMap().getFarmTime());
                        showWildAnimals(controller.getMap(), mapGroup, scene, controller.getMap().getFarmTime());
                        showHelicopter(controller.getMap(), mapGroup);
                        setHButton();
                        showWildAnimals(controller.getMap(), mapGroup, scene, controller.getMap().getFarmTime());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if((int)time - time == 0) {
                        map.turnMap(0.1);
                        System.out.println("term nam");
                    }
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
            System.out.println("\nx: " + liveStock.getX() + " ,y: " + liveStock.getY());
            System.out.print("hungerLevel: " + liveStock.getHungerLevel());
            System.out.print("ismustEat: "  + liveStock.isMustEatForage() + " , isKilled: " + liveStock.isKilled());
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
//            System.out.print("x: " + wildAnimal.getX() + " ,y: " + wildAnimal.getY());
//            System.out.print(" ,is caged: " + wildAnimal.isCaged() );
        }
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
                primaryStage.setScene(helicopterScene);
            }
        });
    }

}
