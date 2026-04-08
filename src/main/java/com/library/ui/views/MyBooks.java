package com.library.ui.views;

import com.library.backend.entities.Book;
import com.library.backend.entities.BookRepository;
import com.library.ui.components.BookGrid;
import com.library.ui.components.ViewToolbar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.userdetails.User;

@Route("books/favourites")
@PageTitle("My Books")
@Menu(order = 3, title = "My Books", icon = "vaadin:star")
@PermitAll
public class MyBooks extends VerticalLayout {
    private final BookRepository bookRepo;
    private final AuthenticationContext authContext;

    public MyBooks(BookRepository bookRepo, AuthenticationContext authContext) {
        this.bookRepo = bookRepo;
        this.authContext = authContext;

        String activeUsername = this.authContext.getAuthenticatedUser(User.class).map(User::getUsername).orElse("");

        BookGrid grid = new BookGrid(() -> bookRepo.findUserFavourites(activeUsername));
        grid.addFavoriteColumn(
            activeUsername,
            this.bookRepo::save
        );

        // navigate to Book Details page when I click on the grid item for that book
        grid.addItemClickListener(click -> {
            Book targetBook = click.getItem();
            getUI().ifPresent(ui -> ui.navigate("books/" + targetBook.getId()));
        });

        ViewToolbar header = new ViewToolbar("My Books");

        add(header, grid);
    }
}
