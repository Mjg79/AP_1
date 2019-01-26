package Model.Products.Forage;

import Model.Products.Product;
import View.Animations.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.text.Element;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Forage extends Product {

    public Forage(double firstTime) {
        super(firstTime, "forage");
    }

    {
        timeBeingInMap = 600; //10min
        name = "forage";
    }


    ///////////////GRAPHIC_SECTION///////////////////////////
    private transient ImageView imageView;

    public SpriteAnimation grassAnimation(Pane backGroundPane, int x, int y) throws FileNotFoundException {
        imageView = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                "farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\grass1.png")));
        imageView.relocate(x, y);
        imageView.setOpacity(0.8);
        backGroundPane.getChildren().add(imageView);
        new SpriteAnimation(imageView, Duration.millis(1), 12, 4, 0, 0,
                48, 48).play();
        return new SpriteAnimation(imageView, Duration.millis(500), 12, 4,
                0, 0, 48, 48);
    }
}


