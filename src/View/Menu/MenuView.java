package View.Menu;

import Controller.Controller;
import Model.MapAndCell.Map;
import View.Helicopter.HeliCopterView;
import View.Map.MapView;
import View.Map.WarehouseScene;
import com.google.gson.Gson;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MenuView {
    private transient Text name = new Text();
    private static final String backGround =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png";
    private static final String accounts = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\accounts";

    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static final String farmFrenzyScenesDesign =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\";


    private String currentAccount = "";

    private boolean isHaveThisAccount(String string) {
        File file = new File(accounts);
        String[] names = file.list();
        String name;
        try {
            name = names[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        for (String s : names)
            if (string.equals(s))
                return true;
        return false;
    }

    private void createNewAccount(String name) throws IOException {
        Path source = Paths.get("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\sampleMaps");
        Path destination = Paths.get(accounts + "\\" + name);

        List<Path> sources = Files.walk(source).collect(toList());
        List<Path> destinations = sources.stream()
                .map(source::relativize)
                .map(destination::resolve)
                .collect(toList());

        for (int i = 0; i < sources.size(); i++) {
            Files.copy(sources.get(i), destinations.get(i));
        }
    }

    private void deleteAccount(String name) {
        File file = new File(accounts + "\\" + name);
        File[] dir = file.listFiles();
        for (File file1 : dir)
            file1.delete();
        file.delete();
    }

    private void setItemsMainMenu(Group mainMenu) throws FileNotFoundException {
        Rectangle items = new Rectangle(234, 253, 320, 218);
        items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                " -fx-border-radius: 5px");
        mainMenu.getChildren().add(items);

        Image image = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(180, 130);
        imageView.setFitWidth(441);
        imageView.setFitHeight(100);
        mainMenu.getChildren().add(imageView);
    }

    private void setNameSectionInMainMenu(Group mainMenu) {
        Rectangle nameSection = new Rectangle(257, 295, 270, 53);
        nameSection.setStroke(Color.rgb(213, 133, 37));
        nameSection.setFill(Color.rgb(136, 0, 21));
        mainMenu.getChildren().add(nameSection);

        Text hello = new Text("hello!!");
        hello.relocate(366, 273);
        hello.setStyle("-fx-font-family:  'A Spirit Of Doha Black'; -fx-font-size: 40;" +
                " -fx-fill: linear-gradient(from 0% 0% to 100% 100%, repeat, #ffd224 0%, black 80%)");
        mainMenu.getChildren().add(hello);

        mainMenu.getChildren().remove(name);
        if (!isHaveCurrentAccount())
            setCurrentAccount(putNameInSetItems());
        String[] split = getCurrentAccount().split("\\\\");
        System.out.println(getCurrentAccount());
        name.setText(split.length == 7 ? "   " + split[6] + "!" : " Your name!");
        name.relocate(352, 296);
        name.setStyle("-fx-font-family:  'Bodoni MT Black'; -fx-font-size: 15; -fx-fill: #ffd224");
        mainMenu.getChildren().add(name);
    }

    private void refreshMainMenu(Stage stage, Scene menu, Scene names, Scene choseMap, Group mainMenu, Group nameMenu)
            throws FileNotFoundException {
        mainMenu.getChildren().clear();
        mainMenu(stage, menu, names, choseMap, mainMenu, nameMenu);
    }


    private String putNameInSetItems() {
        File accountFile = new File(accounts);
        String name;
        String[] accountNames = accountFile.list();
        try {
            name = accountNames[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
        return name;
    }

    public void mainMenu(Stage primaryStage, Scene menu, Scene names, Scene map, Group mainMenu, Group nameMenu)
            throws FileNotFoundException {
        this.loadPictureOfBackGround(mainMenu, menu, backGround);
        this.setItemsMainMenu(mainMenu);
        this.setNameSectionInMainMenu(mainMenu);

        Button changeName = new Button("Click here to change the current player");
        changeName.relocate(264, 320);
        changeName.setStyle("-fx-background-color: #d57525; -fx-border-color: #81580a;" +
                " -fx-font-family: 'Bodoni MT Black'; -fx-text-fill: #fff27a;" +
                "  -fx-border-radius: 5; -fx-background-radius: 14; -fx-border-width: 3;}" +
                ".button :hover { -fx-background-color: red");
        mainMenu.getChildren().add(changeName);
        buttonOpacityChanged(changeName);
        changeName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    refreshNameMenu(primaryStage, menu, nameMenu, names, mainMenu, map);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                primaryStage.setScene(names);
            }
        });
        Button play = new Button("       Play :)     ");
        play.setFont(Font.font("Bauhaus 93", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
        play.setStyle("-fx-background-color: #6993e3; -fx-border-color: #3e48cc; -fx-border-width: 3" +
                ";  -fx-text-fill: linear-gradient(from 0% 0% to 100% 250%, repeat, whitesmoke 0%, black 60%);" +
                " -fx-border-radius: 5; -fx-background-radius: 10;");
        play.relocate(331, 363);
        buttonOpacityChanged(play);
        mainMenu.getChildren().add(play);

        play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (putNameInSetItems().equals("")) {
                    try {
                        refreshNameMenu(primaryStage, menu, nameMenu, names, mainMenu, map);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    primaryStage.setScene(names);
                } else
                    primaryStage.setScene(map);
            }
        });

        Button exit = new Button("       Exit :(      ");
        exit.setFont(Font.font("Bauhaus 93", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
        exit.setStyle("-fx-background-color: #6993e3; -fx-border-color: #3e48cc; -fx-border-width: 3" +
                "; -fx-font-family: 'Bauhaus 93'; " +
                " -fx-text-fill: linear-gradient(from 0% 0% to 100% 250%, repeat, whitesmoke 0%, black 60%);" +
                "-fx-border-radius: 5; -fx-background-radius: 10;");
        exit.relocate(331, 413);

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        buttonOpacityChanged(exit);
        mainMenu.getChildren().add(exit);

    }

    private void initializeSetInNameMenu(Group nameMenu, Scene names) throws FileNotFoundException {
        this.loadPictureOfBackGround(nameMenu, names, backGround);
        Rectangle rectangle = new Rectangle(246, 76, 597 - 265, 491 - 84);
        rectangle.setFill(Color.rgb(226, 223, 200));
        nameMenu.getChildren().add(rectangle);


        Image image1 = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\jigar.jpg"));
        ImageView imageView1 = new ImageView(image1);
        nameMenu.getChildren().add(imageView1);
        imageView1.setX(250);
        imageView1.setY(80);
        imageView1.setFitWidth(592 - 269);
        imageView1.setFitHeight(400);

        Text enterYourName = new Text("Enter your name.");
        enterYourName.relocate(342, 90);
        enterYourName.setStyle("-fx-fill: #6d473b; -fx-font-family: 'Bodoni MT Black'; -fx-font-size: 18;" +
                " -fx-font-style: italic;");
        nameMenu.getChildren().add(enterYourName);


    }

    private void okButtonStyle(Button ok) {
        ok.relocate(285, 423);
        ok.setStyle("-fx-background-color: #51e6e1; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #192dea; -fx-border-width: 3px");


    }

    private void setCurrentAccount(String name) {
        if (!name.equals(""))
            currentAccount = accounts + "\\" + name;
    }

    private String getCurrentAccount() {
        return currentAccount;
    }

    private boolean isHaveCurrentAccount() {
        if (currentAccount.length() > accounts.length())
            return true;
        return false;
    }

    private void cancelButtonStyle(Button cancel) {
        cancel.relocate(455, 423);
        cancel.setStyle("-fx-background-color: #ff3d05; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ;-fx-border-radius: 5px; -fx-border-color: #ea003a;" +
                " -fx-border-width: 3px; -fx-font-size: 12;");
    }

    private void newButtonStyle(Button New) {
        New.relocate(285, 423);
        New.setStyle("-fx-background-color: #b5e627; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #03ea39; -fx-border-width: 3px;");

    }

    private void buttonOpacityChanged(Button button) {
        button.setOpacity(0.8);
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(1);
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(0.8);
            }
        });
    }


    private void deleteButtonStyle(Button delete) {
        delete.relocate(370, 443);
        delete.setStyle("-fx-background-color: #ffa70b; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ;-fx-border-radius: 5px; -fx-border-color: #ea6b0a;" +
                " -fx-border-width: 3px; -fx-font-size: 12");

    }

    private boolean showAvailableAccounts(Group nameMenu) {
        File file = new File(accounts);
        File[] names = file.listFiles();
        try {
            String name = names[0].getName();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        for (int i = 0; i < names.length; i++) {
            Text text = new Text();
            text.setText(names[i].getName());
            text.setStyle("-fx-stroke: #000000; -fx-stroke-width: 1.4px; -fx-font-family: 'Cooper Black'; " +
                    "-fx-font-weight: bold;-fx-font-style: italic; -fx-font-size: 18; -fx-fill: #ffffff");
            text.relocate(374, 175 + 25 * i);
            nameMenu.getChildren().add(text);
        }
        return true;
    }

    private void refreshNameMenu(Stage stage, Scene menu, Group nameMenu, Scene names, Group mainMenu, Scene choseMap)
            throws FileNotFoundException {
        nameMenu.getChildren().clear();
        nameMenu(stage, menu, nameMenu, names, mainMenu, choseMap);
    }

    public void nameMenu(Stage stage, Scene menu, Group nameMenu, Scene names, Group mainMenu, Scene choseMap)
            throws FileNotFoundException {
        this.initializeSetInNameMenu(nameMenu, names);

        TextField enterName = new TextField();
        enterName.relocate(329, 120);
        enterName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterName.clear();
            }
        });
        enterName.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: bold; -fx-text-fill: #4c260a;");
        nameMenu.getChildren().add(enterName);

        showAvailableAccounts(nameMenu);

        Button cancel = new Button("   cancel   ");
        this.cancelButtonStyle(cancel);
        nameMenu.getChildren().add(cancel);
        buttonOpacityChanged(cancel);

        Button delete = new Button("   delete   ");
        this.deleteButtonStyle(delete);
        buttonOpacityChanged(delete);

        Button New = new Button("      new     ");
        this.newButtonStyle(New);
        nameMenu.getChildren().add(New);
        buttonOpacityChanged(New);


        Button ok = new Button("      Ok     ");
        this.okButtonStyle(ok);
        buttonOpacityChanged(ok);

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deleteAccount(enterName.getText());
                if (getCurrentAccount().equals(accounts + "\\" + enterName.getText()))
                    setCurrentAccount(putNameInSetItems());
                try {
                    refreshMainMenu(stage, menu, names, choseMap, mainMenu, nameMenu);
                    refreshNameMenu(stage, menu, nameMenu, names, mainMenu, choseMap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                enterName.clear();
            }
        });

        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCurrentAccount(enterName.getText());
                mainMenu.getChildren().clear();
                try {
                    refreshMainMenu(stage, menu, names, choseMap, mainMenu, nameMenu);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                stage.setScene(menu);
                enterName.clear();
            }
        });

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(menu);
                try {
                    refreshMainMenu(stage, menu, names, choseMap, mainMenu, nameMenu);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                enterName.clear();
            }
        });

        New.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    createNewAccount(enterName.getText());
                    refreshNameMenu(stage, menu, nameMenu, names, mainMenu, choseMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                enterName.clear();
            }
        });

        AnimationTimer buttonsSituation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (enterName.getText().equals(""))
                    nameMenu.getChildren().removeAll(New, ok, delete);
                else if (!nameMenu.getChildren().contains(New) && !isHaveThisAccount(enterName.getText())) {
                    nameMenu.getChildren().add(New);
                    nameMenu.getChildren().removeAll(ok, delete);
                }
                if (isHaveThisAccount(enterName.getText()) && !nameMenu.getChildren().contains(ok)) {
                    nameMenu.getChildren().add(ok);
                    nameMenu.getChildren().add(delete);
                    nameMenu.getChildren().removeAll(New);
                }
            }
        };
        buttonsSituation.start();

    }


    private void loadPictureOfBackGround(Group group, Scene scene, String path) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(scene.getHeight());
        imageView.setFitWidth(scene.getWidth());
        group.getChildren().add(imageView);
    }

    private void intializeMapChooseMenu(Group chooseMap, Scene choseMap) throws FileNotFoundException {
        this.loadPictureOfBackGround(chooseMap, choseMap, backGround);

        Rectangle rectangle = new Rectangle(103, 55, 602, 432);
        rectangle.setFill(Color.rgb(226, 223, 200));
        chooseMap.getChildren().add(rectangle);

        Rectangle rectangle1 = new Rectangle(108, 58, 594, 426);
        rectangle1.setStyle("-fx-background-color: #000000");
        chooseMap.getChildren().add(rectangle1);

        Rectangle rectangle2 = new Rectangle(111, 62, 587, 419);
        rectangle2.setFill(Color.rgb(213, 133, 37));
        rectangle2.setStyle("-fx-border-color: #a9651b; -fx-background-color: #D58525");
        chooseMap.getChildren().add(rectangle2);

        Text chooseYourMap = new Text("Choose your map.");
        chooseYourMap.relocate(315, 69);
        chooseYourMap.setStyle("-fx-fill: #000000; -fx-font-family: 'Bodoni MT Black'; -fx-font-size: 18;" +
                "-fx-font-weight: bold; -fx-font-style: italic");
        chooseMap.getChildren().add(chooseYourMap);
    }


    public void mapChooseMenu(Stage stage, Group chooseMap, Scene choseMap, Scene menu
    , Controller controller) throws FileNotFoundException {

        Group map = new Group();
        Scene mapScene = new Scene(map, 1000, 750);

        Group hGroup = new Group();
        Scene hScene = new Scene(hGroup, 1000, 750);
        Map map1 = controller.getMap();
        WarehouseScene warehouseScene = new WarehouseScene(controller, mapScene, controller.getMap(), map);

        MapView mapView = new MapView(controller,stage ,mapScene,  hScene, choseMap, menu,warehouseScene);

        HeliCopterView heliCopterView = new HeliCopterView(stage, mapScene,
                hScene, hGroup, map);
        heliCopterView.helicopterShow(controller);

        this.intializeMapChooseMenu(chooseMap, choseMap);
        Button backToMenu = new Button("  backToMenu  ");
        ArrayList<Button> mapButtons = new ArrayList<>();
        backToMenu.relocate(520, 425);
        chooseMap.getChildren().add(backToMenu);
        backToMenu.setStyle("-fx-font-family: 'Bodoni MT Black'; -fx-font-size: 16; -fx-font-style: italic;" +
                " -fx-text-fill: black; -fx-background-color: white ;" +
                "-fx-border-color: grey; -fx-border-width: 3px; -fx-border-radius: 10px;" +
                " -fx-background-radius: 15px");
        buttonOpacityChanged(backToMenu);
        backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(menu);
            }
        });
        for (int i = 0; i < 6; i++)
        makeButtons(chooseMap, stage, mapScene, controller,  mapView, map, 370, 120 + 50 * i,
                "map" + Integer.toString(i + 1));

    }

    private void makeButtons(Group menuGroup, Stage stage, Scene mapScene, Controller controller, MapView mapView,
                             Group map, int x, int y, String name) {
        Button map1 = new Button(name);
        setButtonChoosable(map1);
        menuGroup.getChildren().add(map1);
        map1.relocate(x, y);
            map1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Gson deserializer = new Gson();
                    try {
                        controller.setMap(deserializer.fromJson(new FileReader(getCurrentAccount()
                                + "\\" + name + ".json"), Map.class));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }
                    try {
                        mapView.getWarehouseScene().show();
                        mapView.gameMap(map, mapScene, controller.getMap());
                        mapView.setFileName(getCurrentAccount() + "\\" + name + ".json");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    stage.setScene(mapScene);

                }
            });
    }


    private Button mapButtonChoosable(Button map, Group chooseMap, String mapName,
                                      int x, int y, Scene choseMap) {
        map.setText(mapName);
        map.relocate(x, y);
        chooseMap.getChildren().add(map);
        return map;
    }

    private void setButtonChoosable(Button button) {
        button.setStyle("-fx-font-family: 'Bodoni MT Black'; -fx-font-size: 16; -fx-font-style: italic;" +
                " -fx-text-fill: white; -fx-background-color: blue; " +
                "-fx-border-color: #5d0b1b; -fx-border-width: 3px; -fx-border-radius: 10px;" +
                " -fx-background-radius: 15px; visibility: true");
    }

    private void setButtonUnChoosable(Button button) {
        button.setStyle("-fx-font-family: 'Bodoni MT Black'; -fx-font-size: 16; -fx-font-style: italic;" +
                " -fx-text-fill: white; -fx-background-color: #000000; " +
                "-fx-border-color: #5d0b1b; -fx-border-width: 3px; -fx-border-radius: 10px;" +
                " -fx-background-radius: 15px; visibility: false");
    }

    private void showButtons(ArrayList<Button> buttons) {
        Gson desserializer = new Gson();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 1; i < buttons.size(); i++) {
                    System.out.println(getCurrentAccount() + "\\" + "map1.json");
                    try {
                      Map map = null;
                        if (!getCurrentAccount().equals("")) {
                            map = desserializer.fromJson(new FileReader(getCurrentAccount() + "\\" +
                                    "map1" + ".json"), Map.class);
                            if (map.isMissionCompleted())
                                setButtonChoosable(buttons.get(0));
                        } else
                            setButtonUnChoosable(buttons.get(0));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        this.stop();
                    }

                }
            }

        };
        timer.start();
    }
}
