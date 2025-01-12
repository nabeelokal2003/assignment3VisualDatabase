package controller;

import model.OrderModel;
import model.PizzaModel;
import view.OrderView;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    private model.OrderModel order;
    private OrderView view;
    private Connection conn;

    public OrderController(OrderModel order, OrderView view) {
        this.order = order;
        this.view = view;
        view.addPizzaButton.addActionListener(new AddPizzaListener());
        view.calculateTotalButton.addActionListener(new CalculateTotalListener());
        view.restartOrderButton.addActionListener(new RestartOrderListener());
        try {
            conn = DriverManager.getConnection(
                    // Used port 3307 since I've mapped docker's one 3306 to 3307
                    "jdbc:mysql://localhost:3307/pleaseGiveThisCourseInFlutterOrTauriOrPythonAsThoseAreTheFuture",
                    "root",
                    "password123");
        } catch (SQLException e) {
            System.out.println("Database connection failed");
        }
    }

    class AddPizzaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String size = getSelectedSize();
            String crust = getSelectedCrust();
            ArrayList<String> toppings = getSelectedToppings();

            PizzaModel pizza = new PizzaModel(size, crust, toppings);
            order.addPizza(pizza);

            view.orderDetailsArea.append("Pizza #" + order.getPizzas().size() + ": " + pizza.toString() + "\n");
        }

        private String getSelectedSize() {
            for (Component comp : view.sizePanel.getComponents()) {
                if (comp != null && ((JRadioButton) comp).isSelected()) {
                    return ((JRadioButton) comp).getText();
                }
            }
            return "Medium";
        }

        private String getSelectedCrust() {
            for (Component comp : view.crustPanel.getComponents()) {
                if (comp instanceof JRadioButton && ((JRadioButton) comp).isSelected()) {
                    return ((JRadioButton) comp).getText();
                }
            }
            return "Regular";
        }

        private ArrayList<String> getSelectedToppings() {
            ArrayList<String> selectedToppings = new ArrayList<>();
            for (JCheckBox checkbox : view.toppingCheckboxes) {
                if (checkbox.isSelected()) {
                    selectedToppings.add(checkbox.getText());
                }
            }
            return selectedToppings;
        }
    }

    class CalculateTotalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            ArrayList<PizzaModel> pizzas = order.getPizzas();
            if (pizzas.isEmpty()) {
                view.orderDetailsArea.append("No Pizza has been added\n");
                return;
            }
            String pizzaString = "";
            for (PizzaModel pizza : pizzas) {
                pizzaString += pizza.toString() + "\n";
            }

            double totalCost = order.calculateTotalCost();
            // Save the order to the database
            try {
                conn.setAutoCommit(false);

                // Insert the order
                String insertOrderSQL = "INSERT INTO orders (customer_name, total) VALUES (?, ?)";
                PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
                orderStmt.setString(1, view.customerNameField.getText());
                orderStmt.setDouble(2, order.getCost());
                orderStmt.executeUpdate();

                System.out.println("Order saved to database.");

                // Get the generated order_id
                int orderId;
                try (var rs = orderStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve order ID.");
                    }
                }

                // Insert order details
                String insertOrderDetailSQL = "INSERT INTO order_details (order_id, toppings, crust, size, cost) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement detailStmt = conn.prepareStatement(insertOrderDetailSQL);
                for (PizzaModel pizza : pizzas) {
                    detailStmt.setInt(1, orderId);
                    detailStmt.setString(2, String.join(", ", pizza.getToppings()));
                    detailStmt.setString(3, pizza.getCrust());
                    detailStmt.setString(4, pizza.getSize());
                    detailStmt.setDouble(5, pizza.calculateCost());
                    detailStmt.addBatch();
                }
                detailStmt.executeBatch();

                conn.commit();

            } catch (SQLException ex) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                ex.printStackTrace();
                view.orderDetailsArea.append("Failed to save order to database.\n");
                return;
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException autoCommitEx) {
                    autoCommitEx.printStackTrace();
                }
            }
            view.orderDetailsArea.append("Order by: " + view.customerNameField.getText() + "\n"
                    + "Total Price: " + totalCost + "\n" + pizzaString);

            view.totalAmountField.setText("" + totalCost);

        }
    }

    class RestartOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            order.restart();
            view.orderDetailsArea.setText("");
            view.totalAmountField.setText("");
            view.customerNameField.setText("");

            ((JRadioButton) view.sizePanel.getComponents()[1]).setSelected(true);
            ((JRadioButton) view.crustPanel.getComponents()[1]).setSelected(true);
            for (JCheckBox checkbox : view.toppingCheckboxes) {
                checkbox.setSelected(false);
            }
        }
    }
}