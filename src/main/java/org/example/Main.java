package org.example;

public class Main {
    public static void main(String[] args) {
        // Define some paper prices
        Paper standardA4 = new Paper(PaperType.STANDARD, PaperSize.A4, 0.10);
        Paper glossyA4 = new Paper(PaperType.GLOSSY, PaperSize.A4, 0.20);
        Paper newspaperA4 = new Paper(PaperType.NEWSPAPER, PaperSize.A4, 0.05);

        // Create a printing house with discount settings
        PrintingHouse printingHouse = new PrintingHouse(5, 10, 10000, 100, 10);

        // Add employees
        printingHouse.addEmployee(new PrintingMachineOperator("John Doe", 3000));
        printingHouse.addEmployee(new Manager("Jane Smith", 4000, 5, 10, 10000));

        // Create and add printing machines
        PrintingMachine machine1 = new PrintingMachine("Machine1", MachineType.MONOCHROME, 500, 30);
        PrintingMachine machine2 = new PrintingMachine("Machine2", MachineType.COLORED, 300, 20);
        printingHouse.addMachine(machine1);
        printingHouse.addMachine(machine2);

        // Load paper into machines
        printingHouse.loadPaperToMachine("Machine1", 200);
        printingHouse.loadPaperToMachine("Machine2", 150);

        // Create a publication
        Publication book = new Publication("Java Programming", 300, PaperSize.A4, PaperType.STANDARD);

        // Process an order for 120 copies
        printingHouse.processOrder(book, standardA4, 120);

        // Calculate and print salaries
        printingHouse.calculateSalaries();
        System.out.println("Total salary expenses: $" + printingHouse.getSalaryExpenses());

        // Print total paper expenses
        System.out.println("Total paper expenses: $" + printingHouse.getPaperExpenses());

        // Print total expenses
        System.out.println("Total expenses: $" + printingHouse.calculateTotalExpenses());

        // Print total income
        System.out.println("Total income: $" + printingHouse.getIncome());

        // Calculate and print profit
        System.out.println("Total profit: $" + printingHouse.calculateProfit());

        // Define directory and file name
        String directoryName = "printing_house_data";
        String filename = "data.txt";

        // Write data to file
        printingHouse.writeToFile(directoryName, filename);

        // Read data from file
        System.out.println("Reading data from file:");
        printingHouse.readFromFile(directoryName, filename);
    }
}






