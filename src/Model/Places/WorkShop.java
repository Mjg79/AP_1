package Model.Places;

import Model.ElementAndBoxAndDirection.Element;
import Model.ProductsAndForage.Product;

import java.util.ArrayList;

public abstract class WorkShop extends Element {
    private boolean isInWorking = false;
    private ArrayList<Product> inputProducts;
    private Product kindOfOutputProduct;
    //private ArrayList<Product> outputProduct;
    private int maxInputNumber;
    private int maxOutputNumber;
    private double workTime = 15;
    private double workStartTime;
    private double workEndTime;
    private int inputNumbers;

    public WorkShop(ArrayList<Product> inputProducts) {
        this.inputProducts = inputProducts;
    }

    public boolean checkWorkDone(double time) {
       if (isInWorking &&( time > workEndTime)) {
           isInWorking = false;
           workEndTime = 0;
           workStartTime = 0;
           return true;
       }
       return false;
    }

    public void startWork(double time,int number){
        if (!isInWorking){
            isInWorking = true;
            workStartTime = time;
            workEndTime = time+workTime;
            inputNumbers = number;
        }
    }

    public boolean isInWorking() {
        return isInWorking;
    }

    public void setInWorking(boolean inWorking) {
        isInWorking = inWorking;
    }

    public ArrayList<Product> getInputProducts() {
        return inputProducts;
    }

    public void setInputProducts(ArrayList<Product> inputProducts) {
        this.inputProducts = inputProducts;
    }

    public Product getKindOfOutputProduct() {
        return kindOfOutputProduct;
    }

    public void setKindOfOutputProduct(Product kindOfOutputProduct) {
        this.kindOfOutputProduct = kindOfOutputProduct;
    }

    public int getMaxInputNumber() {
        return maxInputNumber;
    }

    public void setMaxInputNumber(int maxInputNumber) {
        this.maxInputNumber = maxInputNumber;
    }

    public int getMaxOutputNumber() {
        return maxOutputNumber;
    }

    public void setMaxOutputNumber(int maxOutputNumber) {
        this.maxOutputNumber = maxOutputNumber;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public double getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(double workStartTime) {
        this.workStartTime = workStartTime;
    }

    public double getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(double workEndTime) {
        this.workEndTime = workEndTime;
    }

    public int getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(int inputNumbers) {
        this.inputNumbers = inputNumbers;
    }
}
