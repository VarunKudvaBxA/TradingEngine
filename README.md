What I understood from this project:

High-Throughput Transaction Processing Engine for a trading desk. It simulates how financial institutions handle large volumes of trades in real time.

The system: Loads trade requests from a CSV file. Validates trades. Processes trades concurrently using threads. Persists trades into a relational database (H2 in-memory DB).
Maintains an in-memory portfolio state for each account. Generates summary reports using Java Streams. Ensures thread safety and data integrity.

1) Trade Model: Encapsulates trade data
2) Position Model: Represents holdings per symbol and validation: preventing negative positions 
3) Portfolio Manager: ConcurrentHashMap and synchronized for thread safety when updating portfolios
4) Trade Processor: Uses thread pool ExecutorService for concurrent trade processing
5) Database Manager: H2 in-memory DB for simplictiy, ensures persistence avoids SQL injection and ACID compliance
6) Report Generator: Clean reporting using Java Streams filter and forEach
7) Main class: Reads CSV Trades, processes concurrently, persists to DB, generates final report
Separation of concerns: Models, services, persistence, reporting

Persistence means saving data so it survives beyond the program’s runtime. Without persistence, once the program ends, all trade data would be lost.
Persistence ensures that trades can be queried later, audited, or used for reporting. We use JDBC with prepared statements to insert trades into the database safely.

SQL Injection is a security vulnerability where malicious input is used to manipulate database queries. 
To prevent it, we use PreparedStatement with placeholders (?) instead of concatenating strings.
