package org.example;

public class Manager extends Employee {
    private double lowerPercentage;
    private double higherPercentage;
    private double thresholdIncome;

    public Manager(String name, double baseSalary, double lowerPercentage, double higherPercentage, double thresholdIncome) {
        super(name, baseSalary);
        this.lowerPercentage = lowerPercentage;
        this.higherPercentage = higherPercentage;
        this.thresholdIncome = thresholdIncome;
    }

    @Override
    public double calculateSalary(double income) {
        if (income > thresholdIncome) {
            return baseSalary * (1 + higherPercentage / 100);
        } else {
            return baseSalary * (1 + lowerPercentage / 100);
        }
    }
}

