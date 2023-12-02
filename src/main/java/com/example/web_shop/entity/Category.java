package com.example.web_shop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categoty")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cat_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;

        return id == category.getId();

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Category() {
    }

    public Category(String categoryName, Set<Product> books) {
        this.categoryName = categoryName;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Product> getBooks() {
        return books;
    }

    public void setBooks(Set<Product> books) {
        this.books = books;
    }
}
