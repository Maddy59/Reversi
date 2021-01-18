La Programmation Orientée Objet (POO) avec JAVA  
***********************************************************************************
Exercice réalisé dans le cadre de ma formation Développeur Web/Web mobile avec ENI Ecole Informatique  
Janvier 2021  
***********************************************************************************
Les énumérations et la généricité    
TP Réversi    
Durée estimée : 5h  
IDE : Eclipse  
***********************************************************************************
__Partie A__ - Les énumérations (4h)   
__Enoncé__  
L’objectif est de créer un jeu de Reversi (également connu sous le nom d’Othello). Voici
l’explication et les règles de ce jeu, d’après Wikipédia :  
Il se joue sur un plateau unicolore de 64 cases, 8 sur 8. Les joueurs disposent de 64 pions
bicolores, noirs d'un côté et blancs de l'autre. En début de partie, quatre pions sont déjà
placés au centre du plateau dans la position suivante :  

![1](1.png)  

>Chaque joueur, noir et blanc, pose l'un après l'autre un pion de sa couleur sur le plateau de
jeu selon des règles précises. Le jeu s'arrête quand les deux joueurs ne peuvent plus poser
de pion. On compte alors le nombre de pions. Le joueur ayant le plus grand nombre de
pions de sa couleur sur le plateau a gagné.  

>Noir commence toujours la partie. Puis les joueurs jouent à tour de rôle, chacun étant tenu
de capturer des pions adverses lors de son mouvement. Si un joueur ne peut pas capturer
de pion(s) adverse(s), il est forcé de passer son tour. Si aucun des deux joueurs ne peut
jouer, ou si le plateau ne comporte plus de case vide, la partie s'arrête.  

>La capture de pions survient lorsqu'un joueur place un de ses pions à l'extrémité d'un
alignement de pions adverses contigus et dont l'autre extrémité est déjà occupée par un
de ses propres pions. Les alignements considérés peuvent être une colonne, une ligne, ou
une diagonale. Si le pion nouvellement placé vient fermer plusieurs alignements, il capture
tous les pions adverses des lignes ainsi fermées. La capture se traduit par le retournement
des pions capturés. Ces retournements n'entraînent pas d'effet de capture en cascade :
seul le pion nouvellement posé est pris en compte.  

>Pour vous familiariser avec ce jeu, vous pouvez tester une version pour jouer en ligne telle
que reversi.fr.  
*************************************************
__Indications__  
1 - Création du projet :  
- Garder les options par défaut, notamment la séparation des fichiers sources et
des fichiers compilés.  

2 - Création d’une énumération Pion :  
Donner trois valeurs possibles pour cette énumération : LIBRE (il n’y a pas encore de
pion), BLANC et NOIR.  

Ajouter un attribut nombre contenant le nombre de pions de cette couleur sur le
plateau de jeu. Ajouter également un accesseur pour cet attribut.  

Créer une méthode d’instance getSymbole() retournant le caractère représentant
ce pion (LIBRE : · , BLANC : o, NOIR : ●).  

Créer une méthode d’instance autrePion() qui retourne le pion opposé : si cette
méthode est appelé sur BLANC, cela retourne NOIR, et inversement.  

Créer une méthode d’instance gagne() prenant en paramètre le nombre de pions
qui changent de couleur suite à un coup effectué par un joueur. Cette méthode
met à jour l’attribut nombre.  

3 - Création de la classe PlateauDeReversi :  
Le constructeur initialise le plateau de jeu en configuration de début de partie.
La méthode d’instance afficher() écrit sur la console les scores et dessine le
plateau de jeu de la manière suivante :  

![2](2.png)  

La méthode tester() prend en paramètre un pion et deux coordonnées et
retourne un entier correspondant au nombre de pions qui changeraient de couleur
si le joueur choisissait cette case pour poser son pion.  

La méthode peutJouer() prend en paramètre un pion et teste s’il existe au moins
une position où il est possible de le poser.  

La méthode poser() prend en paramètre un pion et deux coordonnées. Elle
positionne le pion et change la couleur de tous les pions capturés par ce coup.  

Enfin, la méthode jouer() crée deux joueurs et les fait jouer à tour de rôle.  
Exemple de début de partie :  

![3](3.png)    
![4](4.png)  
![5](5.png)  

4 - Les différents types de joueurs :  
Créer une interface Joueur avec deux méthodes :  
- jouer() prenant en paramètre le plateau de jeu et le pion à poser et qui
retourne un tableau de deux entiers contenant les coordonnées choisies pour
ce pion.  

- getNom() retournant le nom du joueur.  

Créer deux classes implémentant cette interface, l’une pour faire jouer un humain
et l’autre pour faire jouer l’ordinateur. Pour faire jouer l’humain, il faut demander où
il souhaite positionner son pion, alors que pour l’ordinateur, il faut trouver une case
où il est permis de poser son pion.  

Modifier le reste du programme pour faire jouer au choix soit deux joueurs humains,
soit un humain contre un ordinateur, soit deux ordinateurs.  
**********************************************************************
__Partie B__ - La généricité (1h)      
__Enoncé__   
Il existe une multitude de jeux se jouant sur un plateau de jeu carré ou rectangulaire :  
Reversi, dames, bataille navale, échecs, morpion, Puissance 4, démineur… Il est possible de
créer une classe Plateau qui serait utilisée pour tous ces jeux. Ainsi, celle-ci mutualiserait les
actions communes à tous ces plateaux de jeu (affichage, modification d’une case et
consultation d’une case). Ce qui change entre tous ces plateaux de jeu, ce sont les pions
qui sont posés sur les cases. Cela sera donc paramétré par un type générique.  

![6](6.png) 
***************************************************************
Indications  
1 - Création du projet :  
- Garder les options par défaut, notamment la séparation des fichiers sources et
des fichiers compilés.  

2 - Création d’une interface Affichable :  
Créer une interface nommée Affichable possédant une unique méthode
getSymbole() ne prenant pas de paramètre et retournant un caractère.  

3 - Création d’une classe générique Plateau :  
Créer une classe Plateau paramétrée par un type T ayant pour contrainte
d’implémenter l’interface Affichable.  

Cette classe possède trois attributs : la largeur et la hauteur du plateau de jeu ainsi
qu’une liste d’éléments de type T représentant les pions sur le plateau de jeu. La
liste n’ayant qu’une dimension, les lignes du plateau de jeu sont placées dans celleci les unes à la suite des autres.

![7](7.png)  

Une case de coordonnées (basées à partir de zéro) ligne y colonne x aura pour
position dans la liste y × nbColonnes + x.  

Ajouter un constructeur prenant en paramètre le nombre de lignes et de colonnes
ainsi que la valeur initiale pour l’ensemble des cases du plateau de jeu.  

Créer enfin trois méthodes pour afficher le plateau de jeu, pour récupérer la valeur
d’une case du plateau et pour affecter une valeur d’une case.  

4 - Modification du Reversi :  
Adapter le code du jeu du Reversi précédemment créé pour utiliser la classe
générique Plateau. La classe PlateauDeReversi hérite maintenant de cette classe
générique Plateau.  

5 - D’autres jeux (optionnel) :  
Utiliser cette classe Plateau pour coder un ou plusieurs autres jeux.  

