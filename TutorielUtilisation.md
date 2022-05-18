Tutoriel d’installation de l’application :

L’application ne fonctionne que sur les téléphone android ayant un magnétomètre. Il en possède un si sur
google map vous voyez afficher la direction dans laquelle vous pointez votre téléphone en bleu.
Afin de télécharger l’application sur votre smartphone, il faut faire quelque réglages au préalable. Du côté
du smartphone, il faut mettre votre appareil en mode développement et valider l’option de débogage par
USB. Il faut télécharger le code sur le répertoire github sur android studio. Au niveau des classe
« guidage » et « calage », il faut modifier l’attribut qui s’appel « IP » en haut des classes et mettre votre
adresse IPv4. Ensuite, il faut télécharger MAMP s’il n’est pas présent sur votre ordinateur. Il faut copier
les quatre fichiers présent dans le dossier « bdd » (toujours dans le répertoire github) au niveau du chemin
d’accès au document qui est modifiable dans les préférences du logiciel MAMP. Vous pouvez à présent
brancher le téléphone sur votre ordinateur, lancer le serveur MAMP et lancer l’application lorsque
android studio a reconnu votre appareil. Lorsque vous avez réussi à lancer une fois l’application, cette
dernière sera téléchargée sur votre appareil et il ne sera plus nécessaire de le brancher pour lancer
l’application. Le serveur MAMP contenant les bases de données devra par contre être allumé.
Si ça bug, vérifiez que toutes les autorisations de l’application sont cochées.
