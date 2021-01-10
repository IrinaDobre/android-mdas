package com.example.mpaiproject.models;

public class ShoppingCartItem {
    private double initialPrice;
    private String title;
    private String authors;
    private int quantity;
    private double totalPrice;
    private String imageBook;

    public ShoppingCartItem() {

    }

    public ShoppingCartItem(double initialPrice, String title, String authors, int quantity, double totalPrice, String imageBook) {
        this.initialPrice = initialPrice;
        this.title = title;
        this.authors = authors;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.imageBook = imageBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "initialPrice=" + initialPrice +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", imageBook='" + imageBook + '\'' +
                '}';
    }
}