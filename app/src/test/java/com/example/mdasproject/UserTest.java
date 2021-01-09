package com.example.mdasproject;

import com.example.mdasproject.models.Book;
import com.example.mdasproject.models.ShoppingCartItem;
import com.example.mdasproject.models.User;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;
    Book book;
    ShoppingCartItem shoppingCartItem1;
    ShoppingCartItem shoppingCartItem2;

    @Before
    public void setUp(){
        user = new User();
        //book = new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling",
          //      "1997", "thumbnail", "preview", 223, "URL");
        shoppingCartItem1 = new ShoppingCartItem(30.5, "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 1, 30.5, "Image");
        shoppingCartItem2 = new ShoppingCartItem(50.5, "Harry Potter and the Philosopher's Stone", "J. K. Rowling", 1, 50.5, "Image");
    }

    @Test
    public void addBookToUserFavList(){
        assertEquals(0, user.getFavListSize());
        user.addBookFavList(book);
        assertEquals(1, user.getFavListSize());
    }

    @Test
    public void getShoppingCartTotalPrice(){
        user.addBookShoppingList(shoppingCartItem1);
        user.addBookShoppingList(shoppingCartItem2);

        double expected = 81.0;
        double actual = user.getShoppingCartTotalPrice();
        assertEquals(expected, actual, 0.1);
    }
}