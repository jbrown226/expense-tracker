package com.expensetracker;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public final class ExpenseDetailsScene {

    private ExpenseDetailsScene() {
    }

    public static Scene create(Expense expense, Runnable deleteHandler) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ExpenseDetailsScene.class.getResource(
                            "expense_details.fxml"
                    )
            );

            Parent root = loader.load();

            ExpenseDetailsController controller =
                    loader.getController();

            controller.setExpense(expense, deleteHandler);

            return new Scene(root);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not find details",
                    e
            );
        }
    }
    
}
