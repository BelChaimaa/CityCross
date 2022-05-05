<?php
$servername = "localhost";
$username = "root";
$password = "root";
$nomVille = $_POST["numEllipsoide"];

$link = new mysqli($servername, $username, $password);
$tab = [];
$requete = "SELECT proj4text FROM citycross.ellipsoides WHERE srid = '$numEllipsoide'";
if ($result = mysqli_query($link, $requete)) {
    while ($ligne = mysqli_fetch_assoc($result)) {
        $tab[] = $ligne;
    }
}
echo json_encode($tab);
?>