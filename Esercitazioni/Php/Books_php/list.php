<?php
    require_once("db_connection.php"); 
    $query = "SELECT * FROM books";
    $result = $db_connection -> query($query);
?>

<!DOCTYPE html>
<html>
    <body>
        <h2>Lista dei libri</h2>
        <form action=index.php><button>Back</button></form>
        <table border = "3">
            <tr>
                <h1>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Ranking</th>
                    <th>Year</th>
                    <th>Price</th>
                </h1>
            </tr>
            <?php
                while($row = $result->fetch_assoc()){
                    echo "<tr>";
                    echo "<input type=\"hidden\" name=\"id\" value" . $row["id"] . ">";
                    echo "<td><form><a href=update.php?id=". $row["id"] .">" . $row["isbn"] . "</a></form></td>";
                    echo "<td>" . $row["title"] . "</td>";
                    echo "<td>" . $row["author"] . "</td>";
                    echo "<td>" . $row["publisher"] . "</td>";
                    echo "<td>" . $row["ranking"] . "</td>";
                    echo "<td>" . $row["year"] . "</td>";
                    echo "<td>" . $row["price"] . "</td>";
                    echo "</tr>";
                }
            ?>
        </table>
        <form action=insert.php><button>Add a book </button></form>
    </body>
</html>