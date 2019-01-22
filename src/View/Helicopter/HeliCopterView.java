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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeliCopterView {
    private Scene mapScene;
    private Scene hScene;
    private Group hGroup;
    private Stage stage;
    private MapView mapView;
    private Group mapGroup;
    private Controller controller;
    private static final String HELICOPTERFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Helicopter\\";
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";

    public HeliCopterView(Stage stage, Controller controller, Scene mapScene, Scene hScene, Group hGroup,
                          MapView mapView, Group mapGroup) {
        this.mapScene = mapScene;
        this.hScene = hScene;
        this.hGroup = hGroup;
        this.controller = controller;
        this.stage = stage;
        this.mapView = mapView;
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

    private void flourAddingToHelicopter() {
        Button flour1 = new Button("     1    ");
        GeneralButton.buttonAppearanceWithCursor(flour1, hScene);
        flour1.relocate(290, 150);
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
                if (controller.getMap().getBudget() < 20)
                    flour1.setVisible(false);
                else
                    flour1.setVisible(true);
            }
        };
        timer.start();
    }

    private void setCancelButton() throws FileNotFoundException {
        ImageView cancelView = new ImageView(new Image(new FileInputStream(HELICOPTERFILE + "cancelButton.png")));
        hGroup.getChildren().add(cancelView);
        cancelView.relocate(200, 650);
        Button cancelButton = new Button();
        hGroup.getChildren().add(cancelButton);
        cancelButton.relocate(200, 650);
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                controller.getMap().getHelicopter().
//                stage.setScene(mapScene);
            }
        });
    }

    private void setOkButton() {
        Button okButton = new Button("  ok  ");
        okButton.relocate(100, 650);
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
                stage.setScene(mapScene);
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (controller.getMap().getHelicopter().isHelicopterContainsAny())
                    okButton.setVisible(true);
                else
                    okButton.setVisible(false);
            }
        };
        timer.start();
    }

    private void showSuitableHelicopter() {
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

    public void helicopterShow() throws FileNotFoundException {
        initializeHelicopterScene();
        flourAddingToHelicopter();
        showSuitableHelicopter();
        setOkButton();
    }
}
