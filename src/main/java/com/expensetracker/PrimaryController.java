package com.expensetracker;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.util.ArrayList;





public class PrimaryController {

    private final S3Repository expenseRepository = new S3Repository();

    /**
     * List for storing Expense-type objects from Expense.java
     */
    @FXML
    private ListView<Expense> expenseListView;


    /**
     * Label under list to generate Monthly Total
     */
    @FXML
    private Label totalAmountLabel;

    private void updateTotal() {
        double total = expenseListView.getItems().stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        totalAmountLabel.setText(String.format("Monthly Total: $%.2f", total));
    }
    
    /**
     * Text box for capturing expense information
     */
    @FXML
    private TextField expenseInput;

    /**
     * Text box for capturing expense information
     */
    @FXML
    private TextField amountInput;

    /**
     * Text box for capturing expense information
     */
    @FXML
    private TextField descriptionInput;

    /** 
     * ComboBox for selecting frequency of reocurring expense
     */
    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML TextField contactPhoneInput;

    /**
     * 
     */
    @FXML
    public void initialize() {
        categoryComboBox.getItems().addAll("Weekly", "Bi-weekly", "Monthly");
        categoryComboBox.getSelectionModel().selectFirst();

        expenseListView.getItems().setAll(
                expenseRepository.findAll()
        );


        expenseListView.getItems().addListener(
                (ListChangeListener<Expense>) change -> updateTotal());
        updateTotal();
        
        // handles opening details view on click
        expenseListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Expense selectedExpense =
                        expenseListView.getSelectionModel().getSelectedItem();

                if (selectedExpense != null) {
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Expense Details");
                    detailsStage.setScene(
                            ExpenseDetailsScene.create(
                                    selectedExpense,
                                    () -> {
                                        expenseListView.getItems().remove(selectedExpense);
                                        saveExpenses();
                                    }
                            )
                    );
                    detailsStage.show();
                }
            }
        });

        
    }

    /**
     * adds item to the list when all fields are complete
     */
    @FXML
    private void addItem() {

        
        String name = expenseInput.getText();
        String amountText = amountInput.getText();
        String description = descriptionInput.getText();
        String frequency = categoryComboBox.getValue();
        String contactPhone = contactPhoneInput.getText();
        

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
                Expense newExpense = new Expense(name, finalAmount, description, frequency, contactPhone);
                
                // adds the new expense to the ListView pane
                expenseListView.getItems().add(newExpense);
                
                saveExpenses();

                expenseInput.clear();
                amountInput.clear();
                descriptionInput.clear();
                contactPhoneInput.clear();

            } catch (NumberFormatException e) {
                System.out.println("Invalid dollar amount");
            }
        }

    }

    private void saveExpenses() {
        expenseRepository.saveAll(
                new ArrayList<>(expenseListView.getItems())
        );
    }


}
