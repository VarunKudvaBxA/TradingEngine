package com.tradingengine.service;
import com.tradingengine.model.Trade;
import com.tradingengine.persistence.DatabaseManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TradeProcessor {
    private final PortfolioManager portfolioManager;
    private final DatabaseManager dbManager;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    public TradeProcessor(PortfolioManager portfolioManager, DatabaseManager dbManager) {
        this.portfolioManager = portfolioManager;
        this.dbManager = dbManager;
    }
    public void processTrade(Trade trade) {
        executor.submit(() -> {
            try {
                portfolioManager.applyTrade(trade);
                dbManager.persistTrade(trade);
            } catch (Exception e) {
                System.err.println("Error processing trade: " + e.getMessage());
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
    }
}
