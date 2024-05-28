package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
    private List<Employee> employees;
    private double income;
    private double lowerPercentage;
    private double higherPercentage;
    private double thresholdIncome;
    private double paperExpenses;
    private double salaryExpenses;
    private int discountThreshold;
    private double discountPercentage;
    private List<PrintingMachine> machines;

    public PrintingHouse(double lowerPercentage, double higherPercentage, double thresholdIncome, int discountThreshold, double discountPercentage) {
        this.employees = new ArrayList<>();
        this.lowerPercentage = lowerPercentage;
        this.higherPercentage = higherPercentage;
        this.thresholdIncome = thresholdIncome;
        this.discountThreshold = discountThreshold;
        this.discountPercentage = discountPercentage;
        this.paperExpenses = 0;
        this.salaryExpenses = 0;
        this.income = 0;
        this.machines = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public void calculateSalaries() {
        salaryExpenses = 0;
        for (Employee employee : employees) {
            double salary = employee.calculateSalary(income);
            salaryExpenses += salary;
            System.out.println(employee.getName() + " earns: $" + salary);
        }
    }

    public double getSalaryExpenses() {
        return salaryExpenses;
    }

    public void addPaperExpense(int sheets, double pricePerSheet) {
        paperExpenses += sheets * pricePerSheet;
    }

    public double getPaperExpenses() {
        return paperExpenses;
    }

    public double calculateTotalExpenses() {
        return paperExpenses + salaryExpenses;
    }

    public void addMachine(PrintingMachine machine) {
        machines.add(machine);
    }

    public List<PrintingMachine> getMachines() {
        return machines;
    }

    public void loadPaperToMachine(String machineId, int sheets) {
        for (PrintingMachine machine : machines) {
            if (machine.getId().equals(machineId)) {
                machine.loadPaper(sheets);
                return;
            }
        }
        throw new IllegalArgumentException("No machine found with ID: " + machineId);
    }

    public void processOrder(Publication publication, Paper paper, int quantity) {
        double orderIncome = publication.calculateTotalPrice(paper, quantity, discountThreshold, discountPercentage);
        this.income += orderIncome;

        int sheetsRequired = publication.calculateSheetsRequired() * quantity;
        addPaperExpense(sheetsRequired, paper.getPricePerSheet());

        try {
            distributePrintingLoad(sheetsRequired);
            System.out.println("Processed order for " + quantity + " copies of " + publication.getTitle());
            System.out.println("Income from this order: $" + orderIncome);
        } catch (Exception e) {
            System.out.println("Failed to process order: " + e.getMessage());
        }
    }

    private void distributePrintingLoad(int sheetsRequired) throws Exception {
        int remainingSheets = sheetsRequired;
        for (PrintingMachine machine : machines) {
            if (remainingSheets <= 0) break;
            int sheetsToPrint = Math.min(remainingSheets, machine.getCurrentSheets());
            machine.print(sheetsToPrint);
            remainingSheets -= sheetsToPrint;
        }
        if (remainingSheets > 0) {
            throw new Exception("Not enough paper in all machines to complete the order.");
        }
    }

    public double calculateProfit() {
        return income - calculateTotalExpenses();
    }

    public void writeToFile(String directoryName, String filename) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + directory.getAbsolutePath());
                return;
            }
        }

        File file = new File(directory, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Income: $" + income);
            writer.newLine();
            writer.write("Paper Expenses: $" + paperExpenses);
            writer.newLine();
            writer.write("Salary Expenses: $" + salaryExpenses);
            writer.newLine();
            writer.write("Total Expenses: $" + calculateTotalExpenses());
            writer.newLine();
            writer.write("Profit: $" + calculateProfit());
            writer.newLine();

            writer.write("Printed Publications:");
            writer.newLine();
            for (Employee employee : employees) {
                writer.write(employee.getName() + " earns: $" + employee.calculateSalary(income));
                writer.newLine();
            }

            writer.write("Machines:");
            writer.newLine();
            for (PrintingMachine machine : machines) {
                writer.write(machine.getId() + " - Type: " + machine.getType() + ", Max Sheets: " + machine.getMaxSheets() + ", Current Sheets: " + machine.getCurrentSheets());
                writer.newLine();
            }

            System.out.println("Data successfully written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String directoryName, String filename) {
        File file = new File(directoryName, filename);
        if (!file.exists()) {
            System.out.println("File does not exist: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Data successfully read from file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}





