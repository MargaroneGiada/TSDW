<?php
    require_once("db_connection.php");
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        $isbn = $db_connection->real_escape_string($_POST['isbn']);
        $title = $db_connection->real_escape_string($_POST['title']);
        $author = intval($_POST['author']);
        $publisher = $db_connection->real_escape_string($_POST['publisher']);
        $country = intval($_POST["country"]);
        $price = intval($_POST["price"]);

        $query = "INSERT INTO books2 (isbn, title, author, publisher, country, price) VALUES ('$isbn', '$title', '$author','$publisher','$country', '$price')";
        $result = $db_connection->query($query);

        header("Location: index.php");
    }
?>

<!DOCTYPE html>
<html>
    <body>
        <h2>Aggiungi libro</h2>
        <form method="POST">
            <input type="text" name = "isbn" placeholder="ISBN">
            <input type="text" name = "title" placeholder="title">
            
            <select name="author">
                <?php 
                    $query = "SELECT * FROM authors";
                    $result = $db_connection->query($query);
                    while($row = $result->fetch_assoc()){ 
                        ?>
                        <option value="<?php echo $row['id']?>"><?php echo $row['firstname']?></option>
                <?php
                    }
                ?>
            </select>
            <input type="text" name = "publisher" placeholder="publisher">
            <select name="country">
                <?php 
                    $query = "SELECT * FROM country";
                    $result = $db_connection->query($query);
                    while($row = $result->fetch_assoc()){ 
                        ?>
                        <option value="<?php echo $row['id']?>"><?php echo $row['nicename']?></option>
                <?php
                    }
                ?>
            </select>
            <input type="text" name = "price" placeholder="price">

            <button> Aggiungi </button>
        </form>
    </body>
</html>