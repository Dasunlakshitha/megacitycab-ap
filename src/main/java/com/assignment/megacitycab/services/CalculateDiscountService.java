package com.assignment.megacitycab.services;

public class CalculateDiscountService {
    private static CalculateDiscountService instance;

    // Private constructor to prevent instantiation
    private CalculateDiscountService() {}

    // Public method to get the single instance
    public static CalculateDiscountService getInstance() {
        if (instance == null) {
            synchronized (CalculateDiscountService.class) {
                if (instance == null) {
                    instance = new CalculateDiscountService();
                }
            }
        }
        return instance;
    }

    // Method to calculate 5% discount on fare price
    public double calculateDiscount(double fare) {
        return fare * 0.95; // Applying 5% discount
    }
}
