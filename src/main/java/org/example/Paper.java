package org.example;

public class Paper {
    private PaperType type;
    private PaperSize size;
    private double pricePerSheet;

    public Paper(PaperType type, PaperSize size, double pricePerSheet) {
        this.type = type;
        this.size = size;
        this.pricePerSheet = pricePerSheet;
    }

    public double getPricePerSheet() {
        return pricePerSheet;
    }

    public PaperType getType() {
        return type;
    }

    public PaperSize getSize() {
        return size;
    }
}

