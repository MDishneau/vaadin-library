package com.library.backend.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    boolean existsByOpenLibraryKey(String openLibraryKey);
    void deleteByOpenLibraryKey(String openLibraryKey);

    @Query("SELECT b FROM Book b WHERE :username MEMBER OF b.favouritedByUsers")
    List<Book> findUserFavourites(@Param("username") String username);
}

