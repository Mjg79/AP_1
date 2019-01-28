package View.Map;

import Controller.Controller;
import Model.Animal.LiveStocks.AnimalType;
import Model.Animal.LiveStocks.LiveStock;
import Model.MapAndCell.Map;
import Model.Places.WareHouse;
import Model.Products.Product;
import Model.Transportation.Truck;
import View.Buttons.GeneralButton;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class WarehouseScene {
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\" ;
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private Label chickenText,cowText,ostrichText,wallet;
    private Stage primaryStage;
    private Truck truck;
    private Map map;
    private Controller controller;
    private HashMap<String,Integer> recentSell = new HashMap<>();
    private Group warehouseRoot = new Group();
    private final Scene wareHouseScene = new Scene(warehouseRoot,1000,750);
    private Scene backScene;
    private Group primaryGroup;
    public WarehouseScene(Controller controller, Scene backScene, Map map,Group primaryGroup)
            throws FileNotFoundException {
        this.backScene = backScene;
        this.primaryGroup =primaryGroup;
        this.controller = controller;
        wallet = new Label();
        wallet.setText("0");
        wallet.relocate(830,615);
        wallet.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 30;-fx-font-family: 'Bauhaus 93'");
        warehouseRoot.getChildren().add(wallet);
        this.map = controller.getMap();
        truck = map.getTruck();
        Image backGround = null;
        try {
            backGround = new Image(new FileInputStream(
                    "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\wareHousePlace.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView mapViewBackGround = new ImageView(backGround);
        mapViewBackGround.setFitWidth(1000);
        mapViewBackGround.setFitHeight(750);
        warehouseRoot.getChildren().add(mapViewBackGround);
    }

    public void show() {
        System.out.println("dahaneto service");
    }

    private void cancelOrOk() throws FileNotFoundException {
        warehouseRoot.getChildren().remove(wallet);
        wallet.setText(""+controller.getMap().getTruck().getWallet());
        warehouseRoot.getChildren().add(wallet);
        ImageView grayOk = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES+"okButtonGray.png")));
        grayOk.relocate(340,670);
        ImageView blueOk = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES+"okButtonGreen.png")));
        blueOk.relocate(340,670);
        if (controller.getMap().getTruck().getWallet() == 0){
            warehouseRoot.getChildren().add(grayOk);
        }else {
            blueOk.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MapView.resume();
                    primaryStage.setScene(backScene);
                    for(String string:recentSell.keySet()){
                        for (int i=0;i<recentSell.get(string);i++)
                            controller.getMap().addElementToTruck(new Product(controller.getMap().getFarmTime(),string),1);
                    }
                    controller.getMap().goTruck();
                    try {
                        controller.getMap().getTruck().goTruck(controller,primaryGroup);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            warehouseRoot.getChildren().add(blueOk);
        }
        ImageView cancel = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "cancelButton.png")));
        cancel.relocate(500,670);
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.getMap().getTruck().clear();
                controller.getMap().getTruck().setWallet(0);
                MapView.resume();
                warehouseRoot.getChildren().remove(2,warehouseRoot.getChildren().size());
                recentSell.clear();
                primaryStage.setScene(backScene);
            }
        });
        warehouseRoot.getChildren().add(cancel);
    }


    public void changeToWarehouse(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            addLivestocksToView(controller.getMap());
            cancelOrOk();
            addGoods();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(wareHouseScene);

    }

    private int getPriceOfLiveStock(String liveStock){
        return (new LiveStock(controller.getMap().getFarmTime(),liveStock).getPrice());
    }

    private int getPriceOfProduct(String good){
        return (new Product(controller.getMap().getFarmTime(),good)).getPrice();
    }

    private void addGoods() throws FileNotFoundException {
        int temp = 0;
        System.out.println(controller.getMap().getWareHouse().getGoods().toString());
        for (String good : controller.getMap().getWareHouse().getGoods().keySet()) {
            System.out.println(good);
            ImageView productImage = new ImageView(new Image(new FileInputStream(PRODUCTFILE + good + ".png")));
            productImage.relocate(40, 125 + temp * 40);
            Label productPrice = new Label("" + getPriceOfProduct(good));
            Label number = new Label("X " + controller.getMap().getWareHouse().getGoods().get(good));
            number.relocate(85, 125 + temp * 40);
            number.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family: 'A Spirit Of Doha Black'");
            productPrice.relocate(150, 125 + temp * 40);
            productPrice.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 30;-fx-font-family: 'A Spirit Of Doha Black'");
            ImageView add = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddBlue.png")));
            add.relocate(240, 130 + temp * 40);
            Button addGood = new Button();
            addGood.relocate(260, 133 + temp * 40);
            addGood.setScaleX(3);
            GeneralButton.buttonAppearanceWithCursor(addGood, wareHouseScene);
            addGood.setOpacity(0);
            addGood.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (recentSell.containsKey(good)) {
                        recentSell.replace(good, recentSell.get(good) + 1);
                    } else {
                        recentSell.put(good, 1);
                    }
                    controller.getMap().getTruck().setWallet(controller.getMap().getTruck().getWallet() + getPriceOfProduct(good));
                    try {
                        if (controller.getMap().getWareHouse().getGoods().get(good).equals(recentSell.get(good))) {
                            add.setImage(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddGray.png")));
                            addGood.setVisible(false);
                        }
                        cancelOrOk();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            temp++;
            warehouseRoot.getChildren().add(add);
            warehouseRoot.getChildren().add(productImage);
            warehouseRoot.getChildren().add(addGood);
            warehouseRoot.getChildren().add(number);
            warehouseRoot.getChildren().add(productPrice);
        }
    }


    private void addLivestocksToView(Map map) throws FileNotFoundException {
        System.out.println(map.getLiveStocks().toString());
        int chickenNumber = 0;
        int cowNumber = 0;
        int ostrichNumber = 0;
        int chickenPrice = 0;
        int ostrichPrice = 0;
        int cowPrice = 0;
        ImageView chicken = new ImageView(new Image(new FileInputStream(PRODUCTFILE+"chicken.png")));
        chicken.setScaleY(0.7);
        ImageView cow = new ImageView(new Image(new FileInputStream(PRODUCTFILE+"cow.png")));
        ImageView ostrich = new ImageView(new Image(new FileInputStream(PRODUCTFILE+"ostrich.png")));
        for(LiveStock liveStock:map.getLiveStocks()){
            if (liveStock.getType() == AnimalType.chicken) {
                chickenPrice = liveStock.getPrice();
                chickenNumber++;
            }else if (liveStock.getType() == AnimalType.ostrich){
                ostrichPrice = liveStock.getPrice();
                ostrichNumber++;
            }else {
                cowNumber++;
                cowPrice = liveStock.getPrice();
            }
        }
        int animalTypes = 0;
        if (chickenNumber != 0) {
            ImageView coin = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES+"coin.png")));
            coin.relocate(850,140);
            Label price = new Label(""+chickenPrice);
            price.relocate(835,140);
            warehouseRoot.getChildren().add(price);
            warehouseRoot.getChildren().add(coin);
            chickenText = new Label("X " + chickenNumber);
            chickenText.relocate(725,130+animalTypes*20);
            chickenText.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 20;-fx-font-family: 'Bauhaus 93'");
            chicken.relocate(680, 125 + animalTypes * 20);
            warehouseRoot.getChildren().add(chickenText);
            warehouseRoot.getChildren().add(chicken);
            animalTypes++;
        }
        if (ostrichNumber != 0) {
            ostrichText = new Label("X "+ ostrichNumber);
            ostrichText.relocate(725,130*animalTypes*20);
            ostrichText.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 20;-fx-font-family: 'Bauhaus 93'");
            ostrich.relocate(690, 125 + animalTypes * 20);
            warehouseRoot.getChildren().add(ostrichText);
            warehouseRoot.getChildren().add(ostrich);
            animalTypes++;
        }
        if (cowNumber != 0) {
            cowText = new Label("X "+cowNumber);
            cowText.relocate(725,130+20*animalTypes);
            cowText.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 20;-fx-font-family: 'Bauhaus 93'");
            cow.relocate(690, 125 + animalTypes * 20);
            warehouseRoot.getChildren().add(cowText);
            warehouseRoot.getChildren().add(cow);
        }

    }

}