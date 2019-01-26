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
    public HeliCopterView(Stage stage, Scene mapScene, Scene hScene, Group hGroup
                          , Group mapGroup) {
        this.mapScene = mapScene;
        this.hScene = hScene;
        this.hGroup = hGroup;
        this.stage = stage;
        this.mapGroup = mapGroup;
    }

    private void initializeHelicopterScene() throws FileNotFoundException {
        ImageView backGroundView = new ImageView(new Image(
                new FileInputStream(HELICOPTERFILE + "helicopterScene.jpg")));
        hGroup.getChildren().add(backGroundView);
        ImageView flour = new ImageView(new Image(new FileInputStream(PRODUCTFILE + "flour.png")));
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
                    try {
                        addView.setImage(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddGray.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    flour1.setVisible(false);
                } else {
                    try {
                        addView.setImage(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddBlue.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    flour1.setVisible(true);
                }
            }
        };
        timer.start();
    }

    private void setCancelButton(Controller controller) throws FileNotFoundException {
        ImageView cancelView = new ImageView(new Image(new FileInputStream(FARMFRENZYSAVEFILES
                + "cancelButton.png")));
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
                    try {
                        okView.setImage(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGreen.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    okButton.setVisible(true);
                } else {
                    try {
                        okView.setImage(new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGray.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
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
                try {
                    helicopterView.setImage(new Image(new FileInputStream(HELICOPTERFILE + "0" +
                            controller.getMap().getHelicopter().getLevel() + ".png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
