Tutoriel utilisateur :



Bienvenue sur le tutoriel de notre application CityCross. Ce dernier va vous permettre de prendre en main rapidement notre produit final. Tout d’abord, au lancement de l’application, 4 interfaces sont disponibles (Guidage,Calage,Informations et Sauvegarde).

L’interface d’Informations est seulement une page explicative sur les ellipsoïdes. Elle est destinée au grand public qui ignorerait cette notion.

Il faut savoir que, pour utiliser correctement notre application, le téléphone doit être orienté horizontalement, comme ceci : 

Nous allons alors vous présenter une à une les différentes interfaces de notre application :



Interface de guidage : 

Cette fonctionnalité permet à l’utilisateur de connaître la direction d’une ville en fonction de sa propre position. Il lui suffit alors de rentrer alors la ville dont il souhaite connaître la direction. Le choix de l'ellipsoïde est facultatif puisque nous avons jugé que,pour un utilisateur lambda, cette notion peut sembler vague. Par conséquent une ellipsoïde est choisie par défaut, il s’agit de WGS84.Puis, en appuyant sur le bouton chercher, on obtient ceci :



La direction exacte est celle donnée par la flèche jaune. L’utilisateur doit alors pivoter sur lui-même jusqu’à aligner la droite à cette flèche. On sait que l’utilisateur est dans la bonne direction lorsque la couleur de fond passe du rouge au vert. On a aussi la distance de l’utilisateur à la ville sur l'ellipsoïde considéré affiché en haut de votre écran.



Interface de Calage :

 Cette interface prend en entrée une distance (à rentrer en mètres), un ellipsoïde ainsi qu’un nombre de villes. De même le champ ellipsoïde n’est pas nécessaire. Une fois qu’on a choisi le nombre d eville à afficher à la distance souhaitée, on obtient alors le résultat sous cette forme-ci :




En bas à droite, on aperçoit un bouton de sauvegarde, qui permet de stocker dans la mémoire de notre téléphone la recherche effectuée. Elle est stockée sous forme d’un fichier texte qui contient le nom de la ville, la distance entre l’utilisateur et la ville sur l'ellipsoïde utilisé, l’azimut ainsi que l'ellipsoïde considéré. Ce fichier est alors récupérable dans la mémoire de votre appareil et se présente sous la forme suivante :



Interface de sauvegarde : 

Enfin cette dernière interface fait appel à la mémoire propre à l’application. Elle permet de rendre compte des dernières recherches effectuées pour les 2 interfaces. Pour l’interface de guidage, on obtiendra un fichier texte contenant le nom de la ville visée, la distance, l’azimut ainsi que l'ellipsoïde considéré. Puis, pour la fonctionnalité de calage une petite subtilité intervient. On se rappelle que pour l’interface de calage, on limite l’affichage des villes à un nombre choisi par l’utilisateur. Or dans ce stockage interne, on obtiendra toutes les villes situées à la distance choisie par notre utilisateur. (Par exemple, on souhaite obtenir 10 villes à 200km de l’utilisateur dans l’interface de calage. Dans cette mémoire interne on obtient l’ensemble des villes à 200km et non pas seulement 10 qui, elles, sont disponibles dans la mémoire externe à l'application.)





