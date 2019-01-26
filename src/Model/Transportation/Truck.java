package Model.Transportation;


import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import View.Map.MapView;
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

public class Truck extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int wallet = 0;
    private int numOfBoxes = 2;
    private double startTimeForSellingElements;
    private double endTimeForSellingElements;
    private boolean isInWareHouse = true;
    private int countReturnToWareHouse = 0;
    private int timeDurationForWorking = 20;
    private final String TRUCKFILE= "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Truck\\";
    private transient ImageView truckView = new ImageView();
    {
        for (int i = 0; i < numOfBoxes; i++)
            boxes.add(new Box());
        moneyForUpgrading = 200;
        this.level = 1;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getWallet() {
        return wallet;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public double getStartTimeForSellingElements() {
        return startTimeForSellingElements;
    }

    public double getEndTimeForSellingElements() {
        return endTimeForSellingElements;
    }

    public int getTimeDurationForWorking() {
        return timeDurationForWorking;
    }

    @Override
    public void move(int finalX, int finalY) {
        //
    }

    @Override
    public boolean upgrade() {
        if (level < 3) {
            numOfBoxes += 2;
            boxes.add(new Box());
            boxes.add(new Box());
            timeDurationForWorking -= 2;
            moneyForUpgrading += 100;
            level++;
            return true;
        }
        return false;
    }


    private int putOneCountOfAnElementInTrunk(Element element) {
        for (Box box: boxes) {
            if (!(box.getContent().getClass().equals(element.getClass())) ||
                   box.getCurrent() == box.getVolume())
                continue;
            box.addElement(element, 1);
            return 0;
        }
        return 1;
    }

    private int putManyOfAnElementInTrunk(Element element, int count) {
        for (Box box: boxes) {
            if (!box.getContent().getClass().equals(element.getClass()) ||
                  box.getCurrent() == box.getVolume())
                continue;
            int countCapacity = (box.getVolume() - box.getCurrent()) / element.getVolume();
            if (countCapacity < count) {
                box.addElement(element, countCapacity);
                count -= countCapacity;
            } else {
                box.addElement(element, count);
                count = 0;
                break;
            }
        }

        return count;
    }
    public void putElementInTrunk(Element element, int count) {
        if (isInWareHouse)
            if (count == 1) {
                countReturnToWareHouse = this.putOneCountOfAnElementInTrunk(element);
            } else if (count > 1)
                countReturnToWareHouse = this.putManyOfAnElementInTrunk(element, count);

    }

    public int getCountReturnToWareHouse() {
        return countReturnToWareHouse;
    }


    public boolean isTruckContainsAny() {
        for(Box box: boxes)
            if (box.isContainAny())
                return true;
            return false;

    }


    public void startWorking(double time) {
        if(this.isInWareHouse && this.isTruckContainsAny()) {
            startTimeForSellingElements = time;
            endTimeForSellingElements = time + timeDurationForWorking;
            isInWareHouse = false;
            for (Box box : boxes) {
                this.wallet += box.getContent().getPrice() * box.getElement().get(box.getContent());
                box.removeElement();
            }
        }
        // wallet handeld in timer
    }

    public boolean isInWareHouse() {
        return isInWareHouse;
    }



    public void checkWasTruckCameBackFromBazar(double time) {
        if (time > endTimeForSellingElements && !isInWareHouse) {
            wallet = 0;
            isInWareHouse = true;
            for (Box box : boxes)
                box.removeElement();
        }
    }

    public void clear() {
        if (isInWareHouse && isTruckContainsAny())
            for (Box box: boxes)
                box.removeElement();
    }

    public void goTruck(Map map, Group mapGroup) throws FileNotFoundException {

        setImageOfTruck(mapGroup, map);
        truckView.setScaleX(-1);
        mapGroup.getChildren().add(truckView);
        SpriteAnimation truckAnimation = new SpriteAnimation(truckView, Duration.millis(50), 6,
                3, 0, 0, 48, 48);
        truckAnimation.setAutoReverse(true);
        truckAnimation.setCycleCount(Animation.INDEFINITE);
        truckAnimation.play();

        Path path = new Path(new MoveTo(785, 70), new LineTo(980, 70));
        path.setVisible(false);
        mapGroup.getChildren().add(path);
        PathTransition pathTransition = new PathTransition(
                Duration.millis(map.getTruck().getTimeDurationForWorking()* 600), path, truckView);
        pathTransition.setAutoReverse(true);
        pathTransition.setCycleCount(2);
        pathTransition.play();

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                MapView.getTruckButton().setVisible(false);
//                MapView.getTruckView().setVisible(false);
//                if ((int)pathTransition.getCurrentTime().toSeconds() ==
//                        map.getTruck().getTimeDurationForWorking() / 2 && truckView.getScaleX() != 1) {
//                    truckView.setScaleX(1);
//                }
//                if (pathTransition.getCurrentTime().toSeconds() == 0 && truckView.getScaleX() == 1) {
//                    truckAnimation.stop();
//                    mapGroup.getChildren().remove(truckView);
//                    MapView.getTruckButton().setVisible(true);
//                    MapView.getTruckView().setVisible(true);
//                    map.setBudget(map.getBudget()+map.getTruck().getWallet());
//                    this.stop();
//                }
//            }
//        };
//        timer.start();
    }

    private void setImageOfTruck(Group mapGroup, Map map) throws FileNotFoundException {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    truckView.setImage(new Image(new FileInputStream(TRUCKFILE + "0" +
                            map.getTruck().getLevel() + "_mini.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        animationTimer.start();
    }

}
