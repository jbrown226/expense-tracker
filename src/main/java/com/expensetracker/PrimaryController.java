package com.expensetracker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class PrimaryController {

    /**
     * List for storing Expense-type objects from Expense.java
     */
    @FXML
    private ListView<Expense> expenseListView;

    @FXML
    private TextField expenseInput;

    @FXML
    private TextField amountInput;

    @FXML
    private TextField descriptionInput;

    /** 
     * ComboBox for selecting frequency of reocurring expense
     */
    @FXML
    private ComboBox<String> categoryComboBox;

    /**
     * populates the comboBox with fields for selection
     */
    @FXML
    public void initialize() {
        categoryComboBox.getItems().addAll("Weekly", "Bi-weekly", "Monthly");
        categoryComboBox.getSelectionModel().selectFirst();
    }

    /**
     * adds item to the list when all fields are complete
     */
    @FXML
    private void addItem() {

        // get the 
        String name = expenseInput.getText();
        String amountText = amountInput.getText();
        String description = descriptionInput.getText();
        String frequency = categoryComboBox.getValue();
        

        // checks for missing name and amount
        if (name != null && !name.trim().isEmpty() && amountText != null && !amountText.isEmpty()) {
            try {

                // converts the amountText datatype to a double
                double baseAmount = Double.parseDouble(amountText);
                double finalAmount = baseAmount;

                switch (frequency) {
                    case "Weekly":
                        finalAmount = baseAmount * 4;
                        break;
                    case "Bi-weekly":
                        finalAmount = baseAmount * 2;
                        break;
                    case "Monthly":
                        finalAmount = baseAmount * 1;
                        break;
                }

                // creates a new object of the Expense class
                Expense newExpense = new Expense(name, finalAmount, description, frequency);
                
                // adds the new expense to the ListView pane
                expenseListView.getItems().add(newExpense);

                expenseInput.clear();
                amountInput.clear();
                descriptionInput.clear();

            } catch (NumberFormatException e) {
                System.out.println("Invalid dollar amount");
            }
        }
    }
}
