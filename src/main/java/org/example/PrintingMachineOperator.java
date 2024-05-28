package org.example;

public class PrintingMachineOperator extends Employee {

    public PrintingMachineOperator(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary(double income) {
        return baseSalary;  // Printing Machine Operators get only the base salary
    }
}

