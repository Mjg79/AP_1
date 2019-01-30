package View.Buttons;

import Controller.Controller;
import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.MapAndCell.Map;
import View.Map.MapView;
import com.google.gson.Gson;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.io.*;

public class MenuButton {
    public static Button inGameMenuButton(Group mapGroup, Scene mapScene, MapView mapView, Stage stage, Scene chooseMap,
                                          Scene menu, Controller controller){
        final  Image menuBarImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/menuBar.png");
        final Image menuButtonGrayImage = new Image("View/farmFrenzySaveFiles/buttons/menuButtonGray.png");
        final Image menuButtonBlueImage = new Image("View/farmFrenzySaveFiles/buttons/menuButtonBlue.png");
        final Image continueButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/continueButton.png");
        final Image mainMenuButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/mainMenuButton.png");
        final Image restartButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/restartButton.png");
        final Image mapButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/mapButton.png");
        final Image optiansButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/optiansButton.png");
        final Image helpButtonImage = new Image("View/farmFrenzySaveFiles/buttons/InGameMenuButtons/helpButton.png");
        ImageView menuBarView = new ImageView(menuBarImage);
        menuBarView.setY(92);
        menuBarView.setX(329);

        ImageView continueButtonView = new ImageView(continueButtonImage);
        Button continueButton = new Button();
        continueButton.setGraphic(continueButtonView);
        continueButton.setLayoutY(187);
        continueButton.setLayoutX(396);
        continueButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        continueButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        continueButton.setPadding(Insets.EMPTY);
        continueButton.setBorder(Border.EMPTY);
        continueButton.setBackground(Background.EMPTY);

        ImageView mainMenuButtonView = new ImageView(mainMenuButtonImage);
        Button mainMenuButton = new Button();
        mainMenuButton.setGraphic(mainMenuButtonView);
        mainMenuButton.setLayoutY(248);
        mainMenuButton.setLayoutX(396);
        mainMenuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        mainMenuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        mainMenuButton.setPadding(Insets.EMPTY);
        mainMenuButton.setBorder(Border.EMPTY);
        mainMenuButton.setBackground(Background.EMPTY);

        ImageView restartButtonView = new ImageView(restartButtonImage);
        Button restartButton = new Button();
        restartButton.setGraphic(restartButtonView);
        restartButton.setLayoutY(312);
        restartButton.setLayoutX(396);
        restartButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        restartButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        restartButton.setPadding(Insets.EMPTY);
        restartButton.setBorder(Border.EMPTY);
        restartButton.setBackground(Background.EMPTY);

        ImageView mapButtonView = new ImageView(mapButtonImage);
        Button mapButton = new Button();
        mapButton.setGraphic(mapButtonView);
        mapButton.setLayoutY(373);
        mapButton.setLayoutX(396);
        mapButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        mapButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        mapButton.setPadding(Insets.EMPTY);
        mapButton.setBorder(Border.EMPTY);
        mapButton.setBackground(Background.EMPTY);

        ImageView optiansButtonView = new ImageView(optiansButtonImage);
        Button optiansButton = new Button();
        optiansButton.setGraphic(optiansButtonView);
        optiansButton.setLayoutY(434);
        optiansButton.setLayoutX(396);
        optiansButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        optiansButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        optiansButton.setPadding(Insets.EMPTY);
        optiansButton.setBorder(Border.EMPTY);
        optiansButton.setBackground(Background.EMPTY);

        ImageView helpButtonView = new ImageView(helpButtonImage);
        helpButtonView.setY(499);
        helpButtonView.setX(395);
        Button helpButton = new Button();
        helpButton.setGraphic(helpButtonView);
        helpButton.setLayoutY(499);
        helpButton.setLayoutX(395);
        helpButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        helpButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        helpButton.setPadding(Insets.EMPTY);
        helpButton.setBorder(Border.EMPTY);
        helpButton.setBackground(Background.EMPTY);

        ImageView menuButtonGrayView = new ImageView(menuButtonGrayImage);

        ImageView menuButtonBlueView = new ImageView(menuButtonBlueImage);

        Button menuButton = new Button();
        menuButton.setGraphic(menuButtonBlueView);
        menuButton.setLayoutY(693);
        menuButton.setLayoutX(7);
        menuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        menuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        menuButton.setPadding(Insets.EMPTY);
        menuButton.setBorder(Border.EMPTY);
        menuButton.setBackground(Background.EMPTY);

        menuButton.setOnAction(event -> {
            if(menuButton.getGraphic().equals(menuButtonBlueView)){
                menuButton.setGraphic(menuButtonGrayView);
                mapGroup.getChildren().addAll(menuBarView, continueButton, mainMenuButton, restartButton, mapButton, optiansButton, helpButton);
                menuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mapScene.setCursor(Cursor.DEFAULT);
                    }
                });
                menuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mapScene.setCursor(Cursor.DEFAULT);
                    }
                });
                MapView.pause();
                //TODO:handle pause game and graying all game buttons
            }
        });
        mapGroup.getChildren().add(menuButton);

        continueButton.setOnAction(event -> {
            menuButton.setGraphic(menuButtonBlueView);
            mapGroup.getChildren().removeAll(menuBarView, continueButton, mainMenuButton, restartButton, mapButton, optiansButton, helpButton);
            menuButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mapScene.setCursor(Cursor.HAND);
                }
            });
            menuButton.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mapScene.setCursor(Cursor.DEFAULT);
                }
            });
            MapView.resume();
            //TODO:handle resume game and bluing game buttons if needed
        });

        mainMenuButton.setOnAction(event -> {
            Gson serializer = new Gson();
            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(mapView.getFileName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            serializer.toJson(mapView.getController().getMap(), Map.class, writer);
            try {
                for(LiveStock liveStock : controller.getMap().getLiveStocks())
                    mapGroup.getChildren().remove(liveStock.getLiveStockView());
                for(WildAnimal wildAnimal : controller.getMap().getWildAnimals())
                    mapGroup.getChildren().remove(wildAnimal.getWildAnimalView());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mapGroup.getChildren().removeAll();
            stage.setScene(menu);
        });

        restartButton.setOnAction(event -> {
            restartButton.setGraphic(menuButtonBlueView);
            mapGroup.getChildren().removeAll(menuBarView, continueButton, mainMenuButton, restartButton, mapButton, optiansButton, helpButton);
            Gson deserializer = new Gson();
            try {
                mapView.getController().setMap(deserializer.fromJson(new FileReader(mapView.getFileName()), Map.class));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            restartButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mapScene.setCursor(Cursor.HAND);
                }
            });
            restartButton.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mapScene.setCursor(Cursor.DEFAULT);
                }
            });
            MapView.resume();
            //TODO:handle resume game and bluing game buttons if needed
        });



        mapButton.setOnAction(event -> {
            Gson serializer = new Gson();
            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(mapView.getFileName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            serializer.toJson(mapView.getController().getMap(), Map.class, writer);
            try {
                for(LiveStock liveStock : controller.getMap().getLiveStocks())
                    mapGroup.getChildren().remove(liveStock.getLiveStockView());
                for(WildAnimal wildAnimal : controller.getMap().getWildAnimals())
                    mapGroup.getChildren().remove(wildAnimal.getWildAnimalView());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(chooseMap);
        });

        optiansButton.setOnAction(event -> {
            //TODO:handle going to optians
        });

        helpButton.setOnAction(event -> {
            //TODO:handle going to help
        });
        return menuButton;
    }
}
