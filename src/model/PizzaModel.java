package model;

import java.util.ArrayList;

public class PizzaModel {

    private String size;
    private String crust;
    private ArrayList<String> toppings;

    public PizzaModel(String size, String crust, ArrayList<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
    }

    public double calculateCost(){
        double cost = 0.0;

        switch (size){
            case "Small" -> cost += 5;
            case "Medium" -> cost += 10;
            case "Large"  -> cost += 15;
        }
        switch (crust){
            case "Stuffed" -> cost += 3;
            case "Pan" -> cost += 2;
            case "Regular" -> cost += 1.5;
        }

        cost += toppings.size() * 0.5;



        return  cost;
    }

    @Override
    public String toString() {
        return " " +
                "size: '" + size + '\'' +
                ", crust: '" + crust + '\'' +
                ", toppings: [" + toppings +
                ']';
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }
}