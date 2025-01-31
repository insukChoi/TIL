package com.insuk.reactor.sample;

public class Book {
    private String bookName;
    private String authorName;
    private String penName;
    private int price;
    private int stockQuantity;

    public Book(final String bookName, final String authorName, final String penName, final int price, final int stockQuantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.penName = penName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPenName() {
        return penName;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}