
import java.util.Scanner;

public class ATMSystem {
    private static int userId;
    private static int userPin;
    private static boolean isLoggedIn;
    private static double balance;
    private static StringBuilder transactionHistory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        isLoggedIn = false;
        balance = 0.0;
        transactionHistory = new StringBuilder();

        while (true) {
            if (!isLoggedIn) {
                System.out.print("Enter User ID: ");
                userId = scanner.nextInt();
                System.out.print("Enter User PIN: ");
                userPin = scanner.nextInt();
                isLoggedIn = true;
            }

            System.out.println("ATM Functions:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewTransactionHistory() {
        System.out.println("Transaction History:");
        System.out.println(transactionHistory.toString());
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        String transaction = "Withdraw: $" + amount;
        transactionHistory.append(transaction).append("\n");
        System.out.println(transaction);
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        balance += amount;
        String transaction = "Deposit: $" + amount;
        transactionHistory.append(transaction).append("\n");
        System.out.println(transaction);
    }

    private static void transfer(Scanner scanner) {
        System.out.print("Enter the recipient's account number: ");
        int recipientId = scanner.nextInt();

        if (recipientId == userId) {
            System.out.println("Cannot transfer funds to the same account.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        String transaction = "Transfer to account " + recipientId + ": $" + amount;
        transactionHistory.append(transaction).append("\n");
        System.out.println(transaction);
    }
}
