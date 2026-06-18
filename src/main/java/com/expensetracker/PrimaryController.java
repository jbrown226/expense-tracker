package com.expensetracker;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

public class PrimaryController {

    @FXML
    private ListView<String> expenseListView;

    @FXML
    private ComboBox<String> categoryComboBox;

    /**
     * creates options in the combobox dropdown menu, for selecting frequency
     * of reoccuring expenses
     */
    @FXML
    public void initialize() {
        categoryComboBox.getItems().addAll("Weekly", "Bi-weekly", "Monthly");
    }
    
}
