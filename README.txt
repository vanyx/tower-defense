			Projet Tower Defense
		Thomas BENALOUANE – Fabien LE MORZADEC


I. CONCEPT DU JEU

Notre jeu est basé sur les graphismes du jeu mobile Clash Of Clan.
Nous avons implémenté deux modes de jeu : un mode de jeu classique et un mode de jeu survie.
Le mode de jeu classique est simple : il est composé de 10 manches, de plus en plus difficiles.
Lors de la dernière manche le joueur affronte un boss final.
Le joueur gagne la partie s’il survie à toutes les manches et s’il bat le boss final.
Le mode survie comme son nom l’indique, est un mode où le joueur doit survivre le plus longtemps possible.

Plus les manches avancent, plus le jeu se complique.
Les monstres sont de plus en plus nombreux, de différents types, et ont de plus en plus de vie.
Le joueur a aussi le choix entre 2 plateaux de jeu différents, plus ou moins difficiles.
A chaque fin de manche, le joueur gagne 50 d’or, avant de passer à la manche suivante.
Cependant, avant la manche finale le joueur gagne 120 d’or.
Toute les commandes sont affichées dans la console lors du lancement du jeu.


II. AMELIORATION

1)	Amélioration des tours :
Le joueur a la possibilité d’améliorer chaque tour, sauf les tesla.
L’amélioration d’une tour coûte 40 d’or pour la passer au niveau 2, et 60 pour le niveau 3.
A chaque amélioration la tour récupère toute sa vie, gagne 1.5 de dégâts, 3 de vitesse et 0.05 de portée.
La mine d’or gagne quant à elle 15 de vitesse et 15 de gain à chaque amélioration.

2)	Tours :
Nous avons rajouté d’autres tours, en plus de celles déjà données.

- La tour anti aérienne : Elle tire des missiles téléguides (roquettes) sur les monstres volant.
Elle coute 60 d’or, a un temps de rechargement de 20, une portée de 0.2, et les roquettes ont une vitesse de 0.02.

- La tour de sorcier : elle tire du poison, qui permet d’empoisonner les monstres.
Elle coute 50 d’or, a un temps de rechargement de 15, une portée de 0.3.
Une fois empoisonnés, les monstres perdent 0.03 de vie à chaque update().
Un monstre ne peut pas être empoisonné plusieurs fois, ni soigné.

- La tesla : Elle tire un rayon électrique unique sur un monstre lorsqu’il est dans sa portée.
Elle coute 50 d’or, et a une portée de 0.2. Lorsqu’un monstre est touché par la tesla, il perd 0.1 de vie à chaque update().

- La mine d’or : Elle permet de rapporter de l’or toute les n secondes.
Elle cout 50 d’or, et rapporte 20 d’or tous les 150 update().

3)	Menu :
Lors du lancement du jeu le joueur a la possibilité de sélectionner ses options de jeu, à l’aide de boutons.

4)	Monstres :
Nous avons ajouté 2 types de monstres :

La sorcière : Elle a les mêmes caractéristiques que les monstres terrestres (BaseMonster).
Cependant elle peut tirer sur les tours voisines (mais pas sur les mine d’or).
Elle a une récompense de 8 d’or, une vitesse de tir de 20, et tir des boules de feu à une vitesse de 0.03.
Une boule de feu enlève 1 point de vie lorsqu’elle touche une tour.

Le golem : Il se déplace à une vitesse de 0.0035, a une récompense de 50 d’or, et 400 de vie.
Il est utilisé comme boss final lors de la 10e manche du mode classique.
En mode survie, il peut apparaitre plusieurs fois parmi les monstres d’une vague à partir de la 30e vague.

5)	Réparation des tours :
Nous avons également ajouté la possibilité de réparer les tours.
Réparer une tour coute 30 d’or, et remet la vie de la tour à 3.

6)	Codes de triche :
Nous avons aussi ajouté des codes de triches :
‘O’ pour avoir 10000 de vie, et ‘K’ pour avoir 10000 d’or.


III. REPARTITION DES ROLES

Pour démarrer le projet, Thomas s’est occupé des déplacements des monstres, des images et des améliorations.
Fabien s’est occupé des tours et des projectiles. Nous avons donc terminé le jeu de base à 2.
Le rendu du projet étant initialement prévu pour le 10/01, Thomas a réalisé quelques améliorations.
Après cette date Fabien ayant pris beaucoup de retard sur ces améliorations, Thomas a terminé la réalisation du jeu
(menu, graphismes, ajout de tours et de monstres, modes de jeu et cartes différentes, etc).

