package com.homecredit.bankingappwithrest.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Account {


    @NotNull
    int accountId;

    @NotEmpty
    String name;
    String type;
    
    @Min(value = 100)
    double balance;

    String branch;
    public  Account(){
        
    }


    public Account(int id, String name, String type, double balance, String branch) {
        this.accountId = id;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.branch = branch;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Account [AccountId = " + accountId + ",  Name = " + name + ",  Type = " + type + ",  Balance = " + balance
                + " Rupees" + ",  Branch = " + branch + "]";
    }

    @Override
    public int hashCode() {
        int hashCode = this.accountId % 5;
//		System.out.println("HashCode - " + hashCode);
        return hashCode;
    }


}
