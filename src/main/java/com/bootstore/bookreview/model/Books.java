package com.bootstore.bookreview.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    private String book_name;
    private double price;
    private String seller_info;
    private String author;
    @Lob
    @Column(length = 500)
    private String description;
    @Column(name="likes", columnDefinition = "int default 0")
    private int likes;
    public Long getId() {
        return id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeller_info() {
        return seller_info;
    }

    public void setSeller_info(String seller_info) {
        this.seller_info = seller_info;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
