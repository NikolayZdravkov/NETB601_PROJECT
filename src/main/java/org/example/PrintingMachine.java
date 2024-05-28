package org.example;

public class PrintingMachine {
    private String id;
    private MachineType type;
    private int maxSheets;
    private int currentSheets;
    private int pagesPerMinute;

    public PrintingMachine(String id, MachineType type, int maxSheets, int pagesPerMinute) {
        this.id = id;
        this.type = type;
        this.maxSheets = maxSheets;
        this.currentSheets = 0;
        this.pagesPerMinute = pagesPerMinute;
    }

    public String getId() {
        return id;
    }

    public MachineType getType() {
        return type;
    }

    public int getMaxSheets() {
        return maxSheets;
    }

    public int getCurrentSheets() {
        return currentSheets;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public void loadPaper(int sheets) {
        if (sheets <= 0) {
            throw new IllegalArgumentException("Number of sheets to load must be positive.");
        }
        if (currentSheets + sheets > maxSheets) {
            throw new IllegalArgumentException("Exceeds maximum sheet capacity.");
        }
        currentSheets += sheets;
    }

    public void print(int pages) throws Exception {
        if (currentSheets == 0) {
            throw new Exception("Printing machine " + id + " is out of paper.");
        }
        if (pages <= 0) {
            throw new IllegalArgumentException("Number of pages to print must be positive.");
        }
        if (pages > currentSheets) {
            throw new Exception("Not enough paper to print " + pages + " pages.");
        }
        currentSheets -= pages;
        System.out.println("Printing machine " + id + " printed " + pages + " pages.");
    }
}

