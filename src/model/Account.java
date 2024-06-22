package model;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

import java.util.Date;

public class Account {

    private static int counter2 = 1;

    private int accountNumber;
    private double balance = 0.0;
    private User owner;
    private List<Transaction> transactions;

    public Account(int accountNumber, double balance, User owner) {
        this.accountNumber = counter2;
        this.balance = balance;
        this.owner = owner;
        this.transactions = new ArrayList<>();
        counter2 += 1;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "\nAccountNumber: " + getAccountNumber() +
               "\nOwner: " + getOwner() +
               "\nBalance: " + Utils.doubleToString(getBalance());
    }

    public void deposit(double amount) {
        System.out.println("");
        System.out.println("---Processing deposit---");
        System.out.println("-Previous balance: " + getBalance());

        if (amount > 0) {
            setBalance(getBalance() + amount);
            System.out.println("Your deposit was successfully processed");
            Transaction transaction = new Transaction(
                "DEP" + System.currentTimeMillis(), 
                this, 
                null, 
                amount, 
                new Date()
            );
            addTransaction(transaction);
        } else {
            System.out.println("It was not possible to process the deposit.");
        }

        System.out.println("-Current Balance: " + getBalance());
        System.out.println("---Deposit complete---");
        System.out.println("");
    }

    public void withdraw(double amount) {
        System.out.println("");
        System.out.println("---Processing withdrawal---");
        System.out.println("-Previous balance: " + getBalance());

        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful");
            Transaction transaction = new Transaction(
                "WIT" + System.currentTimeMillis(), 
                this, 
                null, 
                amount, 
                new Date()
            );
            addTransaction(transaction);
        } else {
            System.out.println("Insufficient funds");
        }

        System.out.println("-Current balance: " + getBalance());
        System.out.println("---Withdrawal complete---");
        System.out.println("");
    }

    public void transfer(Account recipientAccount, double amount) {
        System.out.println("");
        System.out.println("---Processing transfer---");
        System.out.println("-Previous balance: " + getBalance());
    
        if (amount > 0 && this.getBalance() >= amount) {
            setBalance(getBalance() - amount);
            recipientAccount.setBalance(recipientAccount.getBalance() + amount);
            System.out.println("Transfer successful!");
            System.out.println("-Current balance: " + getBalance());
            System.out.println("---Transfer complete---");
            Transaction transaction = new Transaction(
                "TRF" + System.currentTimeMillis(), 
                this, 
                recipientAccount, 
                amount, 
                new Date()
            );
            addTransaction(transaction);
            recipientAccount.addTransaction(transaction); // Adiciona a transação apenas na conta do destinatário
        } else {
            System.out.println("It was not possible to complete the transfer.");
            System.out.println("-Current balance: " + getBalance());
        }
        System.out.println("");
    }
       

    public void listTransactions() {
        if (transactions.size() > 0) {
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
        } else {
            System.out.println("No transactions found!");
        }
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("\n--- Account Statement ---\n");
        statement.append("Account Number: ").append(getAccountNumber()).append("\n");
        statement.append("Owner: ").append(getOwner().getName()).append("\n");
        statement.append("Balance: ").append(Utils.doubleToString(getBalance())).append("\n\n");
        statement.append("--- Transactions ---\n");
    
        if (transactions.size() > 0) {
            for (Transaction transaction : transactions) {
                statement.append(transaction.toString()).append("\n");
            }
        } else {
            statement.append("No transactions found!\n");
        }
    
        return statement.toString();
    }
    
}
