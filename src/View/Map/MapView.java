package View.Map;
import Controller.*;
import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Cell;
import Model.MapAndCell.Map;
import Model.Places.WorkShop;
import View.Brightness.Brightness;
import View.Buttons.GeneralButton;
import View.Buttons.GrassButton;
import View.Buttons.LiveStocks.*;
import View.Buttons.MenuButton;
import View.Chat.ChatRoom;
import View.MissionNeed.MissionNeed;
import View.MissionNeed.MissionNeeds;
import View.ScoreBoard.ScoreBoardScene;
import View.Services.WorkShops.CakeBakery;
import View.Services.WorkShops.CookieBakery;
import View.Services.WorkShops.EggPowderPlant;
import View.View;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    private transient String mode;
    private transient ChatRoom chatRoom;
    private transient ScoreBoardScene scoreBoardScene;


    public MapView(Controller controller, Stage primaryStage, Scene mapScene, Scene helicopterScene
    , Scene chooseMap, Scene menu, WarehouseScene warehouseScene, String mode) {
        this.controller = controller;
        MapView.mapScene = mapScene;
        this.primaryStage = primaryStage;
        this.chooseMap = chooseMap;
        this.menu = menu;
        this.warehouseScene = warehouseScene;
        this.helicopterScene = helicopterScene;
        this.mode = mode;
        scoreBoardScene = new ScoreBoardScene(primaryStage,mapScene);
    }



    private static final String accounts = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\accounts";

    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static final String farmFrenzyScenesDesign =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\";
    private static final String animalBuy =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\buttons\\buyAnimal\\";
    private static final String upgrade = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\upgrade\\";
    private static final String CHATROOM = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\chat\\";
    private static final String FARMFRENZY = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\";

    private static Image backGroundImage;
    private static Image chickenAfterImage;
    private static Image chickenBeforeImage;
    private static Image ostrichAfterImage;
    private static Image ostrichBeforeImage;
    private static Image buffaloAfterImage;
    private static Image buffaloBeforeImage;
    private static Image catAfterImage;
    private static Image catBeforeImage;
    private static Image dogAfterImage;
    private static Image dogBeforeImage;
    private static Image underBarImage;
    private static Image upperBarImage;
    private static Image truckImageL1;
    private static Image truckImageL2;
    private static Image truckImageL3;
    private static Image truckImageL4;
    private static Image helicopterImageL1;
    private static Image helicopterImageL2;
    private static Image helicopterImageL3;
    private static Image helicopterImageL4;
    private static Image helpBoxImage1;
    private static Image helpBoxImage2;
    private static Image helpBoxImage13;
    private static Image helpBoxImage24;
    private static Image purchaseButtonBlueImage;
    private static Image purchaseButtonGrayImage;
    private static Image depotImageL1;
    private static Image depotImageL2;
    private static Image depotImageL3;
    private static Image depotImageL4;
    private static Media backGroundMedia;
    private static Media wildAnimalMeida;


    static {
        try {
            backGroundImage = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\mapBackGround.png"));
            chickenAfterImage = new Image(new FileInputStream(animalBuy + "chickenAfter.png"));
            chickenBeforeImage = new Image(new FileInputStream(animalBuy + "chickenBefore.png"));
            ostrichAfterImage = new Image(new FileInputStream(animalBuy + "ostrichAfter.png"));
            ostrichBeforeImage = new Image(new FileInputStream(animalBuy + "ostrichBefore.png"));
            buffaloAfterImage = new Image(new FileInputStream(animalBuy + "buffaloAfter.png"));
            buffaloBeforeImage = new Image(new FileInputStream(animalBuy + "buffaloBefore.png"));
            catAfterImage = new Image(new FileInputStream(animalBuy + "catAfter.png"));
            catBeforeImage = new Image(new FileInputStream(animalBuy + "catBefore.png"));
            dogAfterImage = new Image(new FileInputStream(animalBuy + "dogAfter.png"));
            dogBeforeImage = new Image(new FileInputStream(animalBuy + "dogBefore.png"));
            underBarImage = new Image(new FileInputStream(farmFrenzyScenesDesign + "underbar.png"));
            upperBarImage = new Image(new FileInputStream(farmFrenzyScenesDesign + "upperbar.png"));
            truckImageL1 = new Image(new FileInputStream(serviceFiles + "Truck\\01.png"));
            truckImageL2 = new Image(new FileInputStream(serviceFiles + "Truck\\02.png"));
            truckImageL3 = new Image(new FileInputStream(serviceFiles + "Truck\\03.png"));
            truckImageL4 = new Image(new FileInputStream(serviceFiles + "Truck\\04.png"));
            helicopterImageL1 = new Image(new FileInputStream(serviceFiles + "Helicopter\\01.png"));
            helicopterImageL2 = new Image(new FileInputStream(serviceFiles + "Helicopter\\02.png"));
            helicopterImageL3 = new Image(new FileInputStream(serviceFiles + "Helicopter\\03.png"));
            helicopterImageL4 = new Image(new FileInputStream(serviceFiles + "Helicopter\\04.png"));
            helpBoxImage1 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox1.png"));
            helpBoxImage2 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox2.png"));
            helpBoxImage13 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox13.png"));
            helpBoxImage24 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox24.png"));
            purchaseButtonBlueImage = new Image(new FileInputStream(upgrade + "purchaseButtonBlue.png"));
            purchaseButtonGrayImage = new Image(new FileInputStream(upgrade + "purchaseButtonGray.png"));
            depotImageL1 = new Image(new FileInputStream(serviceFiles + "Depot\\01.png"));
            depotImageL2 = new Image(new FileInputStream(serviceFiles + "Depot\\02.png"));
            depotImageL3 = new Image(new FileInputStream(serviceFiles + "Depot\\03.png"));
            depotImageL4 = new Image(new FileInputStream(serviceFiles + "Depot\\04.png"));
            backGroundMedia = new Media(View.class.getResource("farmFrenzySaveFiles/soundTracks/backGroundSoundTrack.mp3").toExternalForm());
            wildAnimalMeida = new Media(View.class.getResource("farmFrenzySaveFiles/soundTracks/lionSound.WAV").toExternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ImageView scoreBoardButton;
    private static MediaPlayer backGroundMediaPlayer = new MediaPlayer(backGroundMedia);
    private static MediaPlayer wildAnimalMediaPlayer = new MediaPlayer(wildAnimalMeida);

    {
        try {
            scoreBoardButton = new ImageView(new Image(new FileInputStream(
                        FARMFRENZY+"jadval\\scoreBoard.png")));
            scoreBoardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    scoreBoardScene.setScene();
                }
            });
            scoreBoardButton.relocate(0,600);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private ImageView chickenView = new ImageView();
    private ImageView ostrichView = new ImageView();
    private ImageView buffaloView = new ImageView();
    private ImageView helicopterView = new ImageView();
    private static ImageView truckView = new ImageView();
    private ImageView dogView = new ImageView();
    private ImageView catView = new ImageView();
    private ImageView wareHouseView = new ImageView();

    public static Button getTruckButton() {
        return truckButton;
    }

    public static ImageView getTruckView() {
        return truckView;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public WarehouseScene getWarehouseScene() {
        return warehouseScene;
    }

    private void setBuyChickenView(Group mapGroup , Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!(chickenView.getImage() == chickenAfterImage) && controller.getMap().getBudget() >= 100 && isPlaying)
                    chickenView.setImage(chickenAfterImage);
                else if(!(chickenView.getImage() == chickenBeforeImage) && !(controller.getMap().getBudget() >= 100 && isPlaying))
                    chickenView.setImage(chickenBeforeImage);
            }
        };
        animationTimer.start();
        Button chickenButton = ChickenButton.chickenButton(mapGroup, controller.getMap(), chickenView, mapScene);

    }

    private void setBuyOstrichView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!(ostrichView.getImage() == ostrichAfterImage) && controller.getMap().getBudget() >= 1000 && isPlaying)
                    ostrichView.setImage(ostrichAfterImage);
                else if(!(ostrichView.getImage() == ostrichBeforeImage) && !(controller.getMap().getBudget() >= 1000 && isPlaying))
                    ostrichView.setImage(ostrichBeforeImage);
            }
        };
        animationTimer.start();
        Button ostrichButton = OstrichButton.ostrichButton(mapGroup, controller.getMap(), ostrichView, mapScene);

    }

    private void setBuyBuffaloView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!(buffaloView.getImage() == buffaloAfterImage) && controller.getMap().getBudget() >= 10000 && isPlaying)
                    buffaloView.setImage(buffaloAfterImage);
                else if(!(buffaloView.getImage() == buffaloBeforeImage) && !(controller.getMap().getBudget() >= 10000 && isPlaying))
                    buffaloView.setImage(buffaloBeforeImage);
            }
        };
        animationTimer.start();
        Button buffaloButton = BuffaloButton.buffaloButton(mapGroup, controller.getMap(), buffaloView, mapScene);

    }

    private void setBuyCatView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!(catView.getImage() == catAfterImage) && controller.getMap().getBudget() >= 2500 && isPlaying)
                        catView.setImage(catAfterImage);
                else if(!(catView.getImage() == catBeforeImage) && !(controller.getMap().getBudget() >= 2500 && isPlaying))
                        catView.setImage(catBeforeImage);
            }
        };
        animationTimer.start();
        Button catButton = CatButton.catButton(mapGroup, controller.getMap(), catView, mapScene);

    }

    private void setBuyDogView(Group mapGroup ,Scene mapScene) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!(dogView.getImage() == dogAfterImage) && controller.getMap().getBudget() >= 2600 && isPlaying)
                    dogView.setImage(dogAfterImage);
                else if(!(dogView.getImage() == dogBeforeImage) && !(controller.getMap().getBudget() >= 2600 && isPlaying))
                    dogView.setImage(dogBeforeImage);
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

        ImageView mapViewBackGround = new ImageView(backGroundImage);
        mapViewBackGround.setFitWidth(mapScene.getWidth());
        mapViewBackGround.setFitHeight(mapScene.getHeight());
        backGroundPane.getChildren().add(mapViewBackGround);
        backGroundMediaPlayer.play();


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

        ImageView underBarView = new ImageView(underBarImage);
        underBarView.relocate(0, 580);
        if (!mapGroup.getChildren().contains(underBarView))
            mapGroup.getChildren().add(underBarView);

        ImageView moneyAndTransportationView = new ImageView(upperBarImage);
        moneyAndTransportationView.relocate(620, 0);
        if (!mapGroup.getChildren().contains(moneyAndTransportationView))
            mapGroup.getChildren().add(moneyAndTransportationView);
        if (mode.equals("online"))
            if(!mapGroup.getChildren().contains(scoreBoardButton))
                mapGroup.getChildren().add(scoreBoardButton);
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
        MenuButton.inGameMenuButton(mapGroup, mapScene, this,  primaryStage, chooseMap, menu, controller);
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

        makeChatScene(mapScene, map, controller);
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
                if (mode.equals("online")) {
                    try {
                        updateJsonFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

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
                    if (time == 10){
                        map.addWildAnimal();
                        wildAnimalMediaPlayer.play();
                    }
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
        if(!(truckView.getImage() == truckImageL1) && controller.getMap().getTruck().getLevel() == 1)
            truckView.setImage(truckImageL1);
        else if(!(truckView.getImage() == truckImageL2) && controller.getMap().getTruck().getLevel() == 2)
            truckView.setImage(truckImageL2);
        else if(!(truckView.getImage() == truckImageL3) && controller.getMap().getTruck().getLevel() == 3)
            truckView.setImage(truckImageL3);
        else if(!(truckView.getImage() == truckImageL4) && controller.getMap().getTruck().getLevel() == 4)
            truckView.setImage(truckImageL4);
        truckView.relocate(220, 589);
    }

    private void showHelicopter(Map map, Group group) throws FileNotFoundException {
        if(!(helicopterView.getImage() == helicopterImageL1) && controller.getMap().getHelicopter().getLevel() == 1)
            helicopterView.setImage(helicopterImageL1);
        else if(!(helicopterView.getImage() == helicopterImageL2) && controller.getMap().getHelicopter().getLevel() == 2)
            helicopterView.setImage(helicopterImageL2);
        else if(!(helicopterView.getImage() == helicopterImageL3) && controller.getMap().getHelicopter().getLevel() == 3)
            helicopterView.setImage(helicopterImageL3);
        else if(!(helicopterView.getImage() == helicopterImageL4) && controller.getMap().getHelicopter().getLevel() == 4)
            helicopterView.setImage(helicopterImageL4);
        if (!map.getHelicopter().isInWareHouse()) {
            hButton.setVisible(false);
            helicopterView.setVisible(false);
        } else {
            hButton.setVisible(true);
            helicopterView.setVisible(true);
        }
    }

    private void showHelicopterInfo(Map map, Group group) throws FileNotFoundException {
            ImageView info = new ImageView(helpBoxImage2);
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
        backGroundMediaPlayer.pause();
        ChickenButton.pause();
        OstrichButton.pause();
        BuffaloButton.pause();
        DogButton.pause();
        CatButton.pause();
        GeneralButton.buttonAppearanceDefault(hButton, mapScene);
        GeneralButton.buttonAppearanceDefault(truckButton, mapScene);
    }

    public static void resume(){
        isResumed = true;
        isPlaying = true;
        backGroundMediaPlayer.play();
        ChickenButton.resume();
        OstrichButton.resume();
        BuffaloButton.resume();
        DogButton.resume();
        CatButton.resume();
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
                if (map.getWell().getLevel() == 3) {
                    upgradeView.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getWell().getLevel() < 3 && map.getWell().getMoneyForUpgrading() < map.getBudget()) {
                    upgradeView.setImage(purchaseButtonBlueImage);
                    upgradeView.setOpacity(1);
                }else if (map.getWell().getLevel() < 3 && map.getWell().getMoneyForUpgrading() >= map.getBudget()){
                    upgradeView.setImage(purchaseButtonGrayImage);
                    upgradeView.setOpacity(0.9);
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
                if (map.getWorkshops().get(0).getLevel() == 4) {
                    eggPlant.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getWorkshops().get(0).getLevel() < 4
                        && map.getWorkshops().get(0).getMoneyForUpgrading() <= map.getBudget()) {
                    eggPlant.setImage(purchaseButtonBlueImage);
                    eggPlant.setOpacity(1);
                }else if (map.getWorkshops().get(0).getLevel() < 4
                        && map.getWorkshops().get(0).getMoneyForUpgrading() > map.getBudget()){
                    eggPlant.setImage(purchaseButtonGrayImage);
                    eggPlant.setOpacity(0.9);
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
                if (map.getWorkshops().get(1).getLevel() == 4) {
                    cookieBakery.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getWorkshops().get(1).getLevel() < 4
                        && map.getWorkshops().get(1).getMoneyForUpgrading() <= map.getBudget()) {
                    cookieBakery.setImage(purchaseButtonBlueImage);
                    cookieBakery.setOpacity(1);
                }else if (map.getWorkshops().get(1).getLevel() < 4
                        && map.getWorkshops().get(1).getMoneyForUpgrading() > map.getBudget()){
                    cookieBakery.setImage(purchaseButtonGrayImage);
                    cookieBakery.setOpacity(0.9);
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
                if (map.getWorkshops().get(2).getLevel() == 4) {
                    cakeBakery.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getWorkshops().get(2).getLevel() < 4
                        && map.getWorkshops().get(2).getMoneyForUpgrading() <= map.getBudget()) {
                    cakeBakery.setImage(purchaseButtonBlueImage);
                    cakeBakery.setOpacity(1);
                }else if (map.getWorkshops().get(2).getLevel() < 4
                        && map.getWorkshops().get(2).getMoneyForUpgrading() > map.getBudget()){
                    cakeBakery.setImage(purchaseButtonGrayImage);
                    cakeBakery.setOpacity(0.9);
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
                if (map.getHelicopter().getLevel() == 4) {
                    upgradeHelicopter.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getHelicopter().getLevel() < 4 && map.getHelicopter().getMoneyForUpgrading() <
                        map.getBudget()) {
                    upgradeHelicopter.setImage(purchaseButtonBlueImage);
                    upgradeHelicopter.setOpacity(1);
                }else if (map.getHelicopter().getLevel() < 4 && map.getHelicopter().getMoneyForUpgrading() >=
                        map.getBudget()){
                    upgradeHelicopter.setImage(purchaseButtonGrayImage);
                    upgradeHelicopter.setOpacity(0.9);
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
                if(!(wareHouseView.getImage() == depotImageL1) && map.getWareHouse().getLevel() == 1)
                    wareHouseView.setImage(depotImageL1);
                else if(!(wareHouseView.getImage() == depotImageL2) && map.getWareHouse().getLevel() == 2)
                    wareHouseView.setImage(depotImageL2);
                else if(!(wareHouseView.getImage() == depotImageL3) && map.getWareHouse().getLevel() == 3)
                    wareHouseView.setImage(depotImageL3);
                else if(!(wareHouseView.getImage() == depotImageL4) && map.getWareHouse().getLevel() == 4)
                    wareHouseView.setImage(depotImageL4);
                text.setText(Integer.toString((int)map.getWareHouse().getMoneyForUpgrading()));
                if (map.getWareHouse().getLevel() == 4) {
                    upgradeWareHouse.setVisible(false);
                    text.setVisible(false);
                }
                if (map.getWareHouse().getLevel() < 4 && map.getWareHouse().getMoneyForUpgrading() <=
                        map.getBudget()) {
                    upgradeWareHouse.setImage(purchaseButtonBlueImage);
                    upgradeWareHouse.setOpacity(1);
                }else if (map.getWareHouse().getLevel() < 4 && map.getWareHouse().getMoneyForUpgrading() >
                        map.getBudget()){
                    upgradeWareHouse.setImage(purchaseButtonGrayImage);
                    upgradeWareHouse.setOpacity(0.9);
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
        ImageView info = new ImageView(helpBoxImage2);
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


    private void makeChatScene(Scene mapScene, Group mapGroup, Controller controller) throws FileNotFoundException {
        ImageView chat = new ImageView(new Image(new FileInputStream(CHATROOM + "chatButton.png")));
        chat.relocate(270, 0);
        chat.setScaleX(0.3);
        chat.setScaleY(0.3);
        if (mode.equals("online"))
            mapGroup.getChildren().add(chat);
        chatRoom = new ChatRoom(mapScene, controller, primaryStage);
        chatRoom.makeChatRoom();
        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(chatRoom.getChatScene());
            }
        });
    }

    private void updateJsonFile() throws IOException {
        InputStream inputStream = new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiPlayer\\players.json");
        Scanner scanner = new Scanner(inputStream);
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiPlayer\\players.json"));
        Gson serializer = new Gson();
        Gson deserializer = new Gson();
        while (scanner.hasNextLine()){
            String sentence = scanner.nextLine();
            profiles.add(deserializer.fromJson(sentence, Profile.class));
        }
        for (Profile profile :profiles) {
            if (controller instanceof ClientController) {
                if (profile.getUserName().equals(((ClientController) controller).getProfile().getUserName())){
                    profile.setBudget(controller.getMap().getBudget());
                }
            } else {
                if (profile.getUserName().equals(((ClientController) controller).getProfile().getUserName())){
                    profile.setBudget(controller.getMap().getBudget());
                }
            }
            serializer.toJson(profile, Profile.class, writer);
            writer.write("\n");
            writer.flush();
            writer.close();
        }

    }

}


