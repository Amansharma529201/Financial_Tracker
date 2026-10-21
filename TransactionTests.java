import java.util.ArrayList;
public class TransactionTests {
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1, "CREDIT", 5000, "SALARY"));
        transactions.add(new Transaction(2, "DEBIT", 500, "FOOD"));
        transactions.add(new Transaction(3, "DEBIT", 1200, "TRAVEL"));

        double totalCredit = Main.calculateTotalCredit(transactions);
        double totalDebit = Main.calculateTotalDebit(transactions);
        double balance = Main.calculateBalance(totalCredit, totalDebit);

        System.out.println("Total credit: " + totalCredit);
        System.out.println("Total debit: " + totalDebit);
        System.out.println("Balance: " + balance);

        String highestCategory =
                Main.findHighestDebitCategory(transactions);

        if (highestCategory.equals("TRAVEL")) {
            System.out.println("PASS: Highest debit category");
        } else {
            System.out.println("FAIL: Highest debit category");
        }

        Transaction largestDebit = null;

        for (Transaction current : transactions) {
            if (current.type.equals("DEBIT")
                    && (largestDebit == null
                    || current.amount > largestDebit.amount)) {
                largestDebit = current;
            }
        }

        if (largestDebit != null
                && largestDebit.amount == 1200
                && largestDebit.category.equals("TRAVEL")) {
            System.out.println("PASS: Largest individual debit");
        } else {
            System.out.println("FAIL: Largest individual debit");
        }


        if (totalCredit == 5000
                && totalDebit == 1700
                && balance == 3300) {
            System.out.println("PASS: Basic financial summary");
        } else {
            System.out.println("FAIL: Basic financial summary");
        }
    }
}
