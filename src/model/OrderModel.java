package model;

import java.util.ArrayList;

public class OrderModel {
    private double cost;


    private ArrayList<model.PizzaModel> pizzas;


    public double getCost() {
        return cost;
    }
    public ArrayList<model.PizzaModel> getPizzas() {
        return pizzas;
    }

    public OrderModel() {
        this.pizzas = new ArrayList<>();
        cost = 0;
    }

    public void addPizza(model.PizzaModel pizza) {
        pizzas.add(pizza);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (model.PizzaModel pizza :
                pizzas) {
            totalCost += pizza.calculateCost();
        }
        cost = totalCost;
        return totalCost;
    }

    public void restart() {
        pizzas = new ArrayList<>();
    }
}