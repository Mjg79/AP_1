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

public class WarehouseScene {
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\" ;
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private static Image wareHousePlaceImage;
    private static Image okButtonGrayImage;
    private static Image okButtonGreenImage;
    private static Image cancelButtonImage;
    private static Image eggImage;
    private static Image featherImage;
    private static Image hornImage;
    private static Image oneAddBlueImage;
    private static Image oneAddGrayImage;
    private static Image chickenImage;
    private static Image ostrichImage;
    private static Image buffaloImage;
    private static Image coinImage;

    static {
        try {
            wareHousePlaceImage = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\wareHousePlace.png"));
            okButtonGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES+"okButtonGray.png"));
            okButtonGreenImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES+"okButtonGreen.png"));
            cancelButtonImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "cancelButton.png"));
            eggImage = new Image(new FileInputStream(PRODUCTFILE+"egg.png"));
            featherImage = new Image(new FileInputStream(PRODUCTFILE+"feather.png"));
            hornImage = new Image(new FileInputStream(PRODUCTFILE+"horn.png"));
            oneAddBlueImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES+"oneAddBlue.png"));
            oneAddGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddGray.png"));
            chickenImage = new Image(new FileInputStream(PRODUCTFILE+"chicken.png"));
            ostrichImage = new Image(new FileInputStream(PRODUCTFILE+"ostrich.png"));
            buffaloImage = new Image(new FileInputStream(PRODUCTFILE+"buffalo.png"));
            coinImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES+"coin.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Label chickenText,buffaloText,ostrichText,wallet;
    private WareHouse wareHouse;
    private Stage primaryStage;
    private Truck truck;
    private Map map;
    private Group warehouseRoot = new Group();
    private final Scene wareHouseScene = new Scene(warehouseRoot,1000,750);
    private Scene backScene;
    public WarehouseScene(WareHouse wareHouse, Scene backScene, Map map) throws FileNotFoundException {
        this.wareHouse = wareHouse;
        this.backScene = backScene;
        wallet = new Label();
        wallet.setText("0");
        wallet.relocate(500,500);
        wallet.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 30;-fx-font-family: 'Bauhaus 93'");
        warehouseRoot.getChildren().add(wallet);
        this.map = map;
        truck = map.getTruck();
        ImageView mapViewBackGround = new ImageView(wareHousePlaceImage);
        mapViewBackGround.setFitWidth(1000);
        mapViewBackGround.setFitHeight(750);
        warehouseRoot.getChildren().add(mapViewBackGround);
    }

    private void cancelOrOk() throws FileNotFoundException {
        warehouseRoot.getChildren().remove(wallet);
        wallet.setText(""+truck.getWallet());
        warehouseRoot.getChildren().add(wallet);
        ImageView grayOk = new ImageView(okButtonGrayImage);
        grayOk.relocate(340,670);
        ImageView blueOk = new ImageView(okButtonGreenImage);
        blueOk.relocate(340,670);
        if (truck.getWallet() == 0){
            warehouseRoot.getChildren().add(grayOk);
        }else {
            blueOk.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MapView.resume();
                    primaryStage.setScene(backScene);
                    //TODO
                }
            });
            warehouseRoot.getChildren().add(blueOk);
        }
        ImageView cancel = new ImageView(cancelButtonImage);
        cancel.relocate(500,670);
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.getTruck().clear();
                MapView.resume();
                warehouseRoot.getChildren().remove(3,warehouseRoot.getChildren().size());
                primaryStage.setScene(backScene);
            }
        });
        warehouseRoot.getChildren().add(cancel);
    }


    public void changeToWarehouse(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            addLivestocksToView(map);
            addGoods();
            cancelOrOk();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(wareHouseScene);

    }

    private void addGoods() throws FileNotFoundException {
        int temp = 0;
        for(String good:wareHouse.getGoods().keySet()){
            ImageView productImage = null;
            if(!(productImage.getImage() == eggImage) && good.equals("egg"))
                productImage = new ImageView(eggImage);
            else if(!(productImage.getImage() == featherImage) && good.equals("feather"))
                productImage = new ImageView(featherImage);
            else if(!(productImage.getImage() == hornImage) && good.equals("horn"))
                productImage = new ImageView(hornImage);
            productImage.relocate(50,125+temp*20);
            Label productPrice = new Label(""+wareHouse.getGoods().get(good));
            productPrice.relocate(100,128+temp*20);
            productPrice.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 30;-fx-font-family: 'Bauhaus 93'");
            ImageView add = new ImageView(oneAddBlueImage);
            add.relocate(150,128*temp*20);
            add.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("clicked");
                    map.addElementToTruck(new Product(map.getFarmTime(),good),1);
                    try {
                        if (map.getWareHouse().getGoods().get(good) == 0) {
                            add.setImage(oneAddGrayImage);
                        }
                        cancelOrOk();
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });
            temp++;
            warehouseRoot.getChildren().add(add);
            warehouseRoot.getChildren().add(productImage);
            warehouseRoot.getChildren().add(productPrice);
        }
    }

    private void addLivestocksToView(Map map) throws FileNotFoundException {
        int chickenNumber = 0;
        int buffaloNumber = 0;
        int ostrichNumber = 0;
        int chickenPrice = 0;
        int ostrichPrice = 0;
        int buffaloPrice = 0;
        ImageView chicken = new ImageView(chickenImage);
        chicken.setScaleY(0.7);
        ImageView ostrich = new ImageView(ostrichImage);
        ImageView buffalo = new ImageView(buffaloImage);
        for(LiveStock liveStock:map.getLiveStocks()){
            if (liveStock.getType() == AnimalType.chicken) {
                chickenPrice = liveStock.getPrice();
                chickenNumber++;
            }else if (liveStock.getType() == AnimalType.ostrich){
                ostrichPrice = liveStock.getPrice();
                ostrichNumber++;
            }else {
                buffaloNumber++;
                buffaloPrice = liveStock.getPrice();
            }
        }
        int animalTypes = 0;
        if (chickenNumber != 0) {
            ImageView coin = new ImageView(coinImage);
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
        if (buffaloNumber != 0) {
            buffaloText = new Label("X "+buffaloNumber);
            buffaloText.relocate(725,130+20*animalTypes);
            buffaloText.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 20;-fx-font-family: 'Bauhaus 93'");
            buffalo.relocate(690, 125 + animalTypes * 20);
            warehouseRoot.getChildren().add(buffaloText);
            warehouseRoot.getChildren().add(buffalo);
            animalTypes++;
        }

    }

}
