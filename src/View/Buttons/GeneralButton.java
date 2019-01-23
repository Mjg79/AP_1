package View.Buttons;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class GeneralButton {
     protected static final String animalBuy = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\buttons\\buyAnimal\\";


     public static void buyAnimalAppereance(Button button, ImageView buttonView) {
         button.setOnMouseEntered(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 buttonView.setOpacity(1);
             }
         });

         button.setOnMouseExited(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 buttonView.setOpacity(0.9);
             }
         });
     }

    public static void buyAnimalAppereanceDefault(Button button, ImageView buttonView) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonView.setOpacity(0.9);
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonView.setOpacity(0.9);
            }
        });
    }

     public static void buttonAppearanceWithCursor(Button button, Scene scene) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.HAND);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }

    public static void buttonAppearanceDefault(Button button, Scene scene) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }
}
