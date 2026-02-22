package com.tradingengine.service;
import com.tradingengine.model.Position;
import java.util.Map;
public class ReportGenerator {
    public void generateReport(Map<Integer, Map<String, Position>> portfolios) {
        portfolios.forEach((accountId, positions) -> {
            System.out.println("Account " + accountId + " Portfolio:");
            positions.values().stream()
                    .filter(p -> p.getQuantity() > 0)
                    .forEach(p -> System.out.println("  " + p.getSymbol() + ": " + p.getQuantity()));
        });
    }
}
