<?php
    require_once("db_connection.php");

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        
        $author = intval($_POST['author']);

        $query = "SELECT * FROM books2 WHERE author = '$author'";
        $result = $db_connection->query($query);

    }

?>

<!DOCTYPE html>
<html>
    <body>
        <h3>~ Lista dei libri ~</h3>

        <table border = 3>
            <tr>
                <th>ISBN</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Country</th>
                <th>Price</th>
            </tr>
            <?php
                while($row = $result -> fetch_assoc()){
                    $author = $row['author'];
                    $query = "SELECT firstname FROM authors WHERE id = '$author'";
                    $authorResult = $db_connection->query($query);
                    $author = $authorResult -> fetch_assoc();

                    $country = $row['country'];
                    $query = "SELECT nicename FROM country WHERE id = '$country'";
                    $countryResult = $db_connection->query($query);
                    $country = $countryResult -> fetch_assoc();
                ?>
                    <tr>
                    <td><?php echo $row['isbn']; ?></td>
                    <td><?php echo $row['title']; ?></td>
                    <td><?php echo $author['firstname'] ?></td>
                    <td><?php echo $row['publisher']; ?></td>
                    <td><?php echo $country['nicename']; ?></td>
                    <td><?php echo $row['price']; ?></td>
                    </tr>
                <?php
                }
            ?>
        </table>
                <br>
        <form action="index.php"><button>Torna alla lista del libri</button></form>
    </body>   
</html>