package View.Helicopter;

import Controller.Controller;
import Model.Products.Product;
import View.Buttons.GeneralButton;
import View.Map.MapView;
import com.google.gson.Gson;
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

import java.io.*;
import java.util.HashMap;

public class HeliCopterViewOnline {
    private transient Scene mapScene;
    private transient Scene hScene;
    private transient Group hGroup;
    private transient Stage stage;
    private transient Group mapGroup;
    private transient HashMap pGoods;

    private transient Label flour = new Label();
    private transient Label powderedEgg = new Label();
    private transient Label cookie = new Label();
    private transient Label cake = new Label();
    private transient Label egg = new Label();
    private transient Label feather = new Label();
    private transient Label horn = new Label();

    private static final String HELICOPTERFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Helicopter\\";
    private static final String PRODUCTFILE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Products\\";
    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\";
    private static Image helicopterSceneImage;
    private static Image flourImage;
    private static Image cakeImage;
    private static Image powderedEggImage;
    private static Image cookieImage;
    private static Image featherImage;
    private static Image hornImage;
    private static Image eggImage;
    private static Image oneAddGrayImage;
    private static Image oneAddBlueImage;
    private static Image cancelButtonImage;
    private static Image okButtonGreenImage;
    private static Image okButtonGrayImage;
    private static Image helicopterImageL1;
    private static Image helicopterImageL2;
    private static Image helicopterImageL3;
    private static Image helicopterImageL4;


    static {
        try {
            helicopterSceneImage = new Image(new FileInputStream(HELICOPTERFILE + "helicopterScene.jpg"));
            flourImage = new Image(new FileInputStream(PRODUCTFILE + "flour.png"));
            cakeImage = new Image(new FileInputStream(PRODUCTFILE + "cake.png"));
            cookieImage = new Image(new FileInputStream(PRODUCTFILE + "cookie.png"));
            powderedEggImage = new Image(new FileInputStream(PRODUCTFILE + "powderedEgg.png"));
            featherImage = new Image(new FileInputStream(PRODUCTFILE + "feather.png"));
            hornImage = new Image(new FileInputStream(PRODUCTFILE + "horn.png"));
            eggImage = new Image(new FileInputStream(PRODUCTFILE + "egg.png"));
            oneAddGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddGray.png"));
            oneAddBlueImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "oneAddBlue.png"));
            cancelButtonImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "cancelButton.png"));
            okButtonGreenImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGreen.png"));
            okButtonGrayImage = new Image(new FileInputStream(FARMFRENZYSAVEFILES + "okButtonGray.png"));
            helicopterImageL1 = new Image(new FileInputStream(HELICOPTERFILE + "01.png"));
            helicopterImageL2 = new Image(new FileInputStream(HELICOPTERFILE + "02.png"));
            helicopterImageL3 = new Image(new FileInputStream(HELICOPTERFILE + "03.png"));
            helicopterImageL4 = new Image(new FileInputStream(HELICOPTERFILE + "04.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HeliCopterViewOnline(Stage stage, Scene mapScene, Scene hScene, Group hGroup
            , Group mapGroup) {
        this.mapScene = mapScene;
        this.hScene = hScene;
        this.hGroup = hGroup;
        this.stage = stage;
        this.mapGroup = mapGroup;
    }

    private void initializeHelicopterScene() throws FileNotFoundException {
        pGoods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES + "multiPlayer\\bazar.json"),
                HashMap.class);
        System.out.println(pGoods.toString());
        ImageView backGroundView = new ImageView(helicopterSceneImage);
        hGroup.getChildren().add(backGroundView);

        ImageView flourView = new ImageView(flourImage);
        ImageView cakeView = new ImageView(cakeImage);
        ImageView cookieView = new ImageView(cookieImage);
        ImageView powderedEggView = new ImageView(powderedEggImage);
        ImageView eggView = new ImageView(eggImage);
        ImageView featherView = new ImageView(featherImage);
        ImageView hornView = new ImageView(hornImage);

        hGroup.getChildren().add(flour);
        hGroup.getChildren().add(powderedEgg);
        hGroup.getChildren().add(cookie);
        hGroup.getChildren().add(cake);
        hGroup.getChildren().add(egg);
        hGroup.getChildren().add(feather);
        hGroup.getChildren().add(horn);


        hGroup.getChildren().add(flourView);
        hGroup.getChildren().add(cakeView);
        hGroup.getChildren().add(cookieView);
        hGroup.getChildren().add(eggView);
        hGroup.getChildren().add(powderedEggView);
        hGroup.getChildren().add(hornView);
        hGroup.getChildren().add(featherView);

        flourView.relocate(40, 140);
        cakeView.relocate(40, 260);
        cookieView.relocate(40, 220);
        powderedEggView.relocate(40, 180);
        eggView.relocate(40, 300);
        featherView.relocate(40, 340);
        hornView.relocate(40, 380);

        flour.relocate(90, 140);
        powderedEgg.relocate(90, 180);
        cookie.relocate(90, 220);
        cake.relocate(90, 260);
        egg.relocate(90, 300);
        feather.relocate(90, 340);
        horn.relocate(90, 380);


        Text price = new Text("10");
        Text price1 = new Text("50");
        Text price2 = new Text("100");
        Text price3 = new Text("100");
        Text price4 = new Text("10");
        Text price5 = new Text("100");
        Text price6 = new Text("1000");


        price.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price1.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price2.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price3.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price4.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price5.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");
        price6.setStyle("-fx-font-size: 30; -fx-fill: white; -fx-font-weight: bold; -fx-font-family: 'Bauhaus 93'");

        price.relocate(200, 160);
        price1.relocate(200, 200);
        price2.relocate(200, 240);
        price3.relocate(200, 280);
        price4.relocate(200, 320);
        price5.relocate(200, 360);
        price6.relocate(200, 400);

        hGroup.getChildren().add(price);
        hGroup.getChildren().add(price1);
        hGroup.getChildren().add(price2);
        hGroup.getChildren().add(price3);
        hGroup.getChildren().add(price4);
        hGroup.getChildren().add(price5);
        hGroup.getChildren().add(price6);

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
                try {
                    makeNewBazarFile("flour");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "flour"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    flour.setText("X " + (int)(double)goods.get("flour"));
                    flour.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("flour") == 0.0 ||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 10) {
                        addView.setImage(oneAddGrayImage);
                        flour1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        flour1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void powderedEggAddingToHelicopter(Controller controller) {
        Button powderedEgg1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        powderedEgg1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(powderedEgg1, hScene);
        powderedEgg1.relocate(295, 188);
        addView.relocate(290, 183);
        hGroup.getChildren().add(powderedEgg1);

        powderedEgg1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("powderedEgg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "powderedEgg"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    powderedEgg.setText("X " + (int)(double)goods.get("powderedEgg"));
                    powderedEgg.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("powderedEgg") == 0.0||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 50) {
                        addView.setImage(oneAddGrayImage);
                        powderedEgg1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        powderedEgg1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void cookieAddingToHelicopter(Controller controller) {
        Button cookie1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        cookie1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(cookie1, hScene);
        cookie1.relocate(295, 228);
        addView.relocate(290, 223);
        hGroup.getChildren().add(cookie1);

        cookie1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("cookie");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "cookie"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    cookie.setText("X " + (int)(double)goods.get("cookie"));
                    cookie.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("cookie") == 0.0||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 100) {
                        addView.setImage(oneAddGrayImage);
                        cookie1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        cookie1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void cakeAddingToHelicopter(Controller controller) {
        Button cake1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        cake1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(cake1, hScene);
        cake1.relocate(295, 268);
        addView.relocate(290, 263);
        hGroup.getChildren().add(cake1);

        cake1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("cake");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "cake"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    cake.setText("X " + (int)(double)goods.get("cake"));
                    cake.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("cake") == 0.0||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 100) {
                        addView.setImage(oneAddGrayImage);
                        cake1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        cake1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void eggAddingToHelicopter(Controller controller) {
        Button egg1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        egg1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(egg1, hScene);
        egg1.relocate(295, 308);
        addView.relocate(290, 303);
        hGroup.getChildren().add(egg1);

        egg1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("egg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "egg"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    egg.setText("X " + (int)(double)goods.get("egg"));
                    egg.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("egg") == 0.0||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 10) {
                        addView.setImage(oneAddGrayImage);
                        egg1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        egg1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void featherAddingToHelicopter(Controller controller) {
        Button feather1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        feather1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(feather1, hScene);
        feather1.relocate(295, 348);
        addView.relocate(290, 343);
        hGroup.getChildren().add(feather1);

        feather1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("feather");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(controller.getMap().getFarmTime() + 10
                        , "feather"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    feather.setText("X " + (int)(double)goods.get("feather"));
                    feather.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("feather") == 0.0||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 100) {
                        addView.setImage(oneAddGrayImage);
                        feather1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        feather1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }

    private void hornAddingToHelicopter(Controller controller) {
        Button horn1 = new Button(" 1    ");
        ImageView addView = new ImageView();
        horn1.setOpacity(0);
        hGroup.getChildren().add(addView);
        GeneralButton.buttonAppearanceWithCursor(horn1, hScene);
        horn1.relocate(295, 388);
        addView.relocate(290, 383);
        hGroup.getChildren().add(horn1);

        horn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    makeNewBazarFile("horn");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.getMap().addElementToHelicopter(new Product(
                        controller.getMap().getFarmTime() + 10, "horn"));
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES
                            + "multiPlayer\\bazar.json"), HashMap.class);
                    horn.setText("X " + (int)(double)goods.get("horn"));
                    horn.setStyle("-fx-text-fill: #fbfff0; -fx-font-size: 30;-fx-font-family:" +
                            " 'A Spirit Of Doha Black'");
                    if ((controller.getMap().getHelicopter().getMapBudget() ==
                            controller.getMap().getHelicopter().getAllCost() &&
                            controller.getMap().getHelicopter().getAllCost() != 0) ||
                            controller.getMap().getBudget() == 0 || (double)goods.get("horn") == 0.0 ||
                            controller.getMap().getBudget() <
                                    controller.getMap().getHelicopter().getAllCost() + 1000) {
                        addView.setImage(oneAddGrayImage);
                        horn1.setVisible(false);
                    } else {
                        addView.setImage(oneAddBlueImage);
                        horn1.setVisible(true);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }


    private void setCancelButton(Controller controller) throws FileNotFoundException {
        ImageView cancelView = new ImageView(cancelButtonImage);
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
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(
                            new FileOutputStream(FARMFRENZYSAVEFILES + "multiPlayer\\bazar.json"));
                    new Gson().toJson(pGoods, HashMap.class, writer);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                    pGoods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES + "multiPlayer\\bazar.json"),
                            HashMap.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
                    okView.setImage(okButtonGreenImage);
                    okButton.setVisible(true);
                } else {
                    okView.setImage(okButtonGrayImage);
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
                if(!(helicopterView.getImage() == helicopterImageL1) && controller.getMap().getHelicopter().getLevel() == 1)
                    helicopterView.setImage(helicopterImageL1);
                else if(!(helicopterView.getImage() == helicopterImageL2) && controller.getMap().getHelicopter().getLevel() == 2)
                    helicopterView.setImage(helicopterImageL2);
                else if(!(helicopterView.getImage() == helicopterImageL3) && controller.getMap().getHelicopter().getLevel() == 3)
                    helicopterView.setImage(helicopterImageL3);
                else if(!(helicopterView.getImage() == helicopterImageL4) && controller.getMap().getHelicopter().getLevel() == 4)
                    helicopterView.setImage(helicopterImageL4);
            }
        };
        timer.start();
    }

    public void helicopterShow(Controller controller) throws FileNotFoundException {
        initializeHelicopterScene();
        flourAddingToHelicopter(controller);
        powderedEggAddingToHelicopter(controller);
        cookieAddingToHelicopter(controller);
        cakeAddingToHelicopter(controller);
        eggAddingToHelicopter(controller);
        featherAddingToHelicopter(controller);
        hornAddingToHelicopter(controller);

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

    private void makeNewBazarFile(String good) throws IOException {
        HashMap goods = new Gson().fromJson(new FileReader(FARMFRENZYSAVEFILES + "multiPlayer\\bazar.json"),
                HashMap.class);
        goods.put(good, (double)goods.get(good) - 1);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(
                FARMFRENZYSAVEFILES + "multiPlayer\\bazar.json"));
        new Gson().toJson(goods, HashMap.class, writer);
        writer.flush();
    }
}
