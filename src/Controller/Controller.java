package Controller;
import com.google.gson.Gson;
import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.LiveStocks.LiveStock;
import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;
import Model.Products.Product;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class Controller {
    private Map map = new Map();
    private ServerSocket serverSocket = null;
    private Socket socket;
//    private View view = new View();
    private String instruction;
    private ArrayList<Element> elements = new ArrayList<>();
    private boolean isIdentified = false;
    private HashMap<String, Integer> missionNeeds = new HashMap<>();
    private ArrayList<String> listOfElements = new ArrayList<>();

    {
        elements.add(new LiveStock(0, "chicken"));
        elements.add(new LiveStock(0, "cow"));
        elements.add(new Dog());
        elements.add(new Cat("1"));
        elements.add(new Product(0, "cake"));
        elements.add(new Product(0, "egg"));
        elements.add(new Product(0, "feather"));
        elements.add(new Product(0, "horn"));
        elements.add(new Product(0, "cake"));
        elements.add(new Product(0, "cookie"));
        elements.add(new Product(0, "wool"));
        elements.add(new Product(0, "cloth"));
        elements.add(new Product(0, "sewing"));
        elements.add(new Product(0, "flour"));
        elements.add(new Product(0, "powderedEgg"));
        listOfElements.add("egg");
        listOfElements.add("feather");
        listOfElements.add("horn");
        listOfElements.add("cake");
        listOfElements.add("powderedEgg");
        listOfElements.add("cake");
        listOfElements.add("cookie");
        listOfElements.add("flour");
        listOfElements.add("cake");
        listOfElements.add("sewing");
        listOfElements.add("cloth");
        listOfElements.add("wool");
        listOfElements.add("chicken");
        listOfElements.add("ostrich");
        listOfElements.add("cow");
    }

    public void setInstruction(String string) {
        this.instruction = string;
    }

    private String getInstruction() {
        return instruction;
    }

    private boolean isScaleInMap(int x, int y) {
        if (Math.abs(x - 20) <= 15 && Math.abs(y - 20) <= 15) {
            isIdentified = true;
            return true;
        }
        return false;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void operateInstruction() {
        isIdentified = false;
        String[] split = instruction.split("\\s");
        if (getInstruction().matches("buy\\s(chicken|ostrich|cow)")) {
            this.buyAnimal(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("pickup\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.pickup(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        if (getInstruction().matches("cage\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.pickup(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

        }

        if (getInstruction().matches("plant\\s\\d+\\s\\d+")) {
            if (isScaleInMap(Integer.parseInt(split[1]), Integer.parseInt(split[2])))
                this.plant(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        if (getInstruction().matches("well")) {
            this.well();
            isIdentified = true;
        }
        if (getInstruction().matches("start\\s(eggPowderPlant|cakeBakery|cookieBakery|" +
                "sewingFactory|spinnery|weavingFactory)")) {
            this.startWorkshop(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("upgrade\\s(eggPowderPlant|cakeBakery|cookieBakery|" +
                "sewingFactory|spinnery|weavingFactory|cat|well|truck|helicopter|wareHouse)")) {
            this.upgrade(split[1]);
            isIdentified = true;
        }

//        if (getInstruction().matches("turn \\d+")) {
//            this.turn(Integer.parseInt(split[1]));
//            isIdentified = true;
//        }

        if (getInstruction().matches("(truck|helicopter)\\sadd\\s[a-z]+\\s\\d+")) {
            if (split[0].equals("truck"))
                this.addTruck(split[2], Integer.parseInt(split[3]));
            else if (split[0].equals("helicopter"))
                this.addHelicopter(split[2]);
            isIdentified = true;
        }

        if (getInstruction().matches("(truck|helicopter)\\sclear")) {
            if (split[0].equals("truck"))
                this.clearTruck();
            if (split[0].equals("helicopter"))
                this.clearHelicopter();
            isIdentified = true;
        }

        if (getInstruction().matches("(truck|helicopter)\\sgo")) {
            if (split[0].equals("truck"))
                this.goTruck();
            if (split[0].equals("helicopter"))
                this.goHelicopter();
            isIdentified = true;
        }

//        if (getInstruction().matches("print\\s(wareHouse|truck|helicopter|well|CakeBakery|CookieBakery|" +
//                "EggPowderPlant|SewingFactory|Spinnery|WeavingFactory|map|info)")) {
//            this.viewPrint(split[1]);
//            isIdentified = true;
//        }

        if (getInstruction().matches("loadGame\\s.+")) {
            this.loadGame(split[1]);
            isIdentified = true;
        }

        if (getInstruction().matches("saveGame\\s.+")) {
            this.saveGame(split[1]);
            isIdentified = true;
        }

//        if (getInstruction().matches("runMap\\s.+")) {
//            this.runMap(split[1]);
//            isIdentified = true;
//        }

        if (getInstruction().matches("load custom\\s.+")) {
            this.loadCustom(split[2]);
            isIdentified = true;
        }
        if (!isIdentified)
            System.out.println("command not found!");
    }

    private void buyAnimal(String animalName) {
        map.buyAnimal(animalName);
    }


    private void pickup(int x, int y) {
        map.pickUpAndPutInWareHouse(x, y);
    }

    private void cage(int x, int y) {
        map.cageWildAnimal(x, y);
    }

    private void plant(int x, int y) {
        map.plantForage((int) x, (int) y, map.getFarmTime());
    }

    private void well() {
        map.chargeWell();
    }

    private void startWorkshop(String workshopName) {
        map.startWorkshop(workshopName);
    }

    private void upgrade(String elementName) {
        map.upgrade(elementName);
    }

//    private void viewPrint(String place) {
//        view.print(map, place);
//    }

//    private void turn(int increase) {
//        map.turnMap(increase);
//    }

    private void addTruck(String name, int count) {
        for (Element element : elements) {
            if (element.getName().equals(name)) {
                map.addElementToTruck(element, count);
                break;
            }
        }
    }

    private void clearTruck() {
        map.clearTruck();
    }

    private void goTruck() {
        map.goTruck();
    }

    private void addHelicopter(String name) {
        for (Element element : elements)
            if (element.getName().equals(name)) {
                map.addElementToHelicopter(element);
                break;
            }
    }

    private void clearHelicopter() {
        map.clearHelicopter();
    }

    private void goHelicopter() {
        map.goHelicopter();
    }

    private void saveGame(String path) {
        Gson serializer = new Gson();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(path));
            serializer.toJson(this.map, Map.class, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String path) {
        Gson deserializer = new Gson();
        try {
            this.map = deserializer.fromJson(new FileReader(path), Map.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void runMap(String name) {
//        this.map = new Map(name, getMissionNeeds());
//    }

    private void loadCustom(String path) {
        Path pathe = Paths.get(path);
        try (Stream<Path> subPaths = Files.walk(pathe)){
            subPaths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMissionNeeds(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(path));
        JsonParser parser = new JsonParser();
        while (scanner.hasNextLine()) {
            JsonObject jsonObject = (JsonObject) parser.parse(scanner.nextLine());
            for (String name : listOfElements)
                if (jsonObject.has(name)) {
                    int num = jsonObject.get(name).getAsInt();
                    missionNeeds.put(name, num);

                }
        }
    }

    private HashMap<String, Integer> getMissionNeeds() {
        return missionNeeds;
    }


}

