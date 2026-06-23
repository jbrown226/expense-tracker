package com.expensetracker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ExpenseDetailsController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label frequencyLabel;

    /**
     * Receives the selected Expense and displays its information.
     */
    public void setExpense(Expense expense) {
        nameLabel.setText(displayValue(expense.getName()));

        amountLabel.setText(
                String.format("$%.2f", expense.getAmount())
        );

        descriptionLabel.setText(
                displayValue(expense.getDescription())
        );

        frequencyLabel.setText(
                displayValue(expense.getFrequency())
        );
    }

    /**
     * handles null values where details are not provided
     */
    private String displayValue(String value) {
        if (value == null || value.isBlank()) {
            return "(none)";
        }

        return value;
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }
}