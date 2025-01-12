package view;

import utils.BoxIcon;
import utils.CircleIcon;
import utils.CTP;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OrderView extends JFrame {
    private final CTP clrs;
    public JTextField customerNameField;
    public JTextField totalAmountField;
    public JTextArea orderDetailsArea;
    public JPanel sizePanel;
    public JPanel crustPanel;
    JComboBox<String> crustBox;
    public JCheckBox[] toppingCheckboxes;

    public JButton addPizzaButton;
    public JButton calculateTotalButton;
    public JButton restartOrderButton;

    public OrderView() {
        this.setFont(new Font("Helvetica", Font.PLAIN, 18));
        clrs = new CTP();
        applyCtpTheme(clrs);
        getContentPane().setBackground(clrs.getBase());
        getContentPane().setForeground(clrs.getText());
        setTitle("Pizza Order");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLayout(new BorderLayout());

        JPanel customerPanel = new JPanel(new FlowLayout());
        customerNameField = new JTextField(10);
        totalAmountField = new JTextField(10);
        totalAmountField.setEditable(false);
        customerPanel.setForeground(clrs.getText());


        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerNameField);
        customerPanel.add(new JLabel("Total:"));
        customerPanel.add(totalAmountField);


        // Container of all Contained
        JPanel pizzaPanel = new JPanel(new GridLayout(2, 1));

        // Size selection
        sizePanel = new JPanel(new GridLayout(3 ,1 ));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(clrs.getSurface0()), "Size");
        titledBorder.setTitleColor(clrs.getText());  // Set the title text color
        pizzaPanel.setBorder(titledBorder);

        // To color a radio button
        Icon unselectedIcon = new CircleIcon(new Color(0x585b70), new Color(0x313244));
        Icon selectedIcon = new CircleIcon(new Color(0x313244), new Color(0x89dceb));


        JRadioButton smallButton = new JRadioButton("Small");
        smallButton.setIcon(unselectedIcon);
        smallButton.setSelectedIcon(selectedIcon);
        JRadioButton mediumButton = new JRadioButton("Medium", true); // Default selected
        mediumButton.setIcon(unselectedIcon);
        mediumButton.setSelectedIcon(selectedIcon);
        JRadioButton largeButton = new JRadioButton("Large");
        largeButton.setIcon(unselectedIcon);
        largeButton.setSelectedIcon(selectedIcon);

        // Let Radios function
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallButton);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);

        sizePanel.add(smallButton);
        sizePanel.add(mediumButton);
        sizePanel.add(largeButton);


//       sizePanel.setBackground(clrs.getBase());
//       sizePanel.setForeground(clrs.getText());

        // Crust selection
        crustPanel = new JPanel(new GridLayout(3 ,1 ));
        crustBox = new JComboBox<>(new String[]{"Pan", "Stuffed", "Regular"});
        TitledBorder titledCrustBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(clrs.getSurface0()), "Crust");
        titledCrustBorder.setTitleColor(clrs.getText());  // Set the title text color
        crustPanel.setBorder(titledCrustBorder);

        // Create radio buttons
        JRadioButton panButton = new JRadioButton("Pan");
        panButton.setIcon(unselectedIcon);
        panButton.setSelectedIcon(selectedIcon);
        JRadioButton stuffedButton = new JRadioButton("Stuffed", true); // Default selected
        stuffedButton.setIcon(unselectedIcon);
        stuffedButton.setSelectedIcon(selectedIcon);
        JRadioButton regularButton = new JRadioButton("Regular");
        regularButton.setIcon(unselectedIcon);
        regularButton.setSelectedIcon(selectedIcon);

        ButtonGroup crustGroup = new ButtonGroup();

        crustGroup.add(panButton);
        crustGroup.add(stuffedButton);
        crustGroup.add(regularButton);

        crustPanel.add(panButton);
        crustPanel.add(stuffedButton);
        crustPanel.add(regularButton);

//        crustPanel.setBackground(clrs.getMantle());
//        crustPanel.setForeground(clrs.getText());


//        crustBox.setBackground(clrs.getSky());
//        crustBox.setForeground(clrs.getText());

// Toppings selection
        JPanel toppingPanel = new JPanel(new GridLayout(2, 3));
//        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));

// Set up the TitledBorder with custom colors
        TitledBorder titledToppingsBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(clrs.getSurface0()), "Toppings");
        titledToppingsBorder.setTitleColor(clrs.getText());  // Set the title text color
        toppingPanel.setBorder(titledToppingsBorder);

        String[] toppings = {"Extra Cheese", "Tomatoes", "Olives", "Green Peppers", "Onions"};
        toppingCheckboxes = new JCheckBox[toppings.length];

        // Create custom icons for selected and unselected states
        Icon unselectedBoxIcon = new BoxIcon(new Color(0x585b70), new Color(0x313244));
        Icon selectedBoxIcon = new BoxIcon(new Color(0x313244), new Color(0x89dceb));
        for (int i = 0; i < toppings.length; i++) {
            toppingCheckboxes[i] = new JCheckBox(toppings[i]);
            toppingCheckboxes[i].setIcon(unselectedBoxIcon);
            toppingCheckboxes[i].setSelectedIcon(selectedBoxIcon);
            toppingPanel.add(toppingCheckboxes[i]);
        }

        toppingPanel.setBackground(clrs.getMantle());


        JPanel buttonsAreaPanel = new JPanel();
        buttonsAreaPanel.setLayout(new BoxLayout(buttonsAreaPanel, BoxLayout.Y_AXIS));

        // Buttons Row
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addPizzaButton = new JButton("Add Pizza");
        calculateTotalButton = new JButton("Calculate Order Total");
        restartOrderButton = new JButton("Restart Order");
        stylizeButton(addPizzaButton);
        stylizeButton(calculateTotalButton);
        stylizeButton(restartOrderButton);
        buttonPanel.add(addPizzaButton);
        buttonPanel.add(calculateTotalButton);
        buttonPanel.add(restartOrderButton);


        orderDetailsArea = new JTextArea(10, 40);
        orderDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderDetailsArea);

        buttonsAreaPanel.add(buttonPanel);
        buttonsAreaPanel.add(orderDetailsArea);

        pizzaPanel.add(sizePanel);
        pizzaPanel.add(toppingPanel);
        pizzaPanel.add(crustPanel);
        pizzaPanel.add(buttonsAreaPanel);


        add(customerPanel, BorderLayout.NORTH);
        add(pizzaPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.EAST);


    }
    private void stylizeButton(JButton button) {
        // Padding and border
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(clrs.getSky(), 2),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        button.setFocusPainted(false);

        // Hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(clrs.getSky());
                button.setForeground(clrs.getCrust());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(clrs.getCrust());
                button.setForeground(clrs.getText());
            }

        });
    }

    public void applyCtpTheme(CTP theme) {
        // Text
        UIManager.put("Label.foreground", theme.getText());
        UIManager.put("Button.foreground", theme.getText());
        UIManager.put("TextField.foreground", theme.getText());
        UIManager.put("TextArea.foreground", theme.getText());
        UIManager.put("CheckBox.foreground", theme.getSubtext());
        UIManager.put("RadioButton.foreground", theme.getSubtext());

        // bckgrnd
        UIManager.put("Panel.background", theme.getBase());
        UIManager.put("Button.background", theme.getCrust()  );
        UIManager.put("TextField.background", theme.getCrust());
        UIManager.put("TextArea.background", theme.getCrust());
        UIManager.put("CheckBox.background", theme.getMantle());
        UIManager.put("RadioButton.background", theme.getMantle());

        UIManager.put("OptionPane.foreground", theme.getText());
        UIManager.put("OptionPane.messageForeground", theme.getText());
        UIManager.put("OptionPane.background", theme.getBase());

        // Others
        UIManager.put("Button.border", BorderFactory.createLineBorder(theme.getSky()));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(theme.getSurface0()));
        UIManager.put("TextArea.border", BorderFactory.createLineBorder(theme.getSurface0()));
        UIManager.put("RadioButton.focus", theme.getSurface());
        UIManager.put("CheckBox.focus", theme.getSurface());

        UIManager.put("Button.margin", new Insets(10, 20, 10, 20));
        UIManager.put("Button.border", new LineBorder(clrs.getSky(), 2));
        UIManager.put("Button.preferredSize", new Dimension(200, 50));


    }

}