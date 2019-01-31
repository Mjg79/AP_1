package Model.Transportation;


import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;
import Model.Products.Product;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Helicopter extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int mapBudget = 0;
    private int allCost = 0;
    private int numOfBoxes = 2;
    private double startTimeForSellingElements;
    private double endTimeForBuyingElements;
    private ArrayList<Element> salesGoods = new ArrayList<>();
    private boolean isInWareHouse = true;
    private  int timeDurationForWorking = 10;
    private boolean isStartedForAddingToBoxes = false;

    {
        for (int i = 0; i < numOfBoxes; i++) {
            Box box = new Box();
            boxes.add(box);
        }
        moneyForUpgrading = 400;
        level = 1;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getAllCost() {
        return allCost;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public double getStartTimeForSellingElements() {
        return startTimeForSellingElements;
    }

    public double getEndTimeForBuyingElements() {
        return endTimeForBuyingElements;
    }

    public  int getTimeDurationForWorking() {
        return timeDurationForWorking;
    }

    public boolean isStartedForAddingToBoxes() {
        return isStartedForAddingToBoxes;
    }

    @Override
    public void move(int finalX, int finalY) {

    }

    @Override
    public boolean upgrade() {
        if (level < 5) {
            numOfBoxes += 1;
            boxes.add(new Box());
            boxes.add(new Box());
            moneyForUpgrading += 100;
            timeDurationForWorking -= 2;
            level++;
            return true;
        }
        return false;
    }

    public int getMapBudget() {
        return mapBudget;
    }

    public void startForAddingElementToHelicopter(int budget) {
        if (!isStartedForAddingToBoxes) {
            this.mapBudget = budget;
            isStartedForAddingToBoxes = true;
        }
    }

    public void putOneCountOfAnElementInHelicopter(Element element, int budget) {
        this.startForAddingElementToHelicopter(budget);
        if (element.getPrice() <= mapBudget && isInWareHouse)
            for (Box box: boxes) {
                if ((!box.getContent().getName().equals("") && !box.getContent().getName().equals(element.getName())) ||
                        box.getCurrent() == box.getVolume())
                    continue;
                box.addElement(element, 1);
                if (box.getElement().get(box.getContent()) == 0)
                    box.addElement(element,1);
                salesGoods.add(element);
                allCost += element.getPrice();
                break;
            }

    }

    public boolean isHelicopterContainsAny() {
        for(Box box: boxes)
            if (box.isContainAny())
                return true;
        return false;

    }



    public void clearSalesGood() {
        salesGoods.clear();
    }

    public int startWorking(double time) {
        if(this.isInWareHouse && this.isHelicopterContainsAny()) {
            startTimeForSellingElements = time;
            isStartedForAddingToBoxes = false;
            endTimeForBuyingElements = time + timeDurationForWorking;
            isInWareHouse = false;
            for (Box box: boxes)
                System.out.println("box" + boxes.indexOf(box) + ": " + " ,productName: " +
                        box.getContent().getName() + " ,numbers: " + box.getElement().get(box.getContent()) +
                        " , isContainsAny: " + isHelicopterContainsAny());
            return mapBudget - allCost;
        }
        return mapBudget;
    }

    public boolean isInWareHouse() {
        return isInWareHouse;
    }



    public boolean checkWasHelicopterCameBackFromBazar(double time) {
        if (time > endTimeForBuyingElements && !isInWareHouse) {
            mapBudget = 0;
            allCost = 0;
            isInWareHouse = true;
            for (Box box : boxes)
                box.removeElement();
            return true;
        }
        return false;
    }

    public ArrayList<Element> getSalesGoods() {
        return salesGoods;
    }

    public void clear() {
        if (this.isInWareHouse && isHelicopterContainsAny()) {
            allCost = 0;
            mapBudget = 0;
            isStartedForAddingToBoxes = false;
            for (Box box : boxes)
                box.removeElement();
        }
    }



    ///////////////////////graphic_helicopter////////////////////////////
    private static final String HELICOPTERFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Helicopter\\";
    private transient ImageView helicopterGo = new ImageView();
    public void goHelicopter(Map map, Group mapGroup) throws FileNotFoundException {

        setImageHelicopterGo(mapGroup, map);
        helicopterGo.setScaleX(-1);
        mapGroup.getChildren().add(helicopterGo);
        SpriteAnimation helicopterAnimation = new SpriteAnimation(helicopterGo, Duration.millis(50), 6,
                3, 0, 0, 48, 48);
        helicopterAnimation.setAutoReverse(true);
        helicopterAnimation.setCycleCount(Animation.INDEFINITE);
        helicopterAnimation.play();

        Path path = new Path(new MoveTo(785, 20), new LineTo(980, 20));
        path.setVisible(false);
        mapGroup.getChildren().add(path);
        PathTransition pathTransition = new PathTransition(
                Duration.millis(map.getHelicopter().getTimeDurationForWorking() * 600), path, helicopterGo);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(2);
        pathTransition.play();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println(pathTransition.getCurrentTime());
                if ((int)pathTransition.getCurrentTime().toSeconds() ==
                        map.getHelicopter().getTimeDurationForWorking() / 2 && helicopterGo.getScaleX() != 1) {
                    helicopterGo.setScaleX(1);
                }
                if (pathTransition.getCurrentTime().toSeconds() == 0 && helicopterGo.getScaleX() == 1) {
                    helicopterAnimation.stop();
                    mapGroup.getChildren().remove(helicopterGo);
                    this.stop();
                }
            }
        };
        timer.start();
    }


    private void setImageHelicopterGo(Group mapGroup, Map map) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    helicopterGo.setImage(new Image(new FileInputStream(HELICOPTERFILE + "0" +
                            map.getHelicopter().getLevel() + "_mini.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();
    }

}