//package View.Buttons.WorkShop;
//
//import Model.MapAndCell.Map;
//import View.Buttons.GeneralButton;
//import View.Services.WorkShops.EggPowderPlant;
//import javafx.animation.Animation;
//import javafx.animation.AnimationTimer;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//
//import java.io.FileNotFoundException;
//
//public class EggPowderPlantButton extends GeneralButton {
//    private static boolean isPaused = false;
//    private static boolean isResumed = false;
//    private static boolean isPlaying = true;
//    private static Scene  mapScene;
//    private static Button workshop = new Button(" workshop ");
//    public static Button workShopButton(Group mapGroup, Scene mapScene, int x, int y, Map map) {
//        EggPowderPlantButton.mapScene = mapScene;
//        workshop.relocate(x, y);
//        GeneralButton.buttonAppearanceWithCursor(workshop, mapScene);
//        mapGroup.getChildren().add(workshop);
//        workshop.setOpacity(0);
//        workshop.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
//                try {
//                    if(isPlaying) {
//                        Animation animation = EggPowderPlant.eggPowderPlantAnimation(true,
//                                mapGroup, 1);
//                        animation.setCycleCount(5);
//                        AnimationTimer timer = new AnimationTimer() {
//                            int criteria = 0;
//
//                            @Override
//                            public void handle(long now) {
//                                if (isPaused) {
//                                    animation.pause();
//                                    isPaused = false;
//                                }
//                                if (isResumed) {
//                                    animation.play();
//                                    isResumed = false;
//                                }
//                                if (isPlaying)
//                                    criteria++;
//                                workshop.setVisible(false);
//                                System.out.println(criteria);
//                                if (criteria == 270) {
//                                    workshop.setVisible(true);
//                                    map.startWorkshop("EggPowderedPlant");
//                                    for (String string : map.getWareHouse().getGoods().keySet())
//                                        System.out.println(string + ": " + map.getWareHouse().getGoods().get(string));
//                                    this.stop();
//                                }
//                            }
//                        };
//                        timer.start();
//                        animation.play();
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//        return workshop;
//    }
//    public static void pause(){
//        isPaused = true;
//        isPlaying = false;
//        GeneralButton.buttonAppearanceDefault(workshop, mapScene);
//    }
//
//    public static void resume(){
//        isResumed = true;
//        isPlaying = true;
//        GeneralButton.buttonAppearanceWithCursor(workshop, mapScene);
//    }
//}
