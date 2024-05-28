package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicationTest {

    @Test
    public void testCalculateTotalPriceNoDiscount() {
        Paper paper = new Paper(PaperType.STANDARD, PaperSize.A4, 0.10);
        Publication book = new Publication("Java Programming", 300, PaperSize.A4, PaperType.STANDARD);
        assertEquals(3600, book.calculateTotalPrice(paper, 120, 100, 10));
    }

    @Test
    public void testCalculateTotalPriceWithDiscount() {
        Paper paper = new Paper(PaperType.STANDARD, PaperSize.A4, 0.10);
        Publication book = new Publication("Java Programming", 300, PaperSize.A4, PaperType.STANDARD);
        assertEquals(3240, book.calculateTotalPrice(paper, 120, 100, 10)); // 3600 - 10% of 3600
    }

    @Test
    public void testCalculateSheetsRequired() {
        Publication book = new Publication("Java Programming", 300, PaperSize.A4, PaperType.STANDARD);
        assertEquals(75, book.calculateSheetsRequired());
    }
}
