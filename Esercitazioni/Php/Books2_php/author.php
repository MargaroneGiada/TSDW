<?php
    require_once("db_connection.php");

?>


<!DOCTYPE html>
<html>
    <body>
        <h2>Seleziona autore</h2>
        <form method="POST" action = "showAuthors.php">
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
            <button> Cerca! </button>
        </form>
    </body>
</html>