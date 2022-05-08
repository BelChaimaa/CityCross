<?php
$servername = "localhost";
$username = "root";
$password = "root";
$distance = $_POST["distance"];

$link = new mysqli($servername, $username, $password);
$tab = [];
$requete = "SELECT Name, Coordinates FROM citycross.villes ORDER BY Population DESC";
if ($result = mysqli_query($link, $requete)) {
    while ($ligne = mysqli_fetch_assoc($result)) {
        $tab[] = $ligne;
    }
}
echo json_encode($tab);
?>