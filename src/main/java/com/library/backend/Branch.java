package com.library.backend;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // one branch has many books
    @OneToMany( mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>(); // this is a virtual field (does not exist in the branch db table)
    // this field is used for convenience so that we can find the books within a particular branch in our code

    public Branch() {}

    public Branch(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return id != null && id.equals(branch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
