package View.Map;

import Controller.Controller;
import Model.MapAndCell.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelicopterScene {
    final Image backGround = new Image("View/farmFrenzySaveFiles/farmFrenzyScenesDesign/helicopterPlace.jpg");
    final Image helicopterImageL1 = new Image("View/farmFrenzySaveFiles/farmFrenzyPlacesAndOthers/UI/Helicopter/01.png");
    final Image helicopterImageL2 = new Image("View/farmFrenzySaveFiles/farmFrenzyPlacesAndOthers/UI/Helicopter/02.png");
    final Image helicopterImageL3 = new Image("View/farmFrenzySaveFiles/farmFrenzyPlacesAndOthers/UI/Helicopter/03.png");
    final Image helicopterImageL4 = new Image("View/farmFrenzySaveFiles/farmFrenzyPlacesAndOthers/UI/Helicopter/04.png");
    private Controller controller;
    private Stage primaryStage;
    private ImageView helicopterView = new ImageView();
    private ImageView backGroundView = new ImageView(backGround);
    public HelicopterScene(Controller controller, Stage primaryStage){
        this.primaryStage = primaryStage;
        this.controller = controller;
        if(controller.getMap().getHelicopter().getLevel() == 1)
            helicopterView.setImage(helicopterImageL1);
        else if(controller.getMap().getHelicopter().getLevel() == 2)
            helicopterView.setImage(helicopterImageL2);
        else if(controller.getMap().getHelicopter().getLevel() == 3)
            helicopterView.setImage(helicopterImageL3);
        else
            helicopterView.setImage(helicopterImageL4);
        helicopterView.setLayoutX(530);
        helicopterView.setScaleY(0.9);
        helicopterView.setScaleX(0.8);
        helicopterView.setLayoutY(370);
        backGroundView.setLayoutY(0);
        backGroundView.setLayoutX(0);
    }

    public void goToHelicopterPlace(){
        Group group = new Group();
        group.getChildren().addAll(backGroundView, helicopterView);
        Scene helicopterScene = new Scene(group, 1000, 750);
        primaryStage.setScene(helicopterScene);
    }

}
