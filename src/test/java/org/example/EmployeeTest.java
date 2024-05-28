package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testPrintingMachineOperatorSalary() {
        PrintingMachineOperator operator = new PrintingMachineOperator("John Doe", 3000);
        assertEquals(3000, operator.calculateSalary(10000));
    }

    @Test
    public void testManagerSalaryBelowThreshold() {
        Manager manager = new Manager("Jane Smith", 4000, 5, 10, 10000);
        assertEquals(4200, manager.calculateSalary(8000)); // 4000 + 5% of 4000
    }

    @Test
    public void testManagerSalaryAboveThreshold() {
        Manager manager = new Manager("Jane Smith", 4000, 5, 10, 10000);
        assertEquals(4400, manager.calculateSalary(12000)); // 4000 + 10% of 4000
    }
}
