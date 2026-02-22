package com.tradingengine.model;
public class Position {
    private String symbol;
    private int quantity;

    public Position(String symbol, int quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }

    public void update(String side, int qty) {
        if (side.equals("BUY")) {
            quantity += qty;
        } else if (side.equals("SELL")) {
            if (quantity - qty < 0) {
                throw new IllegalArgumentException("Negative position not allowed");
            }
            quantity -= qty;
        }
    }
    public String getSymbol() {
        return symbol;
    }
    public int getQuantity() {
        return quantity;
    }
}

