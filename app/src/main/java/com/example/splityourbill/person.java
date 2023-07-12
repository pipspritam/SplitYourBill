package com.example.splityourbill;

import androidx.annotation.NonNull;

public class person {
    public String name;
    public double balance;

    public person(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name = " + name + " -> " + "Balance = " + balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
