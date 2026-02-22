package com.tradingengine.service;
import com.tradingengine.model.Position;
import com.tradingengine.model.Trade;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
public class PortfolioManager {
    private final Map<Integer, Map<String, Position>> portfolios = new ConcurrentHashMap<>();
    public synchronized void applyTrade(Trade trade) {
        portfolios.putIfAbsent(trade.getAccountId(), new ConcurrentHashMap<>());
        Map<String, Position> accountPortfolio = portfolios.get(trade.getAccountId());
        accountPortfolio.putIfAbsent(trade.getSymbol(), new Position(trade.getSymbol(), 0));
        accountPortfolio.get(trade.getSymbol()).update(trade.getSide(), trade.getQuantity());
    }
    public Map<Integer, Map<String, Position>> getPortfolios() {
        return portfolios;
    }
}
