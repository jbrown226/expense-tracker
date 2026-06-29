package com.expensetracker;

public class Expense {

    private String name;
    private double amount;
    private String description;
    private String frequency;
    private String contactPhone;

    public Expense() {
        // needed 
    }

    public Expense(String name, double amount, String description, String frequency, String contactPhone) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.frequency = frequency;
        this.contactPhone = contactPhone;
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

    public String getContactPhone() {
        return contactPhone;
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

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f", this.name, this.amount);
    }


}
