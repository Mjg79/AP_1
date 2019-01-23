package Model.MapAndCell;

import Model.ElementAndBoxAndDirection.Element;
import Model.Products.Product;
import Model.Products.Forage.Forage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.Animal.*;
import Model.Animal.LiveStocks.*;
import Model.Animal.WildAnimals.WildAnimal;
import View.Buttons.GeneralButton;
import View.Buttons.ProductButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell extends Element {
	private ArrayList<LiveStock> liveStocks = new ArrayList<>();
	private ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
	private ArrayList<Cat> cats = new ArrayList<>();
	private ArrayList<Dog> dogs = new ArrayList<>();
	private ArrayList<Forage> forages = new ArrayList<>();
	private ArrayList<Product> products = new ArrayList<>();
	private double farmTime = 0;
	private static final String productFile = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\products\\";
	private ArrayList<ImageView> imageViews = new ArrayList<>();
	private  Button button = new Button();

	{
		name = "cell";
	}

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addElement(Element element) {
		if (element instanceof LiveStock)
			liveStocks.add((LiveStock) element);
		if (element instanceof WildAnimal)
			wildAnimals.add((WildAnimal) element);
		if (element instanceof Cat)
			cats.add((Cat) element);
		if (element instanceof Dog)
			dogs.add((Dog) element);
		if (element instanceof Product)
			products.add((Product) element);
		if (element instanceof Forage)
			forages.add((Forage) element);

	}

	void removeElement(Element element) {
		if (element instanceof WildAnimal)
			wildAnimals.remove(element);
		if (element instanceof Cat)
			cats.remove(element);
		if (element instanceof Product)
			products.remove(element);
		if (element instanceof Forage)
			forages.remove(element);
		if (element instanceof LiveStock)
			liveStocks.remove(element);

	}

	public void removeDog(Dog dog, Group mapGroup) {
		mapGroup.getChildren().remove(dog.getDogView());
		dogs.remove(dog);
	}

	public ArrayList<LiveStock> getLiveStocks() {
		return this.liveStocks;
	}

	public ArrayList<WildAnimal> getWildAnimals() {
		return this.wildAnimals;
	}

	public ArrayList<Dog> getDogs() {
		return this.dogs;
	}

	public ArrayList<Cat> getCats() {
		return this.cats;
	}

	public ArrayList<Product> getProducts() {
		return this.products;
	}

	public ArrayList<Forage> getForages() {
		return this.forages;
	}

	public boolean isHaveForage() {
		if (forages.isEmpty())
			return false;
		return true;
	}

	public void removeAllLiveStocks() {
		this.liveStocks.clear();
	}

	public void removeAllCats() {
		this.cats.clear();
	}

	public void removeAllDogs() {
		this.dogs.clear();
	}

	public void removeAllProducts() {
		this.products.clear();
	}

	public void removeAllWildAnimals() {
		this.wildAnimals.clear();
	}

	@Override
	public void move(int finalX, int finalY) {

	}

	public void removeOneForage() {
		forages.remove(0);
	}

	public void removeAllElements() {
		this.products.clear();
		this.cats.clear();
		this.liveStocks.clear();
	}

	@Override
	public boolean upgrade() {
		return false;

	}


	//////////////////GERAPHIC_CELL///////////////////////////
    private int previousNumberOfCell = 0;


	private void addForage() throws FileNotFoundException {
		for(Forage forage: forages)
			imageViews.add(new ImageView(new Image(new FileInputStream(productFile + "grass1.png"))));
	}

	private void addEgg(Group group, Scene scene, Map map, Product product) throws FileNotFoundException {
		if (product.getName().equals("egg"))
			imageViews.add(new ImageView(new Image(new FileInputStream(productFile + "Egg\\normal_2.png"))));

	}

	private void addPowderEgg(Group group, Scene scene , Map map, Product product) throws FileNotFoundException {
		if (product.getName().equals("powderedEgg"))
			imageViews.add(new ImageView(new Image(new FileInputStream(productFile + "EggPowder.png"))));
	}

	private void addFlour(Group group, Scene scene , Map map, Product product) throws FileNotFoundException {
		if (product.getName().equals("flour"))
			imageViews.add(new ImageView(new Image(new FileInputStream(productFile + "flour.png"))));
	}



	private void setViewOfCell(Group group, Scene scene, Map map) throws FileNotFoundException {
		if (previousNumberOfCell != products.size()) {
			for (ImageView imageView : imageViews)
				group.getChildren().remove(imageView);
			imageViews = new ArrayList<>();
			group.getChildren().remove(button);
			previousNumberOfCell = products.size();
			for (Product product: products) {
				addEgg(group, scene, map, product);
				addPowderEgg(group, scene, map, product);
				addFlour(group, scene, map, product);
			}
		}
	}



	public void showCell(Group group, Scene scene, Map map) throws FileNotFoundException {
		setViewOfCell(group, scene,  map);
		for (ImageView imageView: imageViews) {
			imageView.relocate(250 + 12 * this.getX() + imageViews.indexOf(imageView),
					250 + 7 * this.getY() + imageViews.indexOf(imageView));
			if (!group.getChildren().contains(imageView))
			group.getChildren().add(imageView);
		}
		if (imageViews.size() != 0 && !group.getChildren().contains(button))
				button = ProductButton.productButton(button, group, scene, x, y, map, map.getFarmTime());
	}

}
