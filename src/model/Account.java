package model;

public class Account {

    private int accountId;
    private int userId;
    private String accountType;
    private double balance;

    // Constructors
    public Account() {}

    public Account(int userId, String accountType, double balance) {
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters & Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
}
