package org.example;

public class Publication {
    private String title;
    private int numberOfPages;
    private PaperSize paperSize;
    private PaperType paperType;

    public Publication(String title, int numberOfPages, PaperSize paperSize, PaperType paperType) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.paperSize = paperSize;
        this.paperType = paperType;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    // Method to calculate the price of the publication
    public double calculatePrice(Paper paper) {
        if (paper.getType() == this.paperType && paper.getSize() == this.paperSize) {
            return numberOfPages * paper.getPricePerSheet();
        } else {
            throw new IllegalArgumentException("Paper type or size does not match the publication's requirements.");
        }
    }

    // Method to calculate the number of sheets required
    public int calculateSheetsRequired() {
        return numberOfPages;  // Assuming one page per sheet for simplicity
    }

    // Method to calculate the total price for a given quantity with a discount
    public double calculateTotalPrice(Paper paper, int quantity, int discountThreshold, double discountPercentage) {
        double pricePerPublication = calculatePrice(paper);
        double totalPrice = pricePerPublication * quantity;
        if (quantity > discountThreshold) {
            totalPrice -= totalPrice * (discountPercentage / 100);
        }
        return totalPrice;
    }
}


