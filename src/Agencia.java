import java.util.ArrayList;
import java.util.Scanner;

import model.Account;
import model.User;
//import model.Transaction;
//import utils.Utils;

public class Agencia {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Account> accountList;

    public static void main(String[] args) {
        accountList = new ArrayList<>();
        operations();
    }

    public static void operations() {
        System.out.println("");
        System.out.println("---Welcome to Digital Bank---");
        System.out.println("1) - Create Account");
        System.out.println("2) - Deposit");
        System.out.println("3) - Withdraw");
        System.out.println("4) - Transfer");
        System.out.println("5) - List Transactions");
        System.out.println("6) - listStatement");
        System.out.println("0) - Exit");
        System.out.println("");
        System.out.println("Select an option:");

        int operation = input.nextInt();

        switch (operation) {
            case 1:
                createAccount();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                transfer();
                break;
            case 5:
                listTransactions(); 
                break;
            case 6:
                listStatement();
                break;
                
            case 7:
                System.out.println("Thank you for using our services.");
                System.exit(0);
            default:
                System.out.println("Invalid option.");
                operations();
                break;
        }
    }

    public static void createAccount() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("\nEnter user ID: ");
            int id = input.nextInt();

            System.out.println("Enter user name: ");
            String name = input.next();

            System.out.println("Enter user email: ");
            String email = input.next();

            System.out.println("Enter user password: ");
            String password = input.next();

            User owner = new User(id, name, email, password);

            Account account = new Account(0, 0.0, owner);

            accountList.add(account);

            System.out.println("Account created successfully!");
            operations();
        }
    }

    private static Account findAccount(int accountNumber) {
        Account account = null;
        if (accountList.size() > 0) {
            for (Account acc : accountList) {
                if (acc.getAccountNumber() == accountNumber) {
                    account = acc;
                    break;
                }
            }
        }
        return account;
    }

    public static void deposit() {
        System.out.println("\nEnter account number: ");
        int accountNumber = input.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.println("Enter the deposit amount: ");
            double depositAmount = input.nextDouble();
            account.deposit(depositAmount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Account not found!");
        }
        operations();
    }

    public static void withdraw() {
        System.out.println("\nEnter account number: ");
        int accountNumber = input.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.println("Enter the withdrawal amount: ");
            double withdrawalAmount = input.nextDouble();
            account.withdraw(withdrawalAmount);
        } else {
            System.out.println("Account not found!");
        }
        operations();
    }

    public static void transfer() {
        System.out.println("\nEnter sender's account number: ");
        int senderAccountNumber = input.nextInt();

        Account senderAccount = findAccount(senderAccountNumber);

        if (senderAccount != null) {
            System.out.println("Enter recipient's account number: ");
            int recipientAccountNumber = input.nextInt();

            Account recipientAccount = findAccount(recipientAccountNumber);

            if (recipientAccount != null) {
                System.out.println("Enter the transfer amount: ");
                double transferAmount = input.nextDouble();

                senderAccount.transfer(recipientAccount, transferAmount);
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Sender account not found!");
        }
        operations();
    }

    public static void listTransactions() {
        System.out.println("\nEnter account number to view transactions: ");
        int accountNumber = input.nextInt();

        Account account = findAccount(accountNumber);

        if (account != null) {
            account.listTransactions();
        } else {
            System.out.println("Account not found!");
        }
        operations();
    }

    public static void listStatement() {
        System.out.println("\nEnter account number to view statement: ");
        int accountNumber = input.nextInt();
    
        Account account = findAccount(accountNumber);
    
        if (account != null) {
            System.out.println(account.getStatement());
        } else {
            System.out.println("Account not found!");
        }
        operations();
    }
    
}
