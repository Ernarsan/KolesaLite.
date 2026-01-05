package com.kolesalite.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String email;
    private String password;
    private double balance;
    private Set<String> favoriteCarIds;
    private Set<String> purchasedCarIds;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = 100000.0;
        this.favoriteCarIds = new HashSet<>();
        this.purchasedCarIds = new HashSet<>();
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void addMoney(double amount) { this.balance += amount; }
    public boolean deductMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public Set<String> getFavoriteCarIds() { return favoriteCarIds; }
    public void addFavorite(String carId) { favoriteCarIds.add(carId); }
    public void removeFavorite(String carId) { favoriteCarIds.remove(carId); }
    public boolean isFavorite(String carId) { return favoriteCarIds.contains(carId); }

    public Set<String> getPurchasedCarIds() { return purchasedCarIds; }
    public void addPurchased(String carId) { purchasedCarIds.add(carId); }
    public boolean isPurchased(String carId) { return purchasedCarIds.contains(carId); }
}