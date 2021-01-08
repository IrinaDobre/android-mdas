package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.IBuilder;

public class Book {
    //mandatory attributes
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String price;
    private String thumbnail;

    //optional attributes
    private String buy;
    private String preview;
    private String categories;
    private int pageCount;
    private String url;

    private Book(Builder builder) {
        this.title = builder.title;
        this.authors = builder.authors;
        this.publishedDate = builder.publishedDate;
        this.description = builder.description;
        this.categories = builder.categories;
        this.thumbnail = builder.thumbnail;
        this.buy = builder.buy;
        this.preview = builder.preview;
        this.price = builder.price;
        this.pageCount = builder.pageCount;
        this.url = builder.url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", categories='" + categories + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", buy='" + buy + '\'' +
                ", preview='" + preview + '\'' +
                ", price='" + price + '\'' +
                ", pageCount=" + pageCount +
                ", url='" + url + '\'' +
                '}';
    }

    public static class Builder implements IBuilder {
        //mandatory attributes
        private String title;
        private String authors;
        private String publishedDate;
        private String description;
        private String price;
        private String thumbnail;

        //optional attributes
        private String buy;
        private String preview;
        private String categories;
        private int pageCount;
        private String url;

        public Builder(String title, String authors, String publishedDate, String description,
                       String price, String thumbnail){
            super();
            this.title = title;
            this.authors = authors;
            this.publishedDate = publishedDate;
            this.description = description;
            this.price = price;
            this.thumbnail = thumbnail;
        }

        public Builder setBuy(String buy) {
            this.buy = buy;
            return this;
        }

        public Builder setPreview(String preview) {
            this.preview = preview;
            return this;
        }

        public Builder setCategories(String categories) {
            this.categories = categories;
            return this;
        }

        public Builder setPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }
    }
}
