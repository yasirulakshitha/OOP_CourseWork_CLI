# Real-Time Event Ticketing System

The **Real-Time Event Ticketing System** is a simulation of a ticket booking process using multiple vendor and customer threads. Vendors release tickets into a pool, and customers retrieve tickets, with configurable rates and pool sizes. The system is implemented in Java with multithreading for real-time operations.

## Features

- **Multi-threaded Simulation**: Vendors and customers run on separate threads.
- **Configurable Parameters**: Customize ticket pool size, release rate, retrieval rate, total tickets, and the number of vendors and customers.
- **Logging**: Track vendor and customer actions with logs.

## Setup Instructions

### Prerequisites

- **Java 8 or higher**
- **Git** (for cloning the repo)

### Steps to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/real-time-event-ticketing-system.git
    ```

2. Compile the code:
    ```bash
    javac Main.java
    ```

3. Run the program:
    ```bash
    java Main
    ```

## Configuration

Configure the following parameters:
- **Max Ticket Pool Size**: Maximum tickets that can be held in the pool (must be greater than or equal to the total tickets).
- **Release Rate**: Tickets released per second.
- **Retrieval Rate**: Tickets retrieved per second.
- **Total Tickets**: Total number of tickets to be processed (must be less than or equal to max pool size).
- **Number of Vendors**: Vendor threads.
- **Number of Customers**: Customer threads.

## How It Works

1. **Ticket Pool**: Created based on the max size, which must be greater than or equal to the total number of tickets.
2. **Vendor Threads**: Release tickets into the pool.
3. **Customer Threads**: Retrieve tickets from the pool.
4. **Logging**: Actions of vendors and customers are logged for monitoring.
