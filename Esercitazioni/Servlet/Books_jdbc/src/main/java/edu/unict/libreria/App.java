package edu.unict.libreria;

import java.io.*; 
import java.sql.*;

import org.hibernate.jdbc.Expectations;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Books")
public class App extends HttpServlet{
    private Connection conn;
    private final String connString = "jdbc:mysql://localhost:3306/exam?user=root&password=mysql";

    public void init(){
        try{
            conn = DriverManager.getConnection(connString);
            System.out.println("Connessione effettuata con successo");
        }catch(Exception e){}
    }

    private PrintWriter initWebPage(HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter page = null;
        try{
            page = response.getWriter();
        }catch(Exception e){}

        page.write("<html>");        
        page.write("<head><title>LIBRARY</title></head>");
        page.write("<head><h3>LIBRARY</h3></head>");
        page.write("<body>");
        return page;
    }

    private void closeWebPage(PrintWriter page){
        page.write("</body></html>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        PrintWriter page = initWebPage(response);
        ResultSet books = getBooks();

        String action = request.getParameter("action");
        if("update".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            updateBookForm(response, id);
        }else if("delete".equals(action)){
            System.out.println("DELETE");
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");
                stmt.setInt(1, id);

                stmt.executeUpdate();
            }catch(Exception e){}
            //prendo nuovo set di libri
            books = getBooks();
            showBooks(books, page);
            showAddBookForm(page);
        }else {
            showBooks(books, page);
            showAddBookForm(page);
        }
        closeWebPage(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        PrintWriter page = initWebPage(response);

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        int ranking = Integer.parseInt(request.getParameter("ranking"));
        int year = Integer.parseInt(request.getParameter("year"));
        int price = Integer.parseInt(request.getParameter("price"));

        String action = request.getParameter("action");

        if("update".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                PreparedStatement stmt = conn.prepareStatement("UPDATE books SET isbn = ?, title = ?, author = ?, publisher = ?, ranking = ?, year = ?, price = ? WHERE id = ?");
                stmt.setString(1, isbn);
                stmt.setString(2, title);
                stmt.setString(3, author);
                stmt.setString(4, publisher);
                stmt.setInt(5, ranking);
                stmt.setInt(6, year);
                stmt.setInt(7, price);
                stmt.setInt(8, id);

                stmt.executeUpdate();
            }catch(Exception e){}

        }
        else if("delete".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));            
            try{
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");
                stmt.setInt(1, id);

                stmt.executeUpdate();
            }catch(Exception e){}
        }else{
            try{

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (isbn, title, author, publisher, ranking, year, price) VALUES (?,?,?,?,?,?,?)");
                stmt.setString(1, isbn);
                stmt.setString(2, title);
                stmt.setString(3, author);
                stmt.setString(4, publisher);
                stmt.setInt(5, ranking);
                stmt.setInt(6, year);
                stmt.setInt(7, price);

                stmt.executeUpdate();

            }catch(Exception e){}
            
        }
        ResultSet books = getBooks();
        showBooks(books, page);
        showAddBookForm(page);
        closeWebPage(page);
    }

    private ResultSet getBooks(){
        ResultSet result = null;
        String query = "SELECT * FROM books;";
        try{
            result = conn.createStatement().executeQuery(query);
        }catch(Exception e){}

        return result;
    }

    private void showBooks(ResultSet books, PrintWriter page){
        try{
            page.write("<table border = 1><tr><th>ISBN</th>");
            page.write("<th>Title</th>");            
            page.write("<th>Author</th>");
            page.write("<th>Publisher</th>");
            page.write("<th>Ranking</th>");
            page.write("<th>Year</th>");
            page.write("<th>Price</th><th></th></tr>");

            while(books.next()){
                String id = books.getString("id");                
                String isbn = books.getString("isbn");                
                String title = books.getString("title");
                String author = books.getString("author");
                String publisher = books.getString("publisher");
                int ranking = books.getInt("ranking");
                int year = books.getInt("year");
                int price = books.getInt("price");   

                page.write("<tr>");
                page.write("<td>" + "<a href='/Books?action=update&id=" + id + "'> " + isbn + " </a>" + "</td>");
                page.write("<td>" + title + "</td>");
                page.write("<td>" + author + "</td>");
                page.write("<td>" + publisher + "</td>");
                page.write("<td>" + ranking + "</td>");
                page.write("<td>" + year + "</td>");
                page.write("<td>" + price + "</td>");

                page.write("<td><a href='/Books?action=delete&id=" + id + "'><button> Cancella </button> </a> </td>");
                page.write("</tr>");

            }
            page.write("</table>");
        }catch(Exception e){}
    }

    private void showAddBookForm(PrintWriter page){
        page.write("<br> <h1> Inserirsci un nuovo libro </h1>");        
        page.write("<form action='/Books' method='POST'>");
        page.write("<input type='text' name='isbn' placeholder='isbn'/>");
        page.write("<input type='text' name='title' placeholder='title'/>");
        page.write("<input type='text' name='author' placeholder='author'/>");
        page.write("<input type='text' name='publisher' placeholder='publisher'/>");
        page.write("<input type='text' name='ranking' placeholder='ranking'/>");
        page.write("<input type='text' name='year' placeholder='year'/>");
        page.write("<input type='text' name='price' placeholder='price'/>");
        page.write("<input type='submit' value='Inserisci'/>");
        page.write("</form>");
    }

    private void showUpdateBookForm(PrintWriter page, ResultSet book) {
        try {
            page.write("<br> <h1> Aggiorna libro </h1>");
            page.write("<form action='/Books' method='POST'>");
            page.write("<input type='hidden' name='action' value='update'/>");
            page.write("<input type='hidden' name='id' value='" + book.getInt("id") + "'/>");
            page.write("<input type='text' name='isbn' placeholder='isbn' value='" + book.getString("isbn") + "'/>");
            page.write("<input type='text' name='title' placeholder='title' value='" + book.getString("title") + "'/>");
            page.write("<input type='text' name='author' placeholder='author' value='" + book.getString("author") + "'/>");
            page.write("<input type='text' name='publisher' placeholder='publisher' value='" + book.getString("publisher") + "'/>");
            page.write("<input type='text' name='ranking' placeholder='ranking' value='" + book.getInt("ranking") + "'/>");
            page.write("<input type='text' name='year' placeholder='year' value='" + book.getInt("year") + "'/>");
            page.write("<input type='text' name='price' placeholder='price' value='" + book.getInt("price") + "'/>");
            page.write("<input type='submit' value='Aggiorna'/>");
            page.write("</form>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBookForm(HttpServletResponse response, int bookId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
            stmt.setInt(1, bookId);
            ResultSet book = stmt.executeQuery();

            if (book.next()) {
                PrintWriter page = initWebPage(response);
                showUpdateBookForm(page, book);
                closeWebPage(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private void deleteBook(int bookId){

    // }
}