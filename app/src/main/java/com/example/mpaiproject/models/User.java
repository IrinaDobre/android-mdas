package com.example.mpaiproject.models;

import com.example.mpaiproject.models.designpatterns.builder.Book;
import com.example.mpaiproject.models.designpatterns.strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    public static PaymentStrategy playmentMethod;
    public static List<ShoppingCartItem> shoppingList = new ArrayList<>();
    public static List<Book> favListBook = new ArrayList<>();

    public User() {}

    public void addBookFavList(Book favBook) {
        User.favListBook.add(favBook);
    }

    public int getFavListSize(){
        return User.favListBook.size();
   }

    public void addBookShoppingList(ShoppingCartItem item) {
        User.shoppingList.add(item);
    }

   public double getShoppingCartTotalPrice(){
        double totalPrice = 0.0;
        for(ShoppingCartItem item : User.shoppingList){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
   }

    public static void setPlaymentMethod(PaymentStrategy playmentMethod) {
        User.playmentMethod = playmentMethod;
    }

    public static String pay() {
        return playmentMethod.getTypeOfPayment();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
