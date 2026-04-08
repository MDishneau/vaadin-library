package com.library.backend.entities;

import com.vaadin.copilot.shaded.checkerframework.common.aliasing.qual.Unique;
import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String openLibraryKey;

    private String title;
    private String author;
    private String isbn;

    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable(
            name = "book_favourites", // name of bridge table
            joinColumns = @JoinColumn(name = "book_id") // name of bridge table column referencing book id
    )
    @Column(name = "username") // name of bridge table column referencing username
    private Set<String> favouritedByUsers = new HashSet<>(); // use hash set since a collection of unique usernames

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String key, String title, String author, String isbn) {
        this.openLibraryKey = key;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Set<String> getFavouritedByUsers() {
        return favouritedByUsers;
    }

    public void setFavouritedByUsers(Set<String> favouritedByUsers) {
        this.favouritedByUsers = favouritedByUsers;
    }

    public Long getId() {
        return id;
    }

    public String getOpenLibraryKey() {
        return openLibraryKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}