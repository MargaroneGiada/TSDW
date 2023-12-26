<?php

    if($_SERVER["REQUEST_METHOD"] == "GET"){
        if($_GET["id"]){    
            require_once("db_connection.php");
            $id = $db_connection ->real_escape_string($_GET["id"]);

            $query = "DELETE FROM books WHERE id = '$id'";
            $result = $db_connection->query($query);
            header("Location: list.php");
        }
    }
?>

