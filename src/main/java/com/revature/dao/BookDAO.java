package com.revature.dao;

import com.revature.model.Book;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a
 * database. The methods here are mostly filled out, you will just need to add a SQL statement.
 *
 * We may assume that the database has already created a table named 'book'.
 * It contains similar values as the Author class:
 * isbn, which is of type int and is a primary key,
 * author_id, which is of type int, and is a foreign key associated with the column 'id' of 'author',
 * title, which is of type varchar(255),
 * copies_available, which is of type int.
 */
public class BookDAO {

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        String sql = "select * FROM book";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //read the retrieved values from DB:
            while(rs.next()){
                //First create Book object to initialize it with constructor:
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available")
                        );
                //once its ready add it to our list of books that we created before:
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    /**
     * TODO: retrieve a book from the Book table, identified by its isbn.
     * You only need to change the sql String and leverage PreparedStatement's setString and setInt methods.
     * @return a book identified by isbn.
     */

    public Book getBookByIsbn(int isbn){
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * FROM book where isbn = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,isbn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available")
                        );
                return  book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * TODO: insert a book into the Book table.
     * Unlike some of the other insert problems, the primary key here will be provided by the client as part of the
     * Book object. Given the specific nature of an ISBN as both a numerical organization of books outside of this
     * database, and as a primary key, it would make sense for the client to submit an ISBN when submitting a book.
     * You only need to change the sql String and leverage PreparedStatement's setString and setInt methods.
     */

    public Book insertBook(Book book){
        Connection connection = ConnectionUtil.getConnection();

        String sql = "insert into book values (?, ?, ?, ?)";
                    try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, book.getIsbn());
                ps.setInt(2, book.getAuthor_id());
                ps.setString(3, book.getTitle());
                ps.setInt(4, book.getCopies_available());

                int result = ps.executeUpdate();
                if (result > 0) {
                    return book;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return null;
    }

    /**
     * TODO: retrieve all books from the Book table with copies_available over zero.
     * You only need to change the sql String and leverage PreparedStatement's setString and setInt methods.
     * @returnall books with book count > 0.
     */

    public List<Book> getBookWithBookCountOverZero(){
        List<Book> books = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM BOOK WHERE COPIES_AVAILABLE > ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available")
                        );

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

}
