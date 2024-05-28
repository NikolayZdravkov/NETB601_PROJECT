package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintingHouseTest {

    private PrintingHouse printingHouse;
    private PrintingMachineOperator operator;
    private Manager manager;

    @BeforeEach
    public void setUp() {
        printingHouse = new PrintingHouse(5, 10, 10000, 100, 10);
        operator = new PrintingMachineOperator("John Doe", 3000);
        manager = new Manager("Jane Smith", 4000, 5, 10, 10000);

        printingHouse.addEmployee(operator);
        printingHouse.addEmployee(manager);

        PrintingMachine machine1 = new PrintingMachine("Machine1", MachineType.MONOCHROME, 500, 30);
        PrintingMachine machine2 = new PrintingMachine("Machine2", MachineType.COLORED, 300, 20);
        printingHouse.addMachine(machine1);
        printingHouse.addMachine(machine2);
    }

    @Test
    public void testAddPaperExpense() {
        printingHouse.addPaperExpense(100, 0.10);
        assertEquals(10.0, printingHouse.getPaperExpenses());
    }

    @Test
    public void testCalculateSalaries() {
        printingHouse.setIncome(15000);
        printingHouse.calculateSalaries();
        assertEquals(7400, printingHouse.getSalaryExpenses());
    }

    @Test
    public void testProcessOrder() {
        Paper paper = new Paper(PaperType.STANDARD, PaperSize.A4, 0.10);
        Publication book = new Publication("Java Programming", 300, PaperSize.A4, PaperType.STANDARD);
        printingHouse.processOrder(book, paper, 120);

        assertEquals(3600, printingHouse.getIncome());
        assertEquals(36, printingHouse.getPaperExpenses());
    }

    @Test
    public void testLoadPaperToMachine() {
        printingHouse.loadPaperToMachine("Machine1", 200);
        PrintingMachine machine = printingHouse.getMachines().get(0);
        assertEquals(200, machine.getCurrentSheets());
    }

    @Test
    public void testLoadPaperToNonExistingMachine() {
        assertThrows(IllegalArgumentException.class, () -> {
            printingHouse.loadPaperToMachine("NonExistingMachine", 100);
        });
    }
}
