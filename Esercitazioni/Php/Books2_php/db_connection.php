<?php 
    $server = "localhost";
    $user = "root";
    $password = "mysql";
    $db_name = "exam";
    $db_connection = new mysqli($server, $user, $password, $db_name) or die( ("Connessione al server fallita!"));
?>