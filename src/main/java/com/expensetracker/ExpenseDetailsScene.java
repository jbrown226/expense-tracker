package com.expensetracker;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public final class ExpenseDetailsScene {

    /**
     * Class for handing Details pop-up when an expense is selected from the list
     */
    private ExpenseDetailsScene() {
    }

    public static Scene create(Expense expense) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(12);
        root.setVgap(12);

        addDetail(root, 0, "Name", expense.getName());
        addDetail(root, 1, "Monthly amount", String.format("$%.2f", expense.getAmount()));
        addDetail(root, 2, "Description", expense.getDescription());

        Button closeButton = new Button("Close");
        closeButton.setDefaultButton(true);
        closeButton.setOnAction(event ->
                ((Stage) closeButton.getScene().getWindow()).close());
        root.add(closeButton, 1, 4);

        return new Scene(root, 400, 230);
    }

    private static void addDetail(GridPane root, int row, String title, String value) {
        Label titleLabel = new Label(title + ":");
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value == null || value.isBlank() ? "(none)" : value);
        valueLabel.setWrapText(true);
        valueLabel.setMaxWidth(260);

        root.addRow(row, titleLabel, valueLabel);
    }
}
