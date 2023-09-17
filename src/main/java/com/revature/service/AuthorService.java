package com.revature.service;

import com.revature.dao.AuthorDAO;
import com.revature.model.Author;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class AuthorService {
    private AuthorDAO authorDAO;
    /**
     * no-args constructor for creating a new AuthorService with a new AuthorDAO.
     * There is no need to change this constructor.
     */
    public AuthorService (){
        authorDAO = new AuthorDAO();
    }
    public AuthorService(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }

    /**
     * TODO: Use the AuthorDAO to retrieve all authors.
     *
     * @return all authors
     */

    public List<Author> getAllAuthors(){
        List<Author> authors = authorDAO.getAllAuthors();
        return authors;
    }
    /**
     * TODO: Use the AuthorDAO to persist an author. The given Author will not have an id provided.
     *
     * @param author an author object.
     * @return The persisted author if the persistence is successful.
     */

    public Author addAuthor(Author author){
        Author author1 = authorDAO.insertAuthor(author);
        return author1;
    }

}
