<?php
    require_once("db_connection.php");

    $query = "SELECT * FROM books2";
    $result = $db_connection->query($query);

?>

<!DOCTYPE html>
<html>
    <body>
        <h3>~ Lista dei libri ~</h3>

        <table border = 10>
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
        <form action='insert.php'><button>Inserisci un nuovo libro</button></form>
        <br>
        <form action='author.php'><button>Ricerca per autore</button></form>
        <br>
        <form action='price.php'><button>AUMENTA I FOTTUTI PREZZI</button></form>

    </body>   
</html>

