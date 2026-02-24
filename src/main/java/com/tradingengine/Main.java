package com.tradingengine;
import com.tradingengine.model.Trade;
import com.tradingengine.service.*;
import com.tradingengine.persistence.DatabaseManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
public class Main {
    public static void main(String[] args) throws Exception {
        PortfolioManager portfolioManager = new PortfolioManager();
        DatabaseManager dbManager = new DatabaseManager();
        TradeProcessor processor = new TradeProcessor(portfolioManager, dbManager);
        ReportGenerator reportGenerator = new ReportGenerator();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("trades.csv")))) {
            br.lines().skip(1).forEach(line -> {
                String[] parts = line.split(",");
                Trade trade = new Trade(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[2],
                        Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]),
                        parts[5],
                        LocalDateTime.parse(parts[6])
                );
                processor.processTrade(trade);
            });
        }
        Thread.sleep(2000);
        processor.shutdown();
        reportGenerator.generateReport(portfolioManager.getPortfolios());
    }
}
