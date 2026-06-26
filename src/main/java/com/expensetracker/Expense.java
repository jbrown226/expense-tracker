package com.expensetracker;

public class Expense {

    private String name;
    private double amount;
    private String description;
    private String frequency;

    public Expense() {
        // needed 
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f", this.name, this.amount);
    }


}
