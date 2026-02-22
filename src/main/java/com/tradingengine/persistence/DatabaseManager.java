package com.tradingengine.persistence;
import com.tradingengine.model.Trade;
import java.sql.*;
public class DatabaseManager {
    private final Connection connection;
    public DatabaseManager() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:tradingdb", "sa", "");
        initSchema();
    }
    private void initSchema() throws SQLException {
        String sql = "CREATE TABLE trades (" +
                "tradeId INT PRIMARY KEY, accountId INT, symbol VARCHAR(10)," +
                "quantity INT, price DOUBLE, side VARCHAR(10), timestamp TIMESTAMP)";
        connection.createStatement().execute(sql);
    }
    public synchronized void persistTrade(Trade trade) throws SQLException {
        String sql = "INSERT INTO trades VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, trade.getTradeId());
        ps.setInt(2, trade.getAccountId());
        ps.setString(3, trade.getSymbol());
        ps.setInt(4, trade.getQuantity());
        ps.setDouble(5, trade.getPrice());
        ps.setString(6, trade.getSide());
        ps.setTimestamp(7, Timestamp.valueOf(trade.getTimestamp()));
        ps.executeUpdate();
    }
}
