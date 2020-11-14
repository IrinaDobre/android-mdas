package com.example.mdasproject.classes;

public class Book {
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String categories;
    private String thumbnail;
    private String buy;
    private String preview;
    private String price;
    private int pageCount;
    private String url;

    public Book () {}

    public Book(String title, String authors, String publishedDate, String description,
                String categories, String thumbnail, String buy, String preview,
                String price, int pageCount, String url) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.categories = categories;
        this.thumbnail = thumbnail;
        this.buy = buy;
        this.preview = preview;
        this.price = price;
        this.pageCount = pageCount;
        this.url = url;
    }

    public Book(String title, String author, String publishedDate, String thumbnail, String previewLink, int pageCount, String url) {
        this.title = title;
        this.authors = author;
        this.publishedDate = publishedDate;
        this.thumbnail = thumbnail;
        this.preview = previewLink;
        this.pageCount = pageCount;
        this.url = url;
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

    public String getCategories() {
        return categories;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getBuy() {
        return buy;
    }

    public String getPreview() {
        return preview;
    }

    public String getPrice() {
        return price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
