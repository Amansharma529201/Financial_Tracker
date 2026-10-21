# Financial Transaction Summary

## 1. Overview

The Financial Transaction Summary is a Java console-based application that helps users record transactions and get a quick overview of their finances. It calculates total credits, total debits, the final balance, the category with the highest total spending, and the largest individual debit transaction.

Each transaction contains four details:

- **ID:** A unique ID generated automatically by the program.
- **Type:** `CREDIT` or `DEBIT`.
- **Amount:** A positive, finite number.
- **Category:** A category such as `FOOD`, `TRAVEL`, `SALARY`, or `MOVIE`.

The program stores transactions in an `ArrayList` and displays a summary when the user finishes entering them.

## 2. Technologies Used

- Java (JDK 21)
- `ArrayList` to store transactions
- `HashMap` to calculate spending by category
- `Scanner` to accept user input
- IntelliJ IDEA for development and testing

## 3. How to Run the Program

### Option A: Run through IntelliJ IDEA

1. Install JDK 21 or another compatible JDK.
2. Open the project in IntelliJ IDEA.
3. Open `Main.java` from the source directory.
4. Click the green Run icon beside the `main()` method, or right-click the file and select **Run 'Main.main()'**.
5. Enter the transaction type, amount, and category when prompted.
6. Choose `yes` to add another transaction or `no` to finish.
7. The program will display all transactions and the final financial summary.

### Option B: Run through the terminal

Open a terminal in the directory containing `Main.java`.

Compile the program:

```bash
javac Main.java
```

Run it:

```bash
java Main
```

Make sure a compatible JDK is installed and the Java compiler is available on your system PATH.

## 4. Features

- Allows users to enter multiple credit and debit transactions.
- Generates transaction IDs automatically.
- Validates transaction types and rejects invalid entries.
- Rejects non-numeric, zero, negative, and non-finite amounts.
- Prevents users from submitting an empty category.
- Validates the response when asking whether to continue.
- Calculates total credits, total debits, and the final balance.
- Identifies the category with the highest combined debit amount.
- Finds the largest individual debit transaction.
- Displays all recorded transactions and a financial summary.
- Handles empty transaction lists in the calculation methods.

## 5. How the Program Works

### Transaction class

The `Transaction` class represents an individual transaction. It stores the transaction ID, type, amount, and category.

### Taking input and storing transactions

The program uses `Scanner` to collect information from the user. Once the input has been validated, it creates a `Transaction` object and adds it to an `ArrayList<Transaction>`.

### Calculating the financial summary

- **Total credits:** The program loops through the transactions and adds the amounts of all credit transactions.
- **Total debits:** It adds the amounts of all debit transactions.
- **Final balance:** It subtracts total debits from total credits.
- **Highest-spending category:** A `HashMap` stores the combined debit amount for each category, allowing the program to identify the category with the highest total.
- **Largest individual debit:** The program checks each transaction and keeps track of the largest debit amount found.

## 6. Assumptions and Limitations

1. Transactions are entered through the console. The program does not currently read transactions from a JSON file.
2. Transaction IDs start at 1 and increase automatically during each execution.
3. Transaction types are case-insensitive and converted to uppercase.
4. Amounts must be positive, finite numbers.
5. Categories are trimmed and converted to uppercase. The program does not restrict users to a fixed list of categories.
6. The final balance is calculated as total credits minus total debits, so it can be negative.
7. If there are no debit transactions, the program reports that no largest debit exists and displays `None` for the highest debit category.
8. If two categories have the same highest debit total, the program does not guarantee which one will be displayed.
9. Transactions are stored in memory only. They are not saved to a file or database and are lost when the program ends.
10. This project is intended as a learning exercise and is not designed to handle production financial transactions.

## 7. Test Cases and Results

The following cases were manually tested during development, and the reported results matched the expected behavior.

| Test scenario                                 | Expected behavior                                | Result |
| --------------------------------------------- | ------------------------------------------------ | ------ |
| One credit of 5000 and debits of 500 and 1200 | Credits = 5000, debits = 1700, balance = 3300    | Passed |
| FOOD debit of 500 and TRAVEL debit of 1200    | TRAVEL is the highest-spending category          | Passed |
| Largest individual debit                      | Amount = 1200, category = TRAVEL                 | Passed |
| Invalid transaction type                      | Reject the input without recording a transaction | Passed |
| Non-numeric amount                            | Ask the user to enter a valid number             | Passed |
| Zero or negative amount                       | Reject the amount                                | Passed |
| Empty category                                | Ask the user to enter a category                 | Passed |
| Invalid yes/no response                       | Ask the user to enter `yes` or `no`              | Passed |

These tests cover the main calculations and several input-validation scenarios. They do not represent an exhaustive test of every possible edge case.

## 8. Time and Space Complexity

Let `n` represent the total number of transactions and `k` represent the number of distinct categories containing debit transactions.

### Time complexity

- Calculating total credits: **O(n)**
- Calculating total debits: **O(n)**
- Finding the largest individual debit: **O(n)**
- Grouping debit amounts by category using a `HashMap`: **O(n)** average
- Finding the highest debit category from the map: **O(k)** average

The program performs a fixed number of traversals over the transaction list. Therefore, the overall time complexity of generating the financial summary is **O(n)** on average, assuming typical `HashMap` performance.

### Space complexity

- The `ArrayList` stores all transactions and requires **O(n)** space.
- The category map requires **O(k)** additional space.

The overall space complexity is **O(n + k)**, which simplifies to **O(n)** because the number of distinct debit categories cannot exceed the number of transactions.

## 9. AI Tool Disclosure

ChatGPT was used during the development of this project for test-case development, code review, and README preparation

I worked on the Java console application in IntelliJ IDEA, including the transaction input process, automatic ID generation, input validation, transaction storage, and financial summary calculations. I ran the program, checked its output, shared screenshots for review, and worked through issues with guidance from ChatGPT.

AI assistance helped me  document the project. I reviewed the suggested code and checked the program's behavior during development.