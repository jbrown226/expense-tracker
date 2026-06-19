package com.expensetracker;

public class Expense {

    private final String name;
    private final double amount;
    private final String description;
    private final String frequency;

    public Expense(String name, double amount, String description, String frequency) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", this.name, this.amount);
    }


}
