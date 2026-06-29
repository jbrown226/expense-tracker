package com.expensetracker;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ExpenseDetailsController {

    private Expense expense;
    private Runnable deleteHandler;

    @FXML
    private Label nameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label frequencyLabel;

    @FXML
    private Label contactPhoneLabel;

    /**
     * Receives the selected Expense and displays its information.
     */
    public void setExpense(Expense expense, Runnable deleteHandler) {
        this.expense = expense;
        this.deleteHandler = deleteHandler;
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

        contactPhoneLabel.setText(
                displayValue(expense.getContactPhone())
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
    private void deleteExpense() {
        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete " + expense.getName() + "?",
                ButtonType.YES,
                ButtonType.NO
        );
        confirmation.initOwner(nameLabel.getScene().getWindow());
        confirmation.setTitle("Delete Expense");
        confirmation.setHeaderText("This action cannot be undone.");

        confirmation.showAndWait()
                .filter(button -> button == ButtonType.YES)
                .ifPresent(button -> {
                    deleteHandler.run();
                    closeWindow();
                });
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }
}
