<?php
    require_once("db_connection.php");
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        $id = $db_connection->real_escape_string( $_POST["id"]);
        $isbn = $db_connection->real_escape_string( $_POST["isbn"]);
        $title = $db_connection->real_escape_string( $_POST["title"]);
        $author = $db_connection->real_escape_string( $_POST["author"]);
        $publisher = $db_connection->real_escape_string( $_POST["publisher"]);
        $ranking = intval( $_POST["ranking"]);
        $year = intval( $_POST["year"]);
        $price = intval( $_POST["price"]);

        $query = "UPDATE books SET isbn = '$isbn', title = '$title' , author = '$author' , publisher = '$publisher' , ranking = '$ranking' , year = '$year' , price = '$price' WHERE id = '$id'";
        // $query = "UPDATE books SET isbn = '$isbn' WHERE id = '$id'";
        $result = $db_connection->query($query);

        header("Location: list.php"); 
    }else{
        if($_GET["id"]){
            $id = $db_connection->real_escape_string($_GET["id"]);
            $query = "SELECT * FROM books WHERE id = '$id'";
            $result = $db_connection->query($query);
            $row = $result->fetch_assoc();
        }
    }
?>


<!DOCTYPE html>
<html>
    <body>
        <h2>Aggiorna libro</h2>
        <form action=index.php><button>Back</button></form>
        <form method="POST">
            <input type="hidden" name="id" value="<?php echo $row['id']; ?>" />
            <input type="text" name = "isbn" placeholder = "ISBN" value="<?php echo $row['isbn']?>"> 
            <input type="text" name = "title" placeholder = "Titolo" value="<?php echo $row['title']?>"> 
            <input type="text" name = "author" placeholder = "Autore" value="<?php echo $row['author']?>"> 
            <input type="text" name = "publisher" placeholder = "Casa editrice" value="<?php echo $row['publisher']?>"> 
            <input type="text" name = "ranking" placeholder = "Valutazione" value="<?php echo $row['ranking']?>"> 
            <input type="text" name = "year" placeholder = "Year" value="<?php echo $row['year']?>"> 
            <input type="text" name = "price" placeholder = "Price" value="<?php echo $row['price']?>">
            
            <button> Inserisci </button>
            <br>
            
        </form>
        <a href="delete.php?id=<?php echo $row["id"]; ?>"><button>Cancella!</button></a></form>
    </body>
</html>