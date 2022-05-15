<?php
$servername = "localhost";
$username = "root";
$password = "root";
$numEllipsoide = $_POST["numEllipsoide"];

$link = new mysqli($servername, $username, $password);
$tab = [];
$requete = "SELECT srtext, proj4text FROM citycross.ellipsoides WHERE srtext LIKE '%SPHEROID%\"$numEllipsoide%AUTHORITY%' LIMIT 1";
if ($result = mysqli_query($link, $requete)) {
    while ($ligne = mysqli_fetch_assoc($result)) {
        $tab[] = $ligne;
    }
}
echo json_encode($tab);
?>