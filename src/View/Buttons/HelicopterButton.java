package View.Buttons;

import Controller.Controller;
import Model.MapAndCell.Map;
import View.Map.HelicopterScene;
import javafx.event.ActionEvent;
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

public class HelicopterButton {
    public static Button helicopterButton(Group mapGroup, Scene mapScene, Controller controller, Stage primaryStage) {
            final Image helicopterButtonImageL1 = new Image("View/farmFrenzySaveFiles/buttons/HelicopterButtons/01.png");
        final Image helicopterButtonImageL2 = new Image("View/farmFrenzySaveFiles/buttons/HelicopterButtons/02.png");
        final Image helicopterButtonImageL3 = new Image("View/farmFrenzySaveFiles/buttons/HelicopterButtons/03.png");
        final Image helicopterButtonImageL4 = new Image("View/farmFrenzySaveFiles/buttons/HelicopterButtons/04.png");
        ImageView helicopterButtonView = new ImageView();
        if(controller.getMap().getHelicopter().getLevel() == 1)
            helicopterButtonView.setImage(helicopterButtonImageL1);
        else if (controller.getMap().getHelicopter().getLevel() == 2)
            helicopterButtonView.setImage(helicopterButtonImageL2);
        else if(controller.getMap().getHelicopter().getLevel() == 3)
            helicopterButtonView.setImage(helicopterButtonImageL3);
        else
            helicopterButtonView.setImage(helicopterButtonImageL4);
        helicopterButtonView.setFitWidth(180);
        helicopterButtonView.setFitHeight(180);
        helicopterButtonView.setScaleX(1);
        Button helicopterButton = new Button();
        helicopterButton.setGraphic(helicopterButtonView);
        helicopterButton.setBackground(Background.EMPTY);
        helicopterButton.setPadding(Insets.EMPTY);
        helicopterButton.setBorder(Border.EMPTY);
        helicopterButton.setLayoutX(600);
        helicopterButton.setLayoutY(550);
        helicopterButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.HAND);
            }
        });
        helicopterButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapScene.setCursor(Cursor.DEFAULT);
            }
        });
        helicopterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: change scene to helicopterScene
                HelicopterScene helicopterScene = new HelicopterScene(controller, primaryStage);
                helicopterScene.goToHelicopterPlace();
            }
        });
        mapGroup.getChildren().add(helicopterButton);
        return helicopterButton;
    }

    public Button refreshHelicopterButton(Group mapGroup, Scene mapScene,Controller controller, Stage primaryStage){
        return HelicopterButton.helicopterButton(mapGroup, mapScene, controller, primaryStage);
    }
}
