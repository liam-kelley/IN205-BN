# IN205-BN
Projet Bataille Navale ENSTA

Compilation : mvn clean install

éxécution : java -jar target/BN-Kelley-1.0.jar


# Explications

J'ai pas mal changé l'architecture du projet selon ce qui me semblait plus logique et naturel.
J'ai créé un type de joueur supplémentaire: Le joueur autosetup, qui a une installation automatique des bateaux mais est controllé par un joueur.
SendHit a été remplacé par player.DoHit, board.boardHitByOpponent et board.updateDoneHits pour plus de clarté.
Toute la gestion des entrées est gérée dans game.
J'ai ajouté plusieurs fonctions à la classe InputHelper.
J'ai implémenté un choix de joueurs au début du jeu.
J'ai créé deux types de game, qu'on peut sélectionner lors de la compilation. Game normal, ou il y a des pauses pour voir les différents affichages, et un "SuperFastGame" qui extends Game et Overrides la fonction pause pour qu'elle ne fasse plus rien.
