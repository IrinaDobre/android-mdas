package com.example.mdasproject.classes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("Title", "Author", "Date", "Img", "Preview", 100, "URL");
    }

    @Test
    public void initBook(){
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthors());
        assertEquals("Date", book.getPublishedDate());
        assertEquals("Img", book.getThumbnail());
        assertEquals("Preview", book.getPreview());
        assertEquals(100, book.getPageCount());
        assertEquals("URL", book.getUrl());
    }

}