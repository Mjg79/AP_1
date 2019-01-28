package View.Helicopter;

import Controller.Controller;
import Model.MapAndCell.Map;
import Model.Products.Product;
import View.Buttons.GeneralButton;
import View.Map.MapView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeliCopterView {
    private transient Scene mapScene;
    private transient Scene hScene;
    private transient Group hGroup;
    private transient Stage stage;
    private transient Group mapGroup;
    private static final String HELICOPTERFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Helicopter\\";
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\";
    private static Image helicopterSceneImage;
    private static Image flourImage;
    private static Image oneAddGrayImage;
    private static Image oneAddBlueImage;
    private static Image cancelButtonImage;
    private static Image okButtonGreenImage;
    private static Image okButtonGrayImage;
    private static Image helicopterImageL1;
    private static Image helicopterImageL2;
    private static Image helicopterImageL3;
    private static Image helicopterImageL4;


    static {
        try {
            helicopterSceneImage = new Image(new FileInputStream(HELICOPTERFILE + "helicopterScene.jpg"));
            flourImage = new Image(new FileInputStream(PRODUCTFILE + "flour.png"));
            oneAddGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddGray.png"));
            oneAddBlueImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddBlue.png"));
            cancelButtonImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "cancelButton.png"));
            okButtonGreenImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGreen.png"));
            okButtonGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGray.png"));
            helicopterImageL1 = new Image(new FileInputStream(HELICOPTERFILE + "01.png"));
            helicopterImageL2 = new Image(new FileInputStream(HELICOPTERFILE + "02.png"));
            helicopterImageL3 = new Image(new FileInputStream(HELICOPTERFILE + "03.png"));
            helicopterImageL4 = new Image(new FileInputStream(HELICOPTERFILE + "04.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HeliCopterView(Stage stage, Scene mapScene, Scene hScene, Group hGroup
                          , Group mapGroup) {
        this.mapScene = mapScene;
        this.hScene = hScene;
        this.hGroup = hGroup;
        this.stage = stage;
        this.mapGroup = mapGroup;
    }

    private void initializeHelicopterScene() throws FileNotFoundException {
        ImageView backGroundView = new ImageView(helicopterSceneImage);
        hGroup.getChildren().add(backGroundView);
        ImageView flour = new ImageView(flourImage);
        hGroup.getChildren().add(flour);
        flour.relocate(70, 140);
        Text price = new Text("20");
        price.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price.relocate(200, 160);
        hGroup.getChildren().add(price);
    }

    private void flourAddingToHelicopter(Controller controller) {
        Button flour1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        flour1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(flour1, hScene);
        flour1.relocate(295, 148);
        addView.relocate(290, 143);
        hGroup.getChildren().add(flour1);

        flour1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime()
                        , "flour"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((controller.getMap().getHelicopter().getMapBudget() ==
                        controller.getMap().getHelicopter().getAllCost() &&
                        controller.getMap().getHelicopter().getAllCost() != 0) ||
                        controller.getMap().getBudget() == 0) {
                    addView.setImage(oneAddGrayImage);
                    flour1.setVisible(false);
                } else {
                    addView.setImage(oneAddBlueImage);
                    flour1.setVisible(true);
                }
            }
        };
        timer.start();
    }

    private void setCancelButton(Controller controller) throws FileNotFoundException {
        ImageView cancelView = new ImageView(cancelButtonImage);
        hGroup.getChildren().add(cancelView);
        cancelView.relocate(225, 650);
        Button cancelButton = new Button();
        hGroup.getChildren().add(cancelButton);
        cancelButton.relocate(234, 665);
        cancelButton.setOpacity(0);
        GeneralButton.buttonAppearanceWithCursor(cancelButton, hScene);
        cancelButton.setText("cancelButton                  \n");
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.getMap().getHelicopter().clear();
                controller.getMap().getHelicopter().clearSalesGood();
                MapView.resume();
                stage.setScene(mapScene);
            }
        });
    }



    private void setOkButton(Controller controller) {
        Button okButton = new Button("ok\n\n");
        okButton.setScaleX(5);
        okButton.relocate(110, 660);
        okButton.setOpacity(0);
        GeneralButton.buttonAppearanceWithCursor(okButton, hScene);
        ImageView okView = new ImageView();
        okView.relocate(50, 650);
        hGroup.getChildren().add(okView);
        hGroup.getChildren().add(okButton);
        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.getMap().goHelicopter();
                try {
                    controller.getMap().getHelicopter().goHelicopter(controller.getMap(), mapGroup);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                MapView.resume();
                stage.setScene(mapScene);
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getHelicopter().isHelicopterContainsAny()) {
                    okView.setImage(okButtonGreenImage);
                    okButton.setVisible(true);
                } else {
                    okView.setImage(okButtonGrayImage);
                    okButton.setVisible(false);
                }
            }
        };
        timer.start();
    }

    private void showSuitableHelicopter(Controller controller) {
        ImageView helicopterView = new ImageView();
        helicopterView.relocate(500, 400);
        hGroup.getChildren().add(helicopterView);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!(helicopterView.getImage() == helicopterImageL1) && controller.getMap().getHelicopter().getLevel() == 1)
                    helicopterView.setImage(helicopterImageL1);
                else if(!(helicopterView.getImage() == helicopterImageL2) && controller.getMap().getHelicopter().getLevel() == 2)
                    helicopterView.setImage(helicopterImageL2);
                else if(!(helicopterView.getImage() == helicopterImageL3) && controller.getMap().getHelicopter().getLevel() == 3)
                    helicopterView.setImage(helicopterImageL3);
                else if(!(helicopterView.getImage() == helicopterImageL4) && controller.getMap().getHelicopter().getLevel() == 4)
                    helicopterView.setImage(helicopterImageL4);
            }
        };
        timer.start();
    }

    public void helicopterShow(Controller controller) throws FileNotFoundException {
        initializeHelicopterScene();
        flourAddingToHelicopter(controller);
        showSuitableHelicopter(controller);
        setOkButton(controller);
        setCancelButton(controller);
        showAllCost(controller);
    }


    private void showAllCost(Controller controller) {
        Label text = new Label("0");
        hGroup.getChildren().add(text);
        text.relocate(230, 580);
        text.setStyle("-fx-text-fill: #ffe700; -fx-font-size: 30;-fx-font-family: 'Bauhaus 93'");
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
               text.setText(Integer.toString(controller.getMap().getHelicopter().getAllCost()));
            }
        };
        timer.start();
    }
}
