<?php
    require_once("db_connection.php");
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        $isbn = $db_connection->real_escape_string( $_POST["isbn"]);
        $isbn = $db_connection->real_escape_string( $_POST["isbn"]);
        $title = $db_connection->real_escape_string( $_POST["title"]);
        $author = $db_connection->real_escape_string( $_POST["author"]);
        $publisher = $db_connection->real_escape_string( $_POST["publisher"]);
        $ranking = intval( $_POST["ranking"]);
        $year = intval( $_POST["year"]);
        $price = intval( $_POST["price"]);

        $query = "INSERT INTO books(isbn, title, author, publisher, ranking, year, price) VALUES ('$isbn', '$title', '$author', '$publisher', '$ranking', '$year', '$price')";
        $result = $db_connection->query($query);

        header("Location: list.php"); 
    }
?>


<!DOCTYPE html>
<html>
    <body>
        <h2>Aggiungi libro</h2>
        <form action=index.php><button>Back</button></form>
        <form method="POST">
            <input type="text" name = "isbn" placeholder = "ISBN"> 
            <input type="text" name = "title" placeholder = "Titolo"> 
            <input type="text" name = "author" placeholder = "Autore"> 
            <input type="text" name = "publisher" placeholder = "Casa editrice"> 
            <input type="text" name = "ranking" placeholder = "Valutazione"> 
            <input type="text" name = "year" placeholder = "Year"> 
            <input type="text" name = "price" placeholder = "Price">
            
            <button> Inserisci </button>
        </form>
        
    </body>
</html>