<?php
$servername = "localhost";
$username = "root";
$password = "root";
$nomVille = $_POST["nomVille"];

$link = new mysqli($servername, $username, $password);
$tab = [];
$requete = "SELECT Coordinates FROM citycross.villes WHERE Name = '$nomVille'";
if ($result = mysqli_query($link, $requete)) {
    while ($ligne = mysqli_fetch_assoc($result)) {
        $tab[] = $ligne;
    }
}
echo json_encode($tab);
?>