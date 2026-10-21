import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
class Transaction{
    int id;
    String type;
    double amount;
    String category;

    Transaction(int id, String type, double amount, String category){
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();


        int id = 1;
        String choice = "yes";

        while (choice.equalsIgnoreCase("yes")) {

            System.out.print("Enter transaction type (credit/debit): ");
            String type = scanner.nextLine().trim().toUpperCase();
            if (type.equals("NO")){
                break;
            }

            if (!type.equals("CREDIT") && !type.equals("DEBIT")) {
                System.out.println(
                        "Invalid transaction type. Enter CREDIT or DEBIT."
                );
                continue;
            }

            double amount = 0;
            boolean validAmount = false;

            while (!validAmount) {
                System.out.print("Enter amount: ");

                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                    scanner.nextLine();

                    if (amount > 0 && Double.isFinite(amount)) {
                        validAmount = true;
                    } else {
                        System.out.println(
                                "Invalid amount. Enter a positive, finite number."
                        );
                    }
                } else {
                    System.out.println(
                            "Invalid amount. Please enter a number."
                    );
                    scanner.nextLine();
                }
            }

            String category = "";

            while (category.isEmpty() || category.equals("NO")) {
                System.out.print("Enter category (e.g., FOOD, TRAVEL, SALARY, MOVIE): ");
                category = scanner.nextLine().trim().toUpperCase();

                if (category.isEmpty() || category.equals("NO")) {
                    System.out.println("Category cannot be empty. Try again.");
                }
            }


            Transaction newTransaction =
                    new Transaction(id, type, amount, category);

            transactions.add(newTransaction);

            System.out.println("Transaction added successfully!");
            id++;

            // 5. Ask whether to add another transaction
            while(true){
                System.out.print("Do you want to add another transaction? (yes/no): ");
                choice = scanner.nextLine().trim();
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("no")){
                    break;
                }
                System.out.println("Invalid choice: Please enter yes or no.");
            }

        }


        double totalCredit = calculateTotalCredit(transactions);
        double totalDebit = calculateTotalDebit(transactions);
        double balance = calculateBalance(totalCredit,totalDebit);
        String highestDebitCategory = findHighestDebitCategory(transactions);
        double  highestDebitAmount = findHighestDebitCategoryAmount(transactions);

        System.out.println("-----------LARGEST DEBIT TRANSACTION------------------");
        Transaction largestDebit = null;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction current = transactions.get(i);
            if (current.type.equals("DEBIT") && (largestDebit == null || current.amount > largestDebit.amount)) {
                largestDebit = current;
            }
        }
        if (largestDebit == null) {
            System.out.println("No debit transactions found.");
        } else {
            System.out.println("Largest debit transaction: ");
            System.out.println("ID: " + largestDebit.id);
            System.out.println("Amount: " + largestDebit.amount);
            System.out.println("Category: " + largestDebit.category);

        }
        System.out.println("----------ALL TRANSACTIONS--------------");

        for (int i = 0; i < transactions.size(); i++) {
            System.out.println("ID: " + transactions.get(i).id);
            System.out.println("Type: " + transactions.get(i).type);
            System.out.println("Amount: " + transactions.get(i).amount);
            System.out.println("Category: " + transactions.get(i).category);
            System.out.println("---------------------------------------------");
        }

        // Display financial summary
        System.out.println("\n--- FINANCIAL SUMMARY ---");
        System.out.println("Total credits: " + totalCredit);
        System.out.println("Total debits: " + totalDebit);
        System.out.println("Final balance: " + balance);
        System.out.println("Total transactions: " + transactions.size());
        System.out.println("Highest debit category: " + highestDebitCategory);
        System.out.println("Total spent in that category: " + highestDebitAmount);
        scanner.close();
    }

    public static double calculateTotalCredit(ArrayList<Transaction> transactions) {
        double totalCredit = 0;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction current = transactions.get(i);
            if (current.type.equals("CREDIT")) {
                totalCredit += current.amount;
            }
        }
        return totalCredit;
    }

    public static double calculateTotalDebit(ArrayList<Transaction> transactions) {
        double totalDebit = 0;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction current = transactions.get(i);
            if (current.type.equals("DEBIT")) {
                totalDebit += current.amount;
            }
        }
        return totalDebit;
    }
    public static double calculateBalance(double totalCredit, double totalDebit){
        return totalCredit - totalDebit;
    }
    public static String findHighestDebitCategory(ArrayList<Transaction> transactions){
        HashMap<String, Double> debitByCategory = new HashMap<>();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction current = transactions.get(i);
            if (current.type.equals("DEBIT")) {
                double previousTotal = debitByCategory.getOrDefault(current.category, 0.0);
                debitByCategory.put(current.category, previousTotal + current.amount);
            }
        }

        String highestDebitCategory = "None";
        double highestDebitAmount = 0;
        for (String category : debitByCategory.keySet()) {
            double categoryTotal = debitByCategory.get(category);

            if (categoryTotal > highestDebitAmount) {
                highestDebitAmount = categoryTotal;
                highestDebitCategory = category;
            }
        }
        return highestDebitCategory;
    }
    public static double findHighestDebitCategoryAmount(ArrayList<Transaction> transactions){
        HashMap<String, Double> debitByCategory = new HashMap<>();
        for (int i = 0; i<transactions.size();i++){
            Transaction current = transactions.get(i);

            if (current.type.equals("DEBIT")){
                double previousTotal = debitByCategory.getOrDefault(current.category, 0.0);
                debitByCategory.put(current.category, previousTotal + current.amount);
            }
        }
        double highestDebitAmount = 0;
        for (String category : debitByCategory.keySet()){
            double categoryTotal = debitByCategory.get(category);
            if (categoryTotal>highestDebitAmount){
                highestDebitAmount = categoryTotal;
            }
        }
        return highestDebitAmount;
    }
}